/*
 *  Copyright © 2022 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 * CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.
 *
 */
package com.sb.staging.transform.impl;

import com.sb.staging.transform.Transformer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.*;
import reactor.core.publisher.Mono;

import java.util.*;

/**
 * Soap XML to json transformer
 *
 * @author gwang
 * @since 1.0
 */
@Slf4j
public class XmlTransformer implements Transformer {
    private String returnNode;
    private String rootNode;
    // if compress is true,  then if an element has no attribute, it will return: <node>TEXT</node>
    // if it is false, then it will return: <node><text>TEXT</text></node>
    private Boolean compress;
    private Boolean ignoreEmpty;

    public XmlTransformer(String returnNode, String rootNode, Boolean compress, Boolean ignoreEmpty) {
        this.returnNode = returnNode;
        this.rootNode = rootNode;
        this.compress = compress;
        this.ignoreEmpty = ignoreEmpty;
    }

    @Override
    public <T> Mono<T> transform(Object input) {
        try {
            // extra data, remove soap related tags
            String inputStr = handleInput((String) input, returnNode);
            Document document = DocumentHelper.parseText(inputStr);
            // get root node. only return rootNode data
            List<Node> list = document.selectNodes(rootNode);
            List<Object> results = new ArrayList<>();
            for (Node node : list) {
                if (node instanceof Element) {
                    Element el = (Element) node;
                    Map map = iterateElement(el);
                    results.add(map);
                }
            }
            // if root is one node, return Object, if it has multi node, return List.
            Object result = results;
            if (results.size() == 1) {
                result = results.get(0);
            } else if (results.size() == 0) {
                result = new HashMap<>();
            }
            // convert map to json.
            ObjectMapper objectMapper = new ObjectMapper();
            String resultStr = objectMapper.writeValueAsString(result);
            return Mono.just((T) resultStr);
        } catch (Exception e) {
            throw new RuntimeException("XML transformer error.", e);
        }
    }

    private Map iterateElement(Element element) {
        List<Element> nodes = element.elements();
        Map<String, Object> obj = new HashMap<>();
        Object temp;
        for (Element node : nodes) {
            List<Object> list = new LinkedList<>();
            if (node.elements().size() > 0) {
                if (obj.containsKey(node.getName())) {
                    temp = obj.get(node.getName());
                    // object to List, then add new object
                    list = prepareList(temp, list);
                    list.add(iterateElement(node));
                    obj.put(node.getName(), list);
                } else {
                    obj.put(node.getName(), iterateElement(node));
                }
            } else {
                // if text value is empty, if configured ignore, then ignore it.
                if (StringUtils.isBlank(node.getTextTrim()) && ignoreEmpty) {
                    continue;
                }
                if (obj.containsKey(node.getName())) {
                    temp = obj.get(node.getName());
                    // object to List, then add new object
                    list = prepareList(temp, list);
                    list.add(getNodeObj(node));
                    obj.put(node.getName(), list);
                } else {
                    obj.put(node.getName(), getNodeObj(node));
                }
            }
        }
        return obj;
    }

    private List<Object> prepareList(Object temp, List<Object> list) {
        if (temp instanceof List) {
            list = (List) temp;
        } else if (temp instanceof Map) {
            list.add(temp);
        } else {
            list.add(temp);
        }
        return list;
    }

    // extra data, remove soap related tags. will get the data from "node" tag.
    private static String handleInput(String input, String node) {
        if (StringUtils.isEmpty(node)) {
            return input.trim();
        }
        int idx = input.indexOf("<" + node + ">");
        if (idx < 0) {
            idx = input.indexOf("<" + node + " ");
        }
        int idx2 = input.lastIndexOf("</" + node + ">");
        if (idx < 0 || idx2 < 0) {
            log.error("node not existed.");
            return "";
        }
        String str = input.substring(idx + node.length() + 2, idx2);
        return str.trim();
    }

    private Object getNodeObj(Element element) {
        return getNodeObj(element, compress);
    }

    private Object getNodeObj(Element element, boolean compress) {
        List<Attribute> attrs = element.attributes();
        if (compress && (attrs == null || attrs.size() == 0)) {
            return element.getTextTrim();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("text", element.getTextTrim());
        for (Attribute attr : attrs) {
            map.put(attr.getName(), attr.getValue());
        }
        return map;
    }
}