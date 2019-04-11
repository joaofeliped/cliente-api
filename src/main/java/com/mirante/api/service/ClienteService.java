package com.mirante.api.service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mirante.api.model.Cliente;
import com.mirante.api.model.Email;
import com.mirante.api.model.Telefone;
import com.mirante.api.model.exception.CaractereInvalidoException;
import com.mirante.api.repository.ClienteRepository;

/**
 * Service da entidade Cliente.
 *
 */
@Service
public class ClienteService {
	
	/**
	 * Repositório de clientes.
	 */
	@Autowired
	private ClienteRepository clienteRepository;
	
	/**
	 * Serviço de telefones.
	 */
	@Autowired
	private TelefoneService telefoneService;
	
	/**
	 * Serviço de emails.
	 */
	@Autowired
	private EmailService emailService;
	
	/**
	 * Método para salvar cliente.
	 * 
	 * @param cliente
	 */
	@Transactional
	public void salvar(Cliente cliente) {
		validarNome(cliente);
		
		removerMascaraCpf(cliente);
		
		removerMascaraCep(cliente);
		
		final Cliente clienteSalvo = this.clienteRepository.save(cliente);
		
		Set<Telefone> telefones = cliente.getTelefones().stream()
			.map(t -> {
				t.setCliente(clienteSalvo);
				return t;
			}).collect(Collectors.toSet());
		
		Set<Email> emails = cliente.getEmails().stream()
				.map(e -> {
					e.setCliente(clienteSalvo);
					return e;
				}).collect(Collectors.toSet());
		
		
		this.telefoneService.salvar(telefones);
		this.emailService.salvar(emails);
	}
	
	/**
	 * Método para atualizar cliente.
	 * 
	 * @param cliente
	 */
	@Transactional
	public void atualizar(Cliente cliente) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Método para deletar um cliente.
	 * 
	 * @param cliente
	 */
	@Transactional
	public void deletar(Long codigo) {
		Cliente clienteCadastrado = buscarPorCodigo(codigo);
		
		this.clienteRepository.delete(clienteCadastrado);
	}
	
	/**
	 * Busca o cliente pelo código.
	 * 
	 * @param codigo
	 * @return Cliente
	 */
	public Cliente buscarPorCodigo(Long codigo) {
		Cliente cliente = this.clienteRepository.findById(codigo).orElse(null);
		
		if(Objects.isNull(cliente)) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return cliente;
	}
	
	/**
	 * Busca todos os clientes.
	 * 
	 * @return List<Cliente>
	 */
	public List<Cliente> buscarTodos() {
		return this.clienteRepository.findAll();
	}
	
	/**
	 * Remove a mascara de cpf.
	 * 
	 * @param cliente
	 */
	private void removerMascaraCpf(Cliente cliente) {
		cliente.setCpf(cliente.getCpf().replaceAll("[.-]", ""));
	}
	
	/**
	 * Remove a mascara de cep.
	 * 
	 * @param cliente
	 */
	private void removerMascaraCep(Cliente cliente) {
		cliente.getEndereco().setCep(cliente.getEndereco().getCep().replaceAll("[.-]", ""));
	}
	
	/**
	 * Valida se o nome possui caractere inválido.
	 * 
	 * @param cliente
	 */
	private void validarNome(Cliente cliente) {
		if(!cliente.getNome().trim().matches("[\\sa-zA-Z0-9]+")) {
			throw new CaractereInvalidoException();
		}
	}
}
