/*
 *  Copyright © 2022 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 * CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.
 *
 */
package com.sb.staging.transform.jolt.function;

import com.bazaarvoice.jolt.common.Optional;
import com.sb.staging.transform.jolt.transform.CustomerTransformer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author yhuo
 * @since 1.0
 */
public class CurrTimeStringFunction implements CustomerTransformer {
    @Override
    public String getName() {
        return "getCurrTimeString";
    }

    @Override
    public Optional<Object> apply(Object... objects) {
        if (objects.length == 1) {
            LocalDateTime localDateTime = LocalDateTime.now();
            return Optional.of(localDateTime.format(DateTimeFormatter.ofPattern((String) objects[0])));
        }
        return Optional.empty();
    }

}
