package com.example.banco.infrastructure.adapters.in.web.controller;

import com.example.banco.application.dto.ClientRequest;
import com.example.banco.application.usecases.UpdateClientUseCaseImpl;
import com.example.banco.domain.model.Client;
import com.example.banco.domain.ports.in.CreateClientUseCase;
import com.example.banco.domain.ports.in.DeleteClientUseCase;
import com.example.banco.domain.ports.in.UpdateClientUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final CreateClientUseCase createClientUseCase;
    private final UpdateClientUseCase updateClientUseCase;
    private final DeleteClientUseCase deleteClientUseCase;

    public ClientController(CreateClientUseCase createClientUseCase,
                            UpdateClientUseCase updateClientUseCase,
                            DeleteClientUseCase deleteClientUseCase) {
        this.createClientUseCase = createClientUseCase;
        this.updateClientUseCase = updateClientUseCase;
        this.deleteClientUseCase = deleteClientUseCase;
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody ClientRequest request) {
        return new ResponseEntity<>(createClientUseCase.execute(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody ClientRequest request) {
        return ResponseEntity.ok(updateClientUseCase.execute(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        deleteClientUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}

