package com.example.banco.infrastructure.controller;

import com.example.banco.domain.ports.in.TransactionUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TransactionUseCase transactionUseCase;

    @Test
    @WithMockUser
    @DisplayName("Debe retornar 200 OK al realizar un depósito exitoso")
    void shouldReturnOkOnDeposit() throws Exception {
        String payload = """
            {
                "accountNumber": "5388026155",
                "amount": 50000.0
            }
            """;

        mockMvc.perform(post("/api/transaction/deposit")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Consignación realizada con éxito"));
    }

    @Test
    @WithMockUser
    @DisplayName("Debe retornar 200 OK al realizar una transferencia entre cuentas")
    void shouldReturnOkOnTransfer() throws Exception {
        String payload = """
            {
                "originAccount": "5388026155",
                "destinationAccount": "1234567890",
                "amount": 20000.0
            }
            """;

        mockMvc.perform(post("/api/transaction/transfer")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Transferencia realizada con éxito"));
    }
}