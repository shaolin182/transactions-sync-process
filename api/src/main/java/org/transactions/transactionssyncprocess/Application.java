package org.transactions.transactionssyncprocess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "org.transactions")
@EnableMongoRepositories("org.transactions.persistence.repositories")
@EnableElasticsearchRepositories("org.transactions.persistence.repositories")
@ConfigurationPropertiesScan("org.transactions.persistence.config")
@PropertySource("classpath:application.properties")
@PropertySource({"classpath:application-${envTarget:dev}.properties"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
