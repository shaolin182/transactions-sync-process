package org.transactions.persistence.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration related to ES database
 */
@Configuration
@ConfigurationProperties(prefix = "es")
public class ElasticSearchDatabaseConfig {

    private String hostname;

    private String index;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
