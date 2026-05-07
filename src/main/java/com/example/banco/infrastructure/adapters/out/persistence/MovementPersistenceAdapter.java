package com.example.banco.infrastructure.adapters.out.persistence;

import com.example.banco.domain.model.Movement;
import com.example.banco.domain.model.Product;
import com.example.banco.domain.ports.out.MovementRepositoryPort;
import com.example.banco.infrastructure.adapters.out.persistence.mapper.MovementMapper;
import com.example.banco.infrastructure.adapters.out.persistence.repository.JpaMovementRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;


@Component
public class MovementPersistenceAdapter implements MovementRepositoryPort {

    private final JpaMovementRepository jpaMovementRepository;
    private final MovementMapper movementMapper;

    public MovementPersistenceAdapter(JpaMovementRepository jpaMovementRepository,
                                      MovementMapper movementMapper) {
        this.jpaMovementRepository = jpaMovementRepository;
        this.movementMapper = movementMapper;
    }

    @Override
    public List<Movement> findAllByAccountNumber(String accountNumber) {
        return jpaMovementRepository.findAllByAccountNumberNative(accountNumber)
                .stream()
                .map(movementMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public void register(Product product, String type, BigDecimal amount) {
        jpaMovementRepository.saveNative(
                type,
                amount,
                product.getBalance(),
                java.time.LocalDateTime.now(),
                product.getId()
        );
    }
}