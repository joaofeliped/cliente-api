package com.mirante.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa a tabela endereço.
 * 
 */
@Entity
@Table(name = "endereco")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Endereco {
	
	/**
	 * Código do endereço.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long codigo;
	
	/**
	 * CEP do endereço.
	 */
	@NotBlank
	private String cep;
	
	/**
	 * Logradouro.
	 */
	@NotBlank
	private String logradouro;
	
	/**
	 * Bairro.
	 */
	@NotBlank
	private String bairro;
	
	/**
	 * Cidade.
	 */
	@NotBlank
	private String cidade;
	
	/**
	 * UF.
	 */
	@NotBlank
	private String uf;
	
	/**
	 * Complemento.
	 */
	private String complemento;

}
