package com.example.banco.infrastructure.adapters.out.persistence.repository;


import com.example.banco.infrastructure.adapters.out.persistence.entity.ClientEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaClientRepository extends JpaRepository<ClientEntity, Long> {

    @Query(value = "SELECT * FROM clientes WHERE id = :id", nativeQuery = true)
    Optional<ClientEntity> findByIdNative(@Param("id") Long id);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 'TRUE' ELSE 'FALSE' END " +
            "FROM clientes  WHERE identification_number = :number", nativeQuery = true)
    boolean existsByIdentificationNative(@Param("number") String number);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM clientes WHERE id = :id", nativeQuery = true)
    void deleteByIdNative(@Param("id") Long id);




    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 'TRUE' ELSE 'FALSE' END " +
            "FROM clientes WHERE email = :email", nativeQuery = true)
    boolean existsByEmailNative(@Param("email") String email);

}
