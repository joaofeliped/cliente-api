package com.mirante.api.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mirante.api.model.Telefone;
import com.mirante.api.repository.TelefoneRepository;

/**
 * Service da entidade Telefone.
 *
 */
@Service
public class TelefoneService {
	
	/**
	 * Repositório de telefones.
	 */
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	/**
	 * Método para salvar telefones.
	 * 
	 * @param telefones
	 */
	@Transactional
	public void salvar(Set<Telefone> telefones) {
		this.telefoneRepository.saveAll(telefones);
	}
}
