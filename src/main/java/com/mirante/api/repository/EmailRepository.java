package com.mirante.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mirante.api.model.Email;

/**
 * Repositório da entidade email.
 * 
 */
@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

}
