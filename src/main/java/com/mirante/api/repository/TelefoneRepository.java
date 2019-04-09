package com.mirante.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mirante.api.model.Telefone;

/**
 * Repositório da entidade telefone.
 * 
 */
@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

}
