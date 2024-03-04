/* Copyright © 2022 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.sb.staging.transform.impl;

import com.sb.staging.utils.JsonUtil;
import com.sb.staging.transform.Transformer;
import org.json.JSONObject;
import org.json.XML;
import reactor.core.publisher.Mono;

/**
 * @author gwang
 * @since 1.0
 */
public class JsonXmlTransformer implements Transformer {
    private String rootName;

    public JsonXmlTransformer(String rootName) {
        this.rootName = rootName;
    }

    @Override
    public <T> Mono<T> transform(Object input) {
        String str = JsonUtil.objectToJson(input);
        JSONObject obj = new JSONObject(str);
        rootName = rootName == null ? "root" : rootName;
        String xmlStr = XML.toString(obj, rootName);

        return (Mono<T>) Mono.just(xmlStr);
    }
}
