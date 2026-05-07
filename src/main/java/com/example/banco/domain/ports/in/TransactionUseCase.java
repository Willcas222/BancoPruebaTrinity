package com.example.banco.domain.ports.in;

import java.math.BigDecimal;

public interface TransactionUseCase {
    void deposit(String accountNumber, BigDecimal amount);
    void withdraw(String accountNumber, BigDecimal amount);
    void transfer(String originAccount, String destinationAccount, BigDecimal amount);
}
