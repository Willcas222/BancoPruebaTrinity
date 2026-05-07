package com.example.banco.application.usecases;

import com.example.banco.domain.model.Client;
import com.example.banco.domain.ports.in.GetClientUseCase;
import com.example.banco.domain.ports.out.ClientRepositoryPort;
import java.util.Optional;

public class GetClientUseCaseImpl implements GetClientUseCase {

    private final ClientRepositoryPort repositoryPort;

    public GetClientUseCaseImpl(ClientRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Optional<Client> execute(Long id) {
        return repositoryPort.findById(id);
    }
}