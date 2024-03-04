/* Copyright © 2022 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.sb.staging.transform.impl;

import com.sb.staging.transform.Transformer;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import reactor.core.publisher.Mono;

/**
 * TODO description
 *
 * @author jzhai
 * @since 1.0
 */
public class SpelTemplateTransformer implements Transformer {
    private final Expression expression;

    private final MapAccessor mapAccessor = new MapAccessor();

    public SpelTemplateTransformer(Object spec) {
        ExpressionParser parser = new SpelExpressionParser();
        this.expression = parser.parseExpression((String)spec, new TemplateParserContext());
    }

    @Override
    public <T> Mono<T> transform(Object input) {
            StandardEvaluationContext context = new StandardEvaluationContext(input);
            context.addPropertyAccessor(mapAccessor);
            Object value = expression.getValue(context);
            return Mono.just((T)value);
    }
}