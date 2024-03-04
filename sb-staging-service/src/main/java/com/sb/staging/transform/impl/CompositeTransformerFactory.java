/* Copyright © 2022 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.sb.staging.transform.impl;

import com.sb.staging.transform.Transformer;
import com.sb.staging.transform.TransformerFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * TODO description
 *
 * @author jzhai
 * @since 1.0
 */
@RequiredArgsConstructor
public class CompositeTransformerFactory implements TransformerFactory {
    private final List<TransformerFactory> transformerFactoryList;

    @Override
    public Transformer createTransformer(Map<String, Object> properties) {
        for (TransformerFactory cf : transformerFactoryList) {
            if (cf.supports(properties)) {
                return cf.createTransformer(properties);
            }
        }

        return null;
    }

    @Override
    public boolean supports(Map<String, Object> properties) {
        return true;
    }
}