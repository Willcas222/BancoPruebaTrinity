package com.example.banco.domain.ports.in;

import com.example.banco.domain.model.Product;

public interface CreateProductUseCase {
    Product execute(Long clientId, String type);
}
