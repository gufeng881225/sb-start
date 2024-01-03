/* Copyright © 2022 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.sb.staging.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ObjectUtils
 *
 * @author gwang
 * @since 1.0
 */
public class ObjectUtils {
    public static String getDataJson(Object obj) {
        try {
            if (obj instanceof String) {
                return String.valueOf(obj);
            } else {
                ObjectMapper json = new ObjectMapper();
                return json.writeValueAsString(obj);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}