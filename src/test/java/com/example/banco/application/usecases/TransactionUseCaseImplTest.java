package com.example.banco.application.usecases;

import com.example.banco.domain.model.Product;
import com.example.banco.domain.ports.out.ProductRepositoryPort;
import com.example.banco.domain.ports.out.MovementRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionUseCaseImplTest {

    @Mock
    private ProductRepositoryPort productRepository;

    @Mock
    private MovementRepositoryPort movementRepository;

    @InjectMocks
    private TransactionUseCaseImpl transactionUseCase;

    @Test
    void shouldThrowExceptionWhenBalanceIsInsufficientForWithdrawAndGmf() {

        String accNum = "5388026155";

        Product product = new Product("AHORRO", accNum, 6L);

        product.executeTransaction(new BigDecimal("10000"));

        when(productRepository.findByAccountNumber(accNum)).thenReturn(Optional.of(product));

        assertThrows(IllegalArgumentException.class, () -> {
            transactionUseCase.withdraw(accNum, new BigDecimal("10000"));
        });
    }
}