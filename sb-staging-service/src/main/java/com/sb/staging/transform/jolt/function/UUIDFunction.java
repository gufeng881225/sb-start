/*
 *  Copyright © 2022 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 * CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.
 *
 */
package com.sb.staging.transform.jolt.function;

import com.bazaarvoice.jolt.common.Optional;
import com.sb.staging.transform.jolt.transform.CustomerTransformer;
import com.sb.staging.utils.UUIDUtil;

/**
 * @author yhuo
 * @since 1.0
 */
public class UUIDFunction implements CustomerTransformer {
    @Override
    public String getName() {
        return "uuid";
    }

    @Override
    public Optional<Object> apply(Object... objects) {
        return Optional.of(UUIDUtil.snowFlakeIdStr());
    }

}
