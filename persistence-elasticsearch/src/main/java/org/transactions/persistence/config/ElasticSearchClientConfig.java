package org.transactions.persistence.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class ElasticSearchClientConfig extends AbstractElasticsearchConfiguration {

    @Autowired
    ElasticSearchDatabaseConfig esConfig;

    /**
     * Create a client for connecting to elastic search instances
     * @return : a client for connecting to elastic search
     */
    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(esConfig.getHostname())
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
