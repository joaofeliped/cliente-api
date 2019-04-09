package com.mirante.api.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa a tabela cliente.
 * 
 */
@Entity
@Table(name = "cliente")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cliente {
	
	/**
	 * Código do cliente.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long codigo;
	
	/**
	 * Nome do cliente.
	 */
	@NotBlank
	@Size(min = 3, max = 100)
	private String nome;
	
	/**
	 * Cpf do cliente.
	 */
	@CPF
	@NotBlank
	@Column(length = 11)
	private String cpf;
	
	/**
	 * Endereço do cliente.
	 */
	@Valid
	@NotNull
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "codigo_endereco")
	private Endereco endereco;
	
	/**
	 * Lista de telefones.
	 */
	@Valid
	@NotEmpty(message = "Informe pelo menos um telefone.")
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private Set<Telefone> telefones;
	
	/**
	 * Lista de emails.
	 */
	@Valid
	@NotEmpty(message = "Informe pelo menos um e-mail.")
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private Set<Email> emails;
}
