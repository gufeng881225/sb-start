/*
 *  Copyright © 2022 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 * CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.
 *
 */
package com.sb.staging.transform.impl;

import com.sb.staging.transform.Transformer;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.opencsv.CSVReader;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yhuo
 * @since 1.0
 */
public class CsvTransformer implements Transformer {
    private static final String SPLITTER  = ",";
    private final List<String> COLUMN_LIST = Lists.newArrayList();
    private String separator;
    private Boolean skipHeader;


    public CsvTransformer(String headers, String separator, Boolean skipHeader) {
        COLUMN_LIST.addAll(Arrays.asList(headers.split(SPLITTER)));
        this.separator = separator;
        this.skipHeader = skipHeader;
    }

    @Override
    public <T> Mono<T> transform(Object input) {
        String content = (String) input;
        if (this.skipHeader && content.indexOf("\n") > 0) {
            content = content.substring(content.indexOf("\n") + 1);
        }
        List<Map> results = Lists.newArrayList();
        if (StringUtils.hasLength(content)) {
            CSVReader reader = new CSVReader(new StringReader(content), this.separator.charAt(0));
            String[] record;

            try {
                while ((record = reader.readNext()) != null) {
                    Map map = Maps.newHashMap();
                    for (int i = 0; i < COLUMN_LIST.size(); i++) {
                        map.put(COLUMN_LIST.get(i), record[i]);
                    }
                    results.add(map);
                }
            } catch (Exception e) {
                throw new RuntimeException("CSV transformer error.", e);
            } finally {
                try {
                    reader.close();
                } catch (Exception ignore) {
                }
            }
        }
        return Mono.just((T) results);
    }
}