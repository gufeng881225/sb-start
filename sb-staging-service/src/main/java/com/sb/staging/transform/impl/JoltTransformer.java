/* Copyright © 2022 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package com.sb.staging.transform.impl;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;
import com.sb.staging.transform.Transformer;
import com.sb.staging.utils.JsonUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * TODO description
 *
 * @author jzhai
 * @since 1.0
 */
public class JoltTransformer implements Transformer {
    private final Chainr chainr;
    private static final String FILE_PATH = "META-INF/jolt/transform.properties";
    private static final String OPERATION = "operation";
    private final Properties properties;


    public JoltTransformer(Object spec){
        try {
            this.properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource(FILE_PATH));
        } catch (Exception e) {
            throw new RuntimeException("Jolt transformer cannot load jolt define file.",e);
        }
        List list = JsonUtils.jsonToList((String) spec);
        list.forEach(o -> {
            Map map = (Map) o;
            if (map.containsKey(OPERATION) && properties.getProperty((String) map.get(OPERATION)) != null) {
                map.put(OPERATION, properties.getProperty((String) map.get(OPERATION)));
            }

        });
        this.chainr = Chainr.fromSpec(list);
    }

    @Override
    public <T> Mono<T> transform(Object input) {
        String str = JsonUtil.objectToJson(input);
        Object transformedOutput = chainr.transform(JsonUtils.jsonToObject(str));
        return Mono.just((T) (transformedOutput == null ? input : transformedOutput));
    }
}