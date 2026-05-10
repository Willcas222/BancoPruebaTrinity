package com.example.banco.infrastructure.controller;


import com.example.banco.domain.ports.in.UpdateGmfExemptUseCase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.MediaType;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UpdateGmfExemptUseCase updateGmfExemptUseCase;

    @Test
    @DisplayName("Debe retornar 200 OK al cambiar estado de GMF")
    void shouldReturnOkWhenUpdatingGmfStatus() throws Exception {
        String accNum = "5388026155";

        when(updateGmfExemptUseCase.execute(anyString(), anyBoolean())).thenReturn(null);

        mockMvc.perform(patch("/api/products/" + accNum + "/gmf-status")
                        .param("exempt", "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").exists());
    }
}