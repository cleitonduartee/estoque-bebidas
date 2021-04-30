package com.estoqueBebidas.entities.dto;

import java.io.Serializable;

import com.estoqueBebidas.entities.Secao;

public class ProdutoSecaoOutDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;	
	
	public ProdutoSecaoOutDTO() {
		
	}

	public ProdutoSecaoOutDTO(Secao secao) {
		super();
		id = secao.getId();
		nome = secao.getNome();		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

	
	
}
