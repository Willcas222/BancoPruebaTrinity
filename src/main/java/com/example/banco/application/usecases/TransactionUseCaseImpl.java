package com.example.banco.application.usecases;

import com.example.banco.domain.model.Product;
import com.example.banco.domain.ports.in.TransactionUseCase;
import com.example.banco.domain.ports.out.ProductRepositoryPort;
import com.example.banco.domain.ports.out.MovementRepositoryPort;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

public class TransactionUseCaseImpl implements TransactionUseCase {

    private final ProductRepositoryPort productRepository;
    private final MovementRepositoryPort movementRepository;

    public TransactionUseCaseImpl(ProductRepositoryPort productRepository,
                                  MovementRepositoryPort movementRepository) {
        this.productRepository = productRepository;
        this.movementRepository = movementRepository;
    }

    @Override
    @Transactional
    public void deposit(String accountNumber, BigDecimal amount) {
        Product product = getActiveProduct(accountNumber);
        product.executeTransaction(amount);

        productRepository.save(product);
        movementRepository.register(product, "CONSIGNACION", amount);
    }

    @Override
    @Transactional
    public void withdraw(String accountNumber, BigDecimal amount) {
        processOutboundTransaction(accountNumber, amount.negate(), "RETIRO");
    }

    @Override
    @Transactional
    public void transfer(String originAccount, String destinationAccount, BigDecimal amount) {
        if (originAccount.equals(destinationAccount)) {
            throw new IllegalArgumentException("La cuenta origen y destino no pueden ser la misma.");
        }
        processOutboundTransaction(originAccount, amount.negate(), "TRANSFERENCIA_SALIDA");
        Product destination = getActiveProduct(destinationAccount);
        destination.executeTransaction(amount);
        productRepository.save(destination);
        movementRepository.register(destination, "TRANSFERENCIA_ENTRADA", amount);
    }


    private void processOutboundTransaction(String accountNumber, BigDecimal amount, String type) {
        Product product = getActiveProduct(accountNumber);
        BigDecimal balanceBefore = product.getBalance();
        product.executeTransaction(amount);
        productRepository.save(product);
        movementRepository.register(product, type, amount);
        BigDecimal expectedBalanceWithoutTax = balanceBefore.add(amount);
        BigDecimal taxAmount = expectedBalanceWithoutTax.subtract(product.getBalance());


        if (taxAmount.compareTo(BigDecimal.ZERO) > 0) {
            movementRepository.register(product, "IMPUESTO_GMF_4X1000", taxAmount.negate());
        }
    }

    private Product getActiveProduct(String accountNumber) {
        Product product = productRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("La cuenta " + accountNumber + " no existe."));
        if ("CANCELADA".equalsIgnoreCase(product.getStatus())) {
            throw new IllegalStateException("No se pueden realizar transacciones sobre una cuenta CANCELADA.");
        }
        return product;
    }
}