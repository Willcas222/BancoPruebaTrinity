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

        if (request.identificationNumber() != null && !request.identificationNumber().trim().isEmpty()
                && !request.identificationNumber().equals(existingClient.getIdentificationNumber())) {

            if (repositoryPort.existByIdentification(request.identificationNumber())) {
                throw new IllegalArgumentException("Ya existe otro cliente con el número de identificación: "
                        + request.identificationNumber());
            }
        }

        if (request.email() != null && !request.email().trim().isEmpty()
                && !request.email().equals(existingClient.getEmail())) {

            if (repositoryPort.existByEmail(request.email())) {
                throw new IllegalArgumentException("El correo electrónico " + request.email() + " ya está registrado por otro cliente.");
            }
        }

        String finalFirstName = (request.firstName() != null && !request.firstName().trim().isEmpty())
                ? request.firstName() : existingClient.getFirstName();

        String finalLastName = (request.lastName() != null && !request.lastName().trim().isEmpty())
                ? request.lastName() : existingClient.getLastName();

        String finalEmail = (request.email() != null && !request.email().trim().isEmpty())
                ? request.email() : existingClient.getEmail();

        String finalIdentNumber = (request.identificationNumber() != null && !request.identificationNumber().trim().isEmpty())
                ? request.identificationNumber() : existingClient.getIdentificationNumber();

        String finalIdentType = (request.identificationType() != null && !request.identificationType().trim().isEmpty())
                ? request.identificationType() : existingClient.getIdentificationType();


        existingClient.updateData(
                finalFirstName,
                finalLastName,
                finalEmail,
                finalIdentNumber,
                finalIdentType
        );

        return repositoryPort.save(existingClient);
    }
}