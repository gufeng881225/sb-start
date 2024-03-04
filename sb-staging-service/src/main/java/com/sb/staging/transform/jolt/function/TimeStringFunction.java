/*
 *  Copyright © 2022 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 * CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.
 *
 */
package com.sb.staging.transform.jolt.function;

import com.bazaarvoice.jolt.common.Optional;
import com.sb.staging.transform.jolt.transform.CustomerTransformer;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author yhuo
 * @since 1.0
 */
public class TimeStringFunction implements CustomerTransformer {
    @Override
    public String getName() {
        return "getTimeString";
    }

    @Override
    public Optional<Object> apply(Object... objects) {
        if (objects.length == 2) {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(ZonedDateTime.parse((String) objects[0]).toInstant(), ZoneId.systemDefault());
            return Optional.of(localDateTime.format(DateTimeFormatter.ofPattern((String) objects[1])));
        }
        return Optional.empty();
    }

}
