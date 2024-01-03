/* Copyright © 2022 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package sb.deploy;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Map;

/**
 * InitLiquibase
 *
 * @author gwang
 * @since 1.0
 */
@Component
@RequiredArgsConstructor
public class InitLiquibase {

    private final DeployLiquibaseConfigurationProperties liquibaseConfigurationProperties;

    @PostConstruct
    public void initLiquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        Map map = liquibaseConfigurationProperties.getDataSource();
        map.keySet().stream().forEach(key -> {
            Map configMap = (Map) map.get(key);
            DataSource dataSource = getDataSource(configMap);
            Map liquibaseMap = (Map) configMap.get("liquibase");
            try {
                liquibase.setDataSource(dataSource);
                liquibase.setDropFirst((Boolean) liquibaseMap.get("drop-first"));
                liquibase.setChangeLog((String) liquibaseMap.get("change-log"));
                liquibase.setShouldRun(true);
                // This runs Liquibase
                liquibase.afterPropertiesSet();
            } catch (LiquibaseException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Liquibase executed for datasource: " + key);
        });
    }

    private DataSource getDataSource(Map configMap) {
        String driver = (String) configMap.get("driver");
        String url = (String) configMap.get("url");
        String username = (String) configMap.get("username");
        String password = (String) configMap.get("password");
        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName(driver)
                .url(url)
                .username(username)
                .password(password)
                .build();
        return dataSource;
    }
}