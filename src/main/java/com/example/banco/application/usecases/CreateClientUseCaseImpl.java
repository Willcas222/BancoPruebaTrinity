package com.example.banco.application.usecases;

import com.example.banco.application.dto.ClientRequest;
import com.example.banco.domain.model.Client;
import com.example.banco.domain.ports.in.CreateClientUseCase;
import com.example.banco.domain.ports.out.ClientRepositoryPort;


public class CreateClientUseCaseImpl implements CreateClientUseCase {

    private final ClientRepositoryPort repositoryPort;

    public CreateClientUseCaseImpl(ClientRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    public Client execute(ClientRequest request) {

        validateRequiredFields(request);

        if (repositoryPort.existByIdentification(request.identificationNumber())) {
            throw new IllegalArgumentException("Ya existe un cliente con el número de identificación: "
                    + request.identificationNumber());
        }

        if (repositoryPort.existByEmail(request.email())) {
            throw new IllegalArgumentException("El correo electrónico " + request.email() + " ya está registrado.");
        }

        Client client = new  Client(
                request.identificationType(),
                request.identificationNumber(),
                request.firstName(),
                request.lastName(),
                request.email(),
                request.birthDate()
        );

        return repositoryPort.save(client);
    }

    private void validateRequiredFields(ClientRequest request) {
        if (request.identificationNumber() == null || request.identificationNumber().trim().isEmpty() ||
                request.identificationType() == null || request.identificationType().trim().isEmpty() ||
                request.firstName() == null || request.firstName().trim().isEmpty() ||
                request.lastName() == null || request.lastName().trim().isEmpty() ||
                request.email() == null || request.email().trim().isEmpty() ||
                request.birthDate() == null) {

            throw new IllegalArgumentException("No se permiten campos vacíos al crear un cliente.");
        }
    }
}
