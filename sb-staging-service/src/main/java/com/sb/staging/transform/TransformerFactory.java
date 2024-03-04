/* Copyright © 2022 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.sb.staging.transform;

import java.util.Map;

/**
 * TODO description
 *
 * @author jzhai
 * @since 1.0
 */
public interface TransformerFactory {
    Transformer createTransformer(Map<String, Object> properties);

    boolean supports(Map<String, Object> properties);
}
