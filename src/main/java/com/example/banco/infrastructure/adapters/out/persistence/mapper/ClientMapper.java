package com.example.banco.infrastructure.adapters.out.persistence.mapper;

import com.example.banco.domain.model.Client;
import com.example.banco.infrastructure.adapters.out.persistence.entity.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {


    public Client toDomain(ClientEntity entity) {
        if (entity == null) return null;

        return new Client(
                entity.getId(),
                entity.getIdentificationType(),
                entity.getIdentificationNumber(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getBirthDate(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }


    public ClientEntity toEntity(Client domain) {
        if (domain == null) return null;

        ClientEntity entity = new ClientEntity();
        entity.setId(domain.getId());
        entity.setIdentificationType(domain.getIdentificationType());
        entity.setIdentificationNumber(domain.getIdentificationNumber());
        entity.setFirstName(domain.getFirstName());
        entity.setLastName(domain.getLastName());
        entity.setEmail(domain.getEmail());
        entity.setBirthDate(domain.getBirthDate());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());

        return entity;
    }
}