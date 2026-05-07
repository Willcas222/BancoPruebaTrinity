package com.example.banco.domain.ports.in;

import com.example.banco.domain.model.Movement;


import java.util.List;

public interface GetMovementsUseCase {
    List<Movement> execute(String accountNumber);
}