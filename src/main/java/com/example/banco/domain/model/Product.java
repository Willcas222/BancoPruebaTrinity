package com.example.banco.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {
    private Long id;
    private String type;
    private String accountNumber;
    private String status;
    private BigDecimal balance;
    private boolean gmfExempt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long clientId;


    public Product(Long id, String type, String accountNumber, String status,
                   BigDecimal balance, boolean gmfExempt, LocalDateTime createdAt,
                   LocalDateTime updatedAt, Long clientId) {

        validateType(type);

        this.id = id;
        this.type = type.toUpperCase();
        this.accountNumber = accountNumber;
        this.status = status.toUpperCase();
        this.balance = balance;
        this.gmfExempt = gmfExempt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.clientId = clientId;
    }

    public Product(String type, String accountNumber, Long clientId) {
        validateType(type);

        this.type = type.toUpperCase();
        this.accountNumber = accountNumber;
        this.clientId = clientId;
        this.balance = BigDecimal.ZERO;
        this.gmfExempt = false;
        this.createdAt = LocalDateTime.now();


        this.changeStatus("ACTIVA");
    }


    private void validateType(String type) {
        if (type == null || (!type.equalsIgnoreCase("AHORRO") && !type.equalsIgnoreCase("CORRIENTE"))) {
            throw new IllegalArgumentException("Tipo de cuenta no permitido. Debe ser AHORRO o CORRIENTE.");
        }
    }


    public void changeStatus(String newStatus) {
        if ("CANCELADA".equalsIgnoreCase(newStatus)) {
            if (this.balance != null && this.balance.compareTo(BigDecimal.ZERO) != 0) {
                throw new IllegalStateException("Solo se podrán cancelar las cuentas que tengan un saldo igual a $0.");
            }
        }


        if (!"ACTIVA".equalsIgnoreCase(newStatus) &&
                !"INACTIVA".equalsIgnoreCase(newStatus) &&
                !"CANCELADA".equalsIgnoreCase(newStatus)) {
            throw new IllegalArgumentException("Estado de cuenta no válido.");
        }

        this.status = newStatus.toUpperCase();
        this.updatedAt = LocalDateTime.now();
    }



    public void executeTransaction(BigDecimal amount) {
        BigDecimal tax = BigDecimal.ZERO;

        if (amount.compareTo(BigDecimal.ZERO) < 0 && !this.gmfExempt) {
            tax = amount.abs().multiply(new BigDecimal("0.004"));
        }

        BigDecimal totalDeduction = amount.subtract(tax);
        BigDecimal newBalance = this.balance.add(totalDeduction);


        if ("AHORRO".equalsIgnoreCase(this.type) && newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente: La transacción más el impuesto GMF (4x1000) superan el saldo disponible.");
        }

        this.balance = newBalance;
        this.updatedAt = LocalDateTime.now();
    }


    public Long getId() { return id; }
    public String getType() { return type; }
    public String getAccountNumber() { return accountNumber; }
    public String getStatus() { return status; }
    public BigDecimal getBalance() { return balance; }
    public boolean isGmfExempt() { return gmfExempt; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public Long getClientId() { return clientId; }

    public void setGmfExempt(boolean gmfExempt) {
        this.gmfExempt = gmfExempt;
        this.updatedAt = java.time.LocalDateTime.now();
    }


}