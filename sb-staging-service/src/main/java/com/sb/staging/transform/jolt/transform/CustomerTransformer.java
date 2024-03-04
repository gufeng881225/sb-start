package com.sb.staging.transform.jolt.transform;

import com.bazaarvoice.jolt.modifier.function.Function;

public interface CustomerTransformer extends Function {
    public String getName();
}
