package com.example.banco.infrastructure.controller;

import com.example.banco.domain.ports.in.CreateClientUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreateClientUseCase createClientUseCase;

    @Test
    @WithMockUser
    @DisplayName("Debe retornar 201 al crear un cliente nuevo")
    void shouldCreateClient() throws Exception {
        String payload = """
            {
                "name": "William",
                "lastName": "Cas",
                "documentType": "CC",
                "documentNumber": "12345",
                "birthDate": "1990-01-01"
            }
            """;

        mockMvc.perform(post("/api/clients")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated());
    }
}