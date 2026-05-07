package com.example.banco.domain.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Movement {
    private Long id;
    private String type;
    private BigDecimal amount;
    private BigDecimal balanceAfter;
    private LocalDateTime createdAt;
    private Long productId;


    public Movement(Long id, String type, BigDecimal amount, BigDecimal balanceAfter,
                    LocalDateTime createdAt, Long productId) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.createdAt = createdAt;
        this.productId = productId;
    }


    public Long getId() { return id; }
    public String getType() { return type; }
    public BigDecimal getAmount() { return amount; }
    public BigDecimal getBalanceAfter() { return balanceAfter; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Long getProductId() { return productId; }
}
