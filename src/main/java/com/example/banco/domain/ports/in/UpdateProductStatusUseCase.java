package com.example.banco.domain.ports.in;

import com.example.banco.domain.model.Product;

public interface UpdateProductStatusUseCase {
    Product execute(String accountNumber, String newStatus);
}