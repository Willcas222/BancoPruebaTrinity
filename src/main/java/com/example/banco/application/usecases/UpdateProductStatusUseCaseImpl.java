package com.example.banco.application.usecases;

import com.example.banco.domain.model.Product;
import com.example.banco.domain.ports.in.UpdateProductStatusUseCase;
import com.example.banco.domain.ports.out.ProductRepositoryPort;

public class UpdateProductStatusUseCaseImpl implements UpdateProductStatusUseCase {

    private final ProductRepositoryPort productRepository;

    public UpdateProductStatusUseCaseImpl(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product execute(String accountNumber, String newStatus) {

        Product product = productRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada: " + accountNumber));


        product.changeStatus(newStatus);


        return productRepository.save(product);
    }
}