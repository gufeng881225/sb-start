/* Copyright © 2021 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.sb.common.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * Supported jolt operation enumeration.
 *
 * @author jzhai
 * @since 2.0
 */
@Getter
public enum JoltOperationType {
    SHIFT("shift", "shift1");


    private final String value;
    private final String value1;

    JoltOperationType(String value, String value1) {
        this.value = value;
        this.value1 = value1;
    }

    public static JoltOperationType fromValue(String value) {
        return Arrays.stream(values())
                .filter(e -> e.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }
}

