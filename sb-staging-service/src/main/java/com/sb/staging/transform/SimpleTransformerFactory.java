/* Copyright © 2022 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.sb.staging.transform;

import java.util.Map;

/**
 * SimpleTransformerFactory
 *
 * @author gwang
 * @since 1.0
 */
public interface SimpleTransformerFactory extends TransformerFactory {
    String getName();

    default boolean supports(Map<String, Object> properties) {
        return getName().equals(properties.get("type"));
    }
}
