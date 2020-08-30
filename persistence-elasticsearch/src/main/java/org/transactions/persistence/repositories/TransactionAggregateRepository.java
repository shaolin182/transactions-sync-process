package org.transactions.persistence.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.transactions.persistence.model.TransactionES;

public interface TransactionAggregateRepository extends ElasticsearchRepository<TransactionES, String> {
}
