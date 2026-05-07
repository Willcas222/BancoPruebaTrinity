package com.example.banco.infrastructure.adapters.out.persistence;

import com.example.banco.domain.model.Client;
import com.example.banco.domain.ports.out.ClientRepositoryPort;
import com.example.banco.infrastructure.adapters.out.persistence.mapper.ClientMapper;
import com.example.banco.infrastructure.adapters.out.persistence.repository.JpaClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientPersistenceAdapter implements ClientRepositoryPort {

    private final JpaClientRepository repository;
    private final ClientMapper mapper;

    public ClientPersistenceAdapter(JpaClientRepository repository, ClientMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Client save(Client client) {
        var entity = mapper.toEntity(client);
        var savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Client> findById(Long id){
        return repository.findByIdNative(id).map(mapper::toDomain);
    }

    @Override
    @Transactional
    public void deleteById(Long id){
        repository.deleteByIdNative(id);
    }

    @Override
    public boolean existByIdentification(String identificationNumber) {
        return repository.existsByIdentificationNative(identificationNumber);
    }

    @Override
    public boolean existByEmail(String email) {
        return repository.existsByEmailNative(email);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }



}
