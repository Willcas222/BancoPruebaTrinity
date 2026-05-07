package com.example.banco.application.dto;

import java.math.BigDecimal;

public record TransferRequest(
        String originAccount,
        String destinationAccount,
        BigDecimal amount
) {}
