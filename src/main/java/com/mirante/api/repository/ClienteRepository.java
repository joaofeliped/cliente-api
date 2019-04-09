package com.mirante.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mirante.api.model.Cliente;

/**
 * Repositório da entidade cliente.
 * 
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
