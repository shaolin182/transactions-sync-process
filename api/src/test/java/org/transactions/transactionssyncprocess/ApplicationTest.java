package org.transactions.transactionssyncprocess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.transactions.persistence.repositories.TransactionAggregateRepository;
import org.transactions.persistence.repositories.TransactionsRepository;
import org.transactions.transactionssyncprocess.controller.SyncController;

@SpringBootTest
class ApplicationTest {

    @Autowired
    private SyncController controller;

    // Mock persistence layer
    @MockBean
    TransactionsRepository repository;

    // Mock persistence layer
    @MockBean
    TransactionAggregateRepository aggregateRepository;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertNotNull(controller);
    }
}