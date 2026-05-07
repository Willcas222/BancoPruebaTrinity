package com.example.banco.application.dto;

import java.math.BigDecimal;

public record TransactionRequest(
        String accountNumber,
        BigDecimal amount
) {}
