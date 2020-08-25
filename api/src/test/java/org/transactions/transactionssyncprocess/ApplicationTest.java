package org.transactions.transactionssyncprocess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.transactions.connector.ITransactionsReadOnlyDatasource;
import org.transactions.sync.connector.ITransactionsAggregateDatasource;
import org.transactions.transactionssyncprocess.controller.SyncController;

@SpringBootTest
class ApplicationTest {

    @Autowired
    private SyncController controller;

    @TestConfiguration
    static class ApplicationTestContextConfiguration {

        @Bean
        public ITransactionsReadOnlyDatasource readOnlyDatasource() {
            return Mockito.mock(ITransactionsReadOnlyDatasource.class);
        }

        @Bean
        public ITransactionsAggregateDatasource aggregateDatasourceDatasource() {
            return Mockito.mock(ITransactionsAggregateDatasource.class);
        }
    }

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertNotNull(controller);
    }
}