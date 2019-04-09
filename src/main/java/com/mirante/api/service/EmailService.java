package com.mirante.api.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mirante.api.model.Email;
import com.mirante.api.repository.EmailRepository;

/**
 * Service da entidade Email.
 *
 */
@Service
public class EmailService {
	
	/**
	 * Repositório de emails.
	 */
	@Autowired
	private EmailRepository emailRepository;
	
	/**
	 * Método para salvar emails.
	 * 
	 * @param emails
	 */
	@Transactional
	public void salvar(Set<Email> emails) {
		this.emailRepository.saveAll(emails);
	}
}
