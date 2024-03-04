/*
 *  Copyright © 2022 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 * CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.
 *
 */
package com.sb.staging.transform.jolt.function;

import com.bazaarvoice.jolt.common.Optional;
import com.sb.staging.transform.jolt.transform.CustomerTransformer;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.MinguoDate;
import java.time.format.DateTimeFormatter;

/**
 * @author yhuo
 * @since 1.0
 */
public class DateStringFunction implements CustomerTransformer {
    public String getName() {
        return "getMinguoDateString";
    }

    @Override
    public Optional<Object> apply(Object... objects) {
        if (objects.length == 2) {
            LocalDate localDate = LocalDateTime.ofInstant(ZonedDateTime.parse((String) objects[0]).toInstant(), ZoneId.systemDefault()).toLocalDate();
            return Optional.of(MinguoDate.from(localDate).format(DateTimeFormatter.ofPattern((String) objects[1])));
        }
        return Optional.empty();
    }
}
