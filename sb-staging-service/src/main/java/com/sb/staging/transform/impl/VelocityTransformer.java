/*
 *  Copyright © 2022 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 * CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.
 *
 */
package com.sb.staging.transform.impl;

import com.sb.staging.transform.Transformer;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import reactor.core.publisher.Mono;

import java.io.StringWriter;
import java.util.Optional;

/**
 * @author gwang
 * @since 1.0
 */
@Slf4j
public class VelocityTransformer implements Transformer {
    private final String spec;
    private final String inputTag;

    public VelocityTransformer(String spec, String inputTag) {
        this.spec = spec;
        this.inputTag = Optional.ofNullable(inputTag).orElse("input");
    }

    @Override
    public <T> Mono<T> transform(Object input) {
        VelocityContext context = new VelocityContext();
        context.put(inputTag, input);
        String str = convertTemplate(spec, context);
        return (Mono<T>) Mono.just(str);
    }

    public String convertTemplate(String str, VelocityContext context) {
        try {
            StringWriter writer = new StringWriter();
            Velocity.evaluate(context, writer, "myString", str);
            return writer.toString();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}