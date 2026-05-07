package com.example.banco.infrastructure.adapters.out.persistence.mapper;

import com.example.banco.domain.model.Product;
import com.example.banco.infrastructure.adapters.out.persistence.entity.ClientEntity;
import com.example.banco.infrastructure.adapters.out.persistence.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductEntity toEntity(Product domain, ClientEntity clientEntity) {
        if (domain == null) return null;

        ProductEntity entity = new ProductEntity();
        entity.setId(domain.getId());
        entity.setType(domain.getType());
        entity.setAccountNumber(domain.getAccountNumber());
        entity.setStatus(domain.getStatus());
        entity.setBalance(domain.getBalance());
        entity.setGmfExempt(domain.isGmfExempt());
        entity.setClient(clientEntity);

        return entity;
    }

    public Product toDomain(ProductEntity entity) {
        if (entity == null) return null;
        return new Product(
                entity.getId(),
                entity.getType(),
                entity.getAccountNumber(),
                entity.getStatus(),
                entity.getBalance(),
                entity.isGmfExempt(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getClient().getId()
        );
    }
}
