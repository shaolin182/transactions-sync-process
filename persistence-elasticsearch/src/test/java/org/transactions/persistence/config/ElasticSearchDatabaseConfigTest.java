package org.transactions.persistence.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(value = ElasticSearchDatabaseConfig.class)
@TestPropertySource("classpath:elasticsearch.properties")
class ElasticSearchDatabaseConfigTest {

    @Autowired
    ElasticSearchDatabaseConfig databaseConfig;

    @Test
    void testLoadConfiguration() {
        assertEquals("indexES", databaseConfig.getIndex());
        assertEquals("hostname:8080", databaseConfig.getHostname());
    }
}