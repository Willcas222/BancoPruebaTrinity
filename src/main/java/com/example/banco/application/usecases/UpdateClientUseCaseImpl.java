package com.example.banco.application.usecases;

import com.example.banco.application.dto.ClientRequest;
import com.example.banco.domain.model.Client;
import com.example.banco.domain.ports.in.UpdateClientUseCase;
import com.example.banco.domain.ports.out.ClientRepositoryPort;

public class UpdateClientUseCaseImpl implements UpdateClientUseCase {

    private final ClientRepositoryPort repositoryPort;

    public UpdateClientUseCaseImpl(ClientRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Client execute(Long id, ClientRequest request) {

        Client existingClient = repositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id));


        existingClient.updateData(
                request.firstName(),
                request.lastName(),
                request.email()
        );


        return repositoryPort.save(existingClient);
    }
}