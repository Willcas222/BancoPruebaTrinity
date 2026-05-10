package com.example.banco.application.usecases;

import com.example.banco.domain.model.Product;
import com.example.banco.domain.ports.in.UpdateGmfExemptUseCase;
import com.example.banco.domain.ports.out.ProductRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class UpdateGmfExemptUseCaseImplTest {

    @Autowired
    private UpdateGmfExemptUseCase updateGmfExemptUseCase;

    @MockitoBean
    private ProductRepositoryPort productRepository;

    @Test
    @DisplayName("Debe fallar si el cliente ya tiene otra cuenta exenta")
    void shouldFailIfClientAlreadyHasExemptAccount() {
        String accountNumber = "5388026155";
        Product mockProduct = new Product("AHORRO", accountNumber, 6L);

        when(productRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(mockProduct));

        when(productRepository.existsByClientIdAndGmfExemptTrue(6L)).thenReturn(true);

        assertThrows(IllegalStateException.class, () -> {
            updateGmfExemptUseCase.execute(accountNumber, true);
        });

        verify(productRepository, never()).save(any());
    }
}