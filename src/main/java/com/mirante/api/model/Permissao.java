package com.mirante.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade de permissões do sistema. 
 */
@Entity
@Table(name = "permissao")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Permissao {
	
	/**
	 * Código da permissão.
	 */
	@Id
	@EqualsAndHashCode.Include
	private Long codigo;
	
	/**
	 * Descrição da permissão.
	 */
	@NotBlank
	private String descricao;
	
}
