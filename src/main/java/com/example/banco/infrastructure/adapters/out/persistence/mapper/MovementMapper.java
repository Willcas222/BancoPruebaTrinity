package com.example.banco.infrastructure.adapters.out.persistence.mapper;

import com.example.banco.domain.model.Movement;
import com.example.banco.infrastructure.adapters.out.persistence.entity.MovementEntity;
import org.springframework.stereotype.Component;

@Component
public class MovementMapper {


    public Movement toDomain(MovementEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Movement(
                entity.getId(),
                entity.getType(),
                entity.getAmount(),
                entity.getBalanceAfter(),
                entity.getCreatedAt(),
                entity.getProduct() != null ? entity.getProduct().getId() : null
        );
    }


    public MovementEntity toEntity(Movement domain) {
        if (domain == null) {
            return null;
        }

        MovementEntity entity = new MovementEntity();
        entity.setId(domain.getId());
        entity.setType(domain.getType());
        entity.setAmount(domain.getAmount());
        entity.setBalanceAfter(domain.getBalanceAfter());
        entity.setCreatedAt(domain.getCreatedAt());


        return entity;
    }
}