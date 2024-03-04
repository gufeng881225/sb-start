/* Copyright © 2022 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.sb.staging.utils;

import java.util.UUID;

/**
 * UUIDUtil
 *
 * @author gwang
 * @since 1.0
 */
public class UUIDUtil {
    private static Snowflake snowflake = new Snowflake();

    public static UUID randomUUID() {
        return UUID.randomUUID();
    }

    public static String randomUUIDStr() {
        return UUID.randomUUID().toString();
    }

    public static String snowFlakeIdStr() {
        return String.valueOf(snowflake.nextId());
    }
}