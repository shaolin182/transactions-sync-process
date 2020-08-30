package org.transactions.persistence;

import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.model.transactions.Transaction;
import org.transactions.persistence.config.ElasticSearchDatabaseConfig;
import org.transactions.persistence.factories.TransactionESFactory;
import org.transactions.persistence.model.TransactionES;
import org.transactions.persistence.repositories.TransactionAggregateRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransactionAggregateDatasourceTest {

    @Mock
    TransactionESFactory factory;

    @Mock
    TransactionAggregateRepository repository;

    @Mock
    RestHighLevelClient esClient;

    @Mock
    ElasticSearchDatabaseConfig esConfig;

    @AfterEach
    void resetMock() {
        Mockito.reset(factory, repository, esClient);
    }

    @Test
    void testPublishData() {
        List<Transaction> transactions = new ArrayList<>();
        List<TransactionES> transactionES = new ArrayList<>();

        Mockito.when(factory.buildTransactionESList(transactions)).thenReturn(transactionES);

        new TransactionAggregateDatasource(factory, repository, esClient, esConfig).publishData(transactions);

        verify(factory, times(1)).buildTransactionESList(transactions);
        verify(repository, times(1)).saveAll(transactionES);
    }
}