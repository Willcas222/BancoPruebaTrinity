package com.example.banco.infrastructure.adapters.out.persistence;

import com.example.banco.domain.model.Product;
import com.example.banco.domain.ports.out.ProductRepositoryPort;
import com.example.banco.infrastructure.adapters.out.persistence.entity.ClientEntity;
import com.example.banco.infrastructure.adapters.out.persistence.entity.ProductEntity;
import com.example.banco.infrastructure.adapters.out.persistence.mapper.ProductMapper;
import com.example.banco.infrastructure.adapters.out.persistence.repository.JpaClientRepository;
import com.example.banco.infrastructure.adapters.out.persistence.repository.JpaProductRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductPersistenceAdapter implements ProductRepositoryPort {

    private final JpaProductRepository jpaProductRepository;
    private final JpaClientRepository jpaClientRepository;
    private final ProductMapper productMapper;


    public ProductPersistenceAdapter(JpaProductRepository jpaProductRepository,
                                     JpaClientRepository jpaClientRepository,
                                     ProductMapper productMapper) {
        this.jpaProductRepository = jpaProductRepository;
        this.jpaClientRepository = jpaClientRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product save(Product product) {
        ClientEntity clientEntity = jpaClientRepository.findById(product.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el cliente con ID: " + product.getClientId()));
        ProductEntity entity = productMapper.toEntity(product, clientEntity);
        ProductEntity savedEntity = jpaProductRepository.saveAndFlush(entity);
        return productMapper.toDomain(savedEntity);
    }

    @Override
    public boolean existsByAccountNumber(String accountNumber) {
        return jpaProductRepository.existsByAccountNumberNative(accountNumber);
    }

    @Override
    public boolean hasProducts(Long clientId) {
        return jpaProductRepository.countByClientIdNative(clientId) > 0;
    }

    @Override
    public Optional<Product> findByAccountNumber(String accountNumber) {

        return jpaProductRepository.findByAccountNumberNative(accountNumber)
                .map(productMapper::toDomain);
    }

    @Override
    public boolean existsByClientIdAndGmfExemptTrue(Long clientId) {
        return jpaProductRepository.existsGmfExemptByClientIdNative(clientId);
    }
}