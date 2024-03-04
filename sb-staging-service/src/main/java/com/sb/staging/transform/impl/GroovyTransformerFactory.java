/* Copyright © 2022 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.sb.staging.transform.impl;

import com.sb.staging.transform.Transformer;
import com.sb.staging.transform.TransformerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * GroovyTransformerFactory
 *
 * @author gwang
 * @since 1.0
 */
@Component
public class GroovyTransformerFactory implements TransformerFactory {
    @Override
    public Transformer createTransformer(Map<String, Object> properties) {
        return new GroovyTransformer(properties.get("file"));
    }

    @Override
    public boolean supports(Map<String, Object> properties) {
        return "groovy".equals(properties.get("type"));
    }
}