package com.example.banco.application.usecases;

import com.example.banco.domain.model.Movement;
import com.example.banco.domain.ports.in.GetMovementsUseCase;
import com.example.banco.domain.ports.out.MovementRepositoryPort;
import java.util.List;

public class GetMovementsUseCaseImpl implements GetMovementsUseCase {
    private final MovementRepositoryPort movementRepository;

    public GetMovementsUseCaseImpl(MovementRepositoryPort movementRepository) {
        this.movementRepository = movementRepository;
    }

    @Override
    public List<Movement> execute(String accountNumber) {
        return movementRepository.findAllByAccountNumber(accountNumber);
    }
}