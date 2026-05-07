package com.example.banco.domain.ports.in;

import com.example.banco.application.dto.ClientRequest;
import com.example.banco.domain.model.Client;

public interface UpdateClientUseCase {
    Client execute(Long id, ClientRequest request);
}