package com.example.banco.application.usecases;

import com.example.banco.domain.model.Product;
import com.example.banco.domain.ports.in.UpdateGmfExemptUseCase;
import com.example.banco.domain.ports.out.ProductRepositoryPort;

public class UpdateGmfExemptUseCaseImpl implements UpdateGmfExemptUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public UpdateGmfExemptUseCaseImpl(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Product execute(String accountNumber, boolean exempt) {
        Product product = productRepositoryPort.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("La cuenta no existe."));

        if ("CANCELADA".equalsIgnoreCase(product.getStatus())) {
            throw new IllegalStateException("No se puede modificar la exención de una cuenta CANCELADA.");
        }

        if (exempt) {

            boolean alreadyHasExempt = productRepositoryPort.existsByClientIdAndGmfExemptTrue(product.getClientId());
            if (alreadyHasExempt) {
                throw new IllegalStateException("El cliente ya tiene una cuenta exenta. Debe desactivar la anterior primero.");
            }
        }


        product.setGmfExempt(exempt);
        return productRepositoryPort.save(product);
    }
}