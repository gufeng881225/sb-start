/*
 *  Copyright © 2022 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 * CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.
 *
 */
package com.sb.staging.transform.jolt.transform;

import com.bazaarvoice.jolt.ContextualTransform;
import com.bazaarvoice.jolt.SpecDriven;
import com.bazaarvoice.jolt.common.Optional;
import com.bazaarvoice.jolt.common.tree.MatchedElement;
import com.bazaarvoice.jolt.common.tree.WalkedPath;
import com.bazaarvoice.jolt.modifier.OpMode;
import com.bazaarvoice.jolt.modifier.TemplatrSpecBuilder;
import com.bazaarvoice.jolt.modifier.function.Function;
import com.bazaarvoice.jolt.modifier.spec.ModifierCompositeSpec;
import com.google.common.collect.Maps;
import org.reflections.Reflections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author yhuo
 * @since 1.0
 */


public class CustomizedTransform implements SpecDriven, ContextualTransform {
    private final ModifierCompositeSpec rootSpec;

    public CustomizedTransform(Object spec) {
        Map<String, Function> functionsMap = Maps.newHashMap();
        // put customer transformers
        functionsMap.putAll(getCustomerTransformers());
        functionsMap = Collections.unmodifiableMap(functionsMap);
        TemplatrSpecBuilder templatrSpecBuilder = new TemplatrSpecBuilder(OpMode.OVERWRITR, functionsMap);
        rootSpec = new ModifierCompositeSpec(ROOT_KEY, (Map<String, Object>) spec, OpMode.OVERWRITR, templatrSpecBuilder);
    }

    public Map<String, Function> getCustomerTransformers() {
        Map<String, Function> map = new HashMap<>();
        Reflections reflections = new Reflections("com");
        Set<Class<? extends CustomerTransformer>> classSet = reflections.getSubTypesOf(CustomerTransformer.class);

        for (Class<? extends CustomerTransformer> clazz : classSet) {
            try {
                CustomerTransformer obj = clazz.getDeclaredConstructor().newInstance();
                map.put(obj.getName(), obj);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return map;
    }

    @Override
    public Object transform(Object input, Map<String, Object> context) {

        Map<String, Object> contextWrapper = Maps.newHashMap();
        contextWrapper.put(ROOT_KEY, context);
        MatchedElement rootLpe = new MatchedElement(ROOT_KEY);
        WalkedPath walkedPath = new WalkedPath();
        walkedPath.add(input, rootLpe);
        rootSpec.apply(ROOT_KEY, Optional.of(input), walkedPath, null, contextWrapper);
        return input;
    }
}
