package com.example.banco.infrastructure.adapters.out.persistence.repository;

import com.example.banco.infrastructure.adapters.out.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query(value = "SELECT COUNT(*) > 0 FROM products WHERE account_number = :accountNumber", nativeQuery = true)
    boolean existsByAccountNumberNative(@Param("accountNumber") String accountNumber);

    @Query(value = "SELECT COUNT(*) FROM products WHERE client_id = :clientId", nativeQuery = true)
    int countByClientIdNative(@Param("clientId") Long clientId);

    @Query(value = "SELECT * FROM products WHERE account_number = :accountNumber", nativeQuery = true)
    Optional<ProductEntity> findByAccountNumberNative(@Param("accountNumber") String accountNumber);

    @Query(value = "SELECT EXISTS(SELECT 1 FROM products WHERE client_id = :clientId AND gmf_exempt = true)",
            nativeQuery = true)
    boolean existsGmfExemptByClientIdNative(@Param("clientId") Long clientId);

}
