package org.transactions.transactionssyncprocess.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest
class SyncControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnEmptyContent() throws Exception {
        mockMvc.perform(post("/sync"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}