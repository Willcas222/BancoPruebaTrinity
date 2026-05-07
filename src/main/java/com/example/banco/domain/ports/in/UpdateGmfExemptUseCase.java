package com.example.banco.domain.ports.in;

import com.example.banco.domain.model.Product;

public interface UpdateGmfExemptUseCase {
    Product execute(String accountNumber, boolean exempt);
}