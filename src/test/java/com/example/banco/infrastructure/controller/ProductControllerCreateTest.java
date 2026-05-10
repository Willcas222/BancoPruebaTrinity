package com.example.banco.infrastructure.controller;

import com.example.banco.domain.model.Product;
import com.example.banco.domain.ports.in.CreateProductUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerCreateTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreateProductUseCase createProductUseCase;

    @Test
    @DisplayName("Debe retornar 201 Created al crear una cuenta válida")
    void shouldReturnCreatedWhenProductIsValid() throws Exception {

        Product mockProduct = new Product("AHORRO", "5388026155", 6L);

        when(createProductUseCase.execute(anyLong(), anyString())).thenReturn(mockProduct);

        String jsonPayload = """
        {
            "type": "AHORRO",
            "clientId": 6,
            "initialBalance": 100000
        }
        """;

        // 3. Ejecución
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accountNumber").value("5388026155"));
    }
}