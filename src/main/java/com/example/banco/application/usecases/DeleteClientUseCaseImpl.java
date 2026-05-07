package com.example.banco.application.usecases;

import com.example.banco.domain.ports.in.DeleteClientUseCase;
import com.example.banco.domain.ports.out.ClientRepositoryPort;
import com.example.banco.domain.ports.out.ProductRepositoryPort;

public class DeleteClientUseCaseImpl implements DeleteClientUseCase {

    private final ClientRepositoryPort clientRepository;
    private final ProductRepositoryPort productRepository;

    public DeleteClientUseCaseImpl(ClientRepositoryPort clientRepository,
                                   ProductRepositoryPort productRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void execute(Long clientId) {
        if (productRepository.hasProducts(clientId)) {
            throw new IllegalStateException("No se puede eliminar el cliente porque tiene productos vinculados.");
        }
        clientRepository.deleteById(clientId);
    }
}