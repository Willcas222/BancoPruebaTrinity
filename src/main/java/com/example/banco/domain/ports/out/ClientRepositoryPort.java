package com.example.banco.domain.ports.out;

import com.example.banco.domain.model.Client;

import java.util.Optional;

public interface ClientRepositoryPort {
    Client save(Client client);
    Optional<Client>  findById(Long id);
    void deleteById(Long id);
    boolean existByIdentification(String identificationNumber);
    boolean existByEmail(String email);
    boolean existsById(Long id);
}
