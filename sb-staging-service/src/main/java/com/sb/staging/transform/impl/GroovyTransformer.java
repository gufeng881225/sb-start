/* Copyright © 2022 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.sb.staging.transform.impl;

import com.sb.staging.transform.Transformer;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import reactor.core.publisher.Mono;

/**
 * GroovyTransformer
 *
 * @author gwang
 * @since 1.0
 */
public class GroovyTransformer implements Transformer {
    private final String script;

    public GroovyTransformer(Object script) {
        this.script = String.valueOf(script);
    }

    @Override
    public <T> Mono<T> transform(Object input) {
        // handle value by groovy
        Binding binding = new Binding();
        binding.setProperty("__param", input);

        GroovyShell groovyShell = new GroovyShell(binding);
        Object value = groovyShell.evaluate(script);
        return Mono.just((T) value);
    }
}