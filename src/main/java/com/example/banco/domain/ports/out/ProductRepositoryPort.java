package com.example.banco.domain.ports.out;

import com.example.banco.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
    Product save(Product product);
    boolean existsByAccountNumber(String accountNumber);
    boolean hasProducts(Long clientId);
    Optional<Product> findByAccountNumber(String accountNumber);
    boolean existsByClientIdAndGmfExemptTrue(Long clientId);
    Optional<Product> findById(Long id);
    void deleteById(Long id);


}
