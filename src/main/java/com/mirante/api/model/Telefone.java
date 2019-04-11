package com.mirante.api.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa a tabela endereço.
 * 
 */
@Entity
@Table(name = "telefone")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Telefone {
	
	/**
	 * Código do telefone.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long codigo;
	
	/**
	 * Tipo de telefone.
	 */
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoTelefone tipo;
	
	/**
	 * Número do telefone.
	 */
	@NotNull
	private String numero;
	
	/**
	 * Cliente do telefone.
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_cliente")
	private Cliente cliente;
}
