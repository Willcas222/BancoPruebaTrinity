package com.example.banco.application.usecases;

import com.example.banco.domain.model.Product;
import com.example.banco.domain.ports.in.CreateProductUseCase;
import com.example.banco.domain.ports.out.ClientRepositoryPort;
import com.example.banco.domain.ports.out.ProductRepositoryPort;

import java.util.Random;

public class CreateProductUseCaseImpl implements CreateProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;
    private final ClientRepositoryPort clientRepositoryPort;

    public CreateProductUseCaseImpl(ProductRepositoryPort productRepositoryPort,
                                    ClientRepositoryPort clientRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
        this.clientRepositoryPort = clientRepositoryPort;
    }

    @Override
    public Product execute(Long clientId, String type) {

        if (!clientRepositoryPort.existsById(clientId)) {
            throw new IllegalArgumentException("No se puede crear el producto: El cliente con ID " + clientId + " no existe.");
        }

        String accountNumber = generateAccountNumber(type);
        while (productRepositoryPort.existsByAccountNumber(accountNumber)) {
            accountNumber = generateAccountNumber(type);
        }

        Product product = new Product(type, accountNumber, clientId);
        return productRepositoryPort.save(product);
    }

    private String generateAccountNumber(String type) {
        String prefix = type.equalsIgnoreCase("AHORRO") ? "53" : "33";
        Random random = new Random();
        long number = (long) (random.nextDouble() * 100_000_000L);
        return prefix + String.format("%08d", number);
    }




    private void validateSingleExemptAccount(Long clientId) {
        boolean alreadyHasExemptAccount = productRepositoryPort.existsByClientIdAndGmfExemptTrue(clientId);

        if (alreadyHasExemptAccount) {
            throw new IllegalStateException("El cliente ya posee una cuenta marcada como exenta de GMF. " +
                    "Solo se permite una cuenta exenta por cliente.");
        }
    }
}
