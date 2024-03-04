/*
 *  Copyright © 2022 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 * CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.
 *
 */
package com.sb.staging.transform.impl;

import com.sb.staging.transform.Transformer;
import com.sb.staging.transform.TransformerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author gwang
 * @since 1.0
 */
@Component
public class XmlTransformerFactory implements TransformerFactory {
    @Override
    public Transformer createTransformer(Map<String, Object> properties) {
        return new XmlTransformer((String) properties.get("returnNode"), (String) properties.get("rootNode"),
                properties.get("compress") == null || (Boolean) properties.get("compress"),
                properties.get("ignoreEmpty") == null || (Boolean) properties.get("ignoreEmpty")
        );
    }

    @Override
    public boolean supports(Map<String, Object> properties) {
        return "xml".equals(properties.get("type"));
    }
}