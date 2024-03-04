/* Copyright © 2022 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.sb.staging.transform.impl;

import com.sb.staging.transform.Transformer;
import com.sb.staging.utils.JsonUtil;
import reactor.core.publisher.Mono;

/**
 * @author yhuo
 * @since 1.0
 */
public class JsonObjectTransformer implements Transformer {

    @Override
    public <T> Mono<T> transform(Object input) {
        return Mono.just(JsonUtil.jsonToObject(input));
    }
}
