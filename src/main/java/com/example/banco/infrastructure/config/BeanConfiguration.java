package com.example.banco.infrastructure.config;

import com.example.banco.application.usecases.*;
import com.example.banco.domain.ports.in.*;
import com.example.banco.domain.ports.out.ClientRepositoryPort;
import com.example.banco.domain.ports.out.MovementRepositoryPort;
import com.example.banco.domain.ports.out.ProductRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CreateClientUseCase createClientUseCase(ClientRepositoryPort clientRepositoryPort) {
        return new CreateClientUseCaseImpl(clientRepositoryPort);
    }

    @Bean
    public UpdateClientUseCase updateClientUseCase(ClientRepositoryPort clientRepositoryPort) {
        return new UpdateClientUseCaseImpl(clientRepositoryPort);
    }

    @Bean
    public DeleteClientUseCase deleteClientUseCase(
            ClientRepositoryPort clientRepositoryPort,
            ProductRepositoryPort productRepositoryPort) {
        return new DeleteClientUseCaseImpl(clientRepositoryPort, productRepositoryPort);
    }

    @Bean
    public GetClientUseCase getClientUseCase(ClientRepositoryPort clientRepositoryPort) {
        return new GetClientUseCaseImpl(clientRepositoryPort);
    }

    @Bean
    public CreateProductUseCase createProductUseCase(
            ProductRepositoryPort productRepositoryPort,
            ClientRepositoryPort clientRepositoryPort) {
        return new CreateProductUseCaseImpl(productRepositoryPort, clientRepositoryPort);
    }

    @Bean
    public UpdateProductStatusUseCase updateProductStatusUseCase(ProductRepositoryPort productRepositoryPort) {
        return new UpdateProductStatusUseCaseImpl(productRepositoryPort);
    }

    @Bean
    public TransactionUseCase transactionUseCase(
            ProductRepositoryPort productRepositoryPort,
            MovementRepositoryPort movementRepositoryPort) {
        return new TransactionUseCaseImpl(productRepositoryPort, movementRepositoryPort);
    }

    @Bean
    public GetMovementsUseCase getMovementsUseCase(MovementRepositoryPort movementRepositoryPort) {
        return new GetMovementsUseCaseImpl(movementRepositoryPort);
    }

    @Bean
    public UpdateGmfExemptUseCase updateGmfExemptUseCase(ProductRepositoryPort productRepositoryPort) {
        return new UpdateGmfExemptUseCaseImpl(productRepositoryPort);
    }

    @Bean
    public DeleteProductUseCase deleteProductUseCase(ProductRepositoryPort productRepositoryPort) {
        return new DeleteProductUseCaseImpl(productRepositoryPort);
    }

}
