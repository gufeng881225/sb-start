/* Copyright © 2022 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.sb.staging.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * JsonUtil
 *
 * @author gwang
 * @since 1.0
 */
public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static String objectToJson(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map jsonToMap(String str) {
        return jsonToMap(str, true);
    }

    public static Map jsonToMap(String str, boolean compress) {
        try {
            Map map = objectMapper.readValue(str, Map.class);
            if (compress) {
                return handleEmpty(map);
            } else {
                return map;
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map handleEmpty(Map map) {
        Map resultMap = new HashMap();
        Set set = map.keySet();
        for (Object key : set) {
            Object val = map.get(key);
            if (val instanceof Map) {
                resultMap.put(key, handleEmpty((Map) val));
            } else if (val instanceof List) {
                List list = new ArrayList();
                for (Object listVal : (List) val) {
                    list.add(handleEmpty((Map) listVal));
                }
                resultMap.put(key, list);
            } else {
                if (val != null
                        && (!(val instanceof Integer) || (Integer) val != 0)
                        && (!(val instanceof Boolean) || (Boolean) val)
                ) {
                    resultMap.put(key, val);
                }
            }
        }
        return resultMap;
    }

    public static <T> T jsonToObject(Object obj) {
        Class clazz = Map.class;
        String input = String.valueOf(obj).trim();
        if (input.startsWith("[")) {
            clazz = List.class;
        }
        return (T) jsonToObject(obj, clazz);
    }

    public static <T> T jsonToObject(Object obj, Class<T> clazz) {
        try {
            String str;
            if (obj instanceof String) {
                str = (String) obj;
            } else {
                str = objectToJson(obj);
            }
            return objectMapper.readValue(str, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map objectToMap(Object obj) {
        try {
            String str = objectToJson(obj);
            return jsonToMap(str);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object convertValue(Object input, String type) {
        if ("string".equalsIgnoreCase(type)) {
            return objectToJson(input);
        } else if ("object".equalsIgnoreCase(type)) {
            return jsonToObject(input);
        }
        return input;
    }
}