package com.estoqueBebidas.entities.dto;

import java.io.Serializable;

import com.estoqueBebidas.entities.Produto;
import com.estoqueBebidas.entities.enuns.Categoria;

public class ProdutoHistoricoOutDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Categoria categoria;
	
	
	public ProdutoHistoricoOutDTO() {
		
	}

	public ProdutoHistoricoOutDTO(Produto produto) {
		super();
		id = produto.getId();
		nome = produto.getNome();
		categoria = produto.getCategoria();		
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
