package org.transactions.transactionssyncprocess.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.transactions.persistence.repositories.TransactionAggregateRepository;
import org.transactions.persistence.repositories.TransactionsRepository;
import org.transactions.sync.ISyncService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest
class SyncControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @TestConfiguration
    static class SyncControllerTestContextConfiguration {

        @Bean
        public ISyncService syncService() {
            return Mockito.mock(ISyncService.class);
        }
    }

    @MockBean
    TransactionsRepository repository;

    @MockBean
    TransactionAggregateRepository aggregateRepository;

    @Autowired
    ISyncService syncService;

    @Test
    void shouldReturnEmptyContent() throws Exception {
        mockMvc.perform(post("/sync"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(syncService, times(1)).syncDatabase();
    }

}