/* Copyright © 2022 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.sb.staging.transform.impl;

import com.sb.staging.transform.Transformer;
import com.sb.staging.utils.JsonUtil;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * @author yhuo
 * @since 1.0
 */
public class JsonArrayTransformer implements Transformer {
    @Override
    public <T> Mono<T> transform(Object input) {
        Object obj = JsonUtil.jsonToObject(input);
        if (obj instanceof List) {
            return Mono.just((T) obj);
        } else {
            return Mono.just((T) Arrays.asList(obj));
        }
    }
}
