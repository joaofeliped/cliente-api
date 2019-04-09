package com.mirante.api.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa a tabela usuário.
 * 
 */
@Entity
@Table(name = "usuario")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario {
	
	/**
	 * Código da permissão.
	 */
	@Id
	@EqualsAndHashCode.Include
	private Long codigo;
	
	/**
	 * Nome do usuário.
	 */
	@NotBlank
	private String nome;
	
	/**
	 * Senha do usuário.
	 */
	@NotBlank
	private String senha;
	
	/**
	 * Permissões do usuário.
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_permissao", joinColumns = @JoinColumn(name = "codigo_usuario"), 
		inverseJoinColumns = @JoinColumn(name = "codigo_permissao"))
	private Set<Permissao> permissoes;
}
