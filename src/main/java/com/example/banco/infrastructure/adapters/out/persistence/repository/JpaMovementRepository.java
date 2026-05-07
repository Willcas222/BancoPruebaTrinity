package com.example.banco.infrastructure.adapters.out.persistence.repository;

import com.example.banco.infrastructure.adapters.out.persistence.entity.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JpaMovementRepository extends JpaRepository<MovementEntity, Long> {

    @Modifying
    @Query(value = "INSERT INTO movements (type, amount, balance_after, created_at, product_id) " +
            "VALUES (:type, :amount, :balanceAfter, :createdAt, :productId)",
            nativeQuery = true)
    void saveNative(@Param("type") String type,
                    @Param("amount") BigDecimal amount,
                    @Param("balanceAfter") BigDecimal balanceAfter,
                    @Param("createdAt") LocalDateTime createdAt,
                    @Param("productId") Long productId);

    @Query(value = "SELECT m.* FROM movements m " +
            "JOIN products p ON m.product_id = p.id " +
            "WHERE p.account_number = :accountNumber " +
            "ORDER BY m.created_at DESC",
            nativeQuery = true)
    List<MovementEntity> findAllByAccountNumberNative(@Param("accountNumber") String accountNumber);

}