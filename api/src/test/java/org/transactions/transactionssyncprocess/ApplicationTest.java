package org.transactions.transactionssyncprocess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.transactions.transactionssyncprocess.controller.SyncController;

@SpringBootTest
class ApplicationTest {

    @Autowired
    private SyncController controller;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertNotNull(controller);
    }
}