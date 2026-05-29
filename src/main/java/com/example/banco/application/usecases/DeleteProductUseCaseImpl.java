package com.example.banco.application.usecases;

import com.example.banco.domain.model.Product;
import com.example.banco.domain.ports.in.DeleteProductUseCase;
import com.example.banco.domain.ports.out.ProductRepositoryPort;

import java.math.BigDecimal;

public class DeleteProductUseCaseImpl implements DeleteProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public DeleteProductUseCaseImpl(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public void execute(Long productId) {

        Product product = productRepositoryPort.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + productId));


        if (product.getBalance() != null && product.getBalance().compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalArgumentException("No se puede eliminar el producto. La cuenta debe estar en cero y actualmente tiene un saldo de: " + product.getBalance());
        }

        productRepositoryPort.deleteById(productId);
    }
}
