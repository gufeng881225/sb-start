package com.sb.staging.transform.impl;
/* Copyright © 2022 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/


import com.sb.staging.transform.SimpleTransformerFactory;
import com.sb.staging.transform.Transformer;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author gwang
 * @since 1.0
 */
@Component
public class JsonStringTransformerFactory implements SimpleTransformerFactory {
    @Override
    public Transformer createTransformer(Map<String, Object> properties) {
        return new JsonStringTransformer();
    }

    @Override
    public String getName() {
        return "jsonString";
    }
}
