package com.client.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.client.api.entity.CustomerEntity;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByCpfCnpj(@Param("cpfCnpj") final String cpfCnpj);
}