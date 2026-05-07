package com.example.banco.domain.ports.in;

import com.example.banco.domain.model.Client;

import java.util.Optional;

public interface GetClientUseCase {
    Optional<Client> execute(Long id);
}