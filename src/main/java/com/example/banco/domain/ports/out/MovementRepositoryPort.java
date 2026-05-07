package com.example.banco.domain.ports.out;

import com.example.banco.domain.model.Movement;
import com.example.banco.domain.model.Product;


import java.math.BigDecimal;
import java.util.List;

public interface MovementRepositoryPort {
    void register(Product product, String type, BigDecimal amount);
    List<Movement> findAllByAccountNumber(String accountNumber);
}
