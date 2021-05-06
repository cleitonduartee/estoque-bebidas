package com.estoqueBebidas.entities.dto;

import java.io.Serializable;

import com.estoqueBebidas.entities.Produto;
import com.estoqueBebidas.entities.enuns.Categoria;

public class ProdutoHistoricoOutDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private String nome;
	private Categoria categoria;
	
	
	public ProdutoHistoricoOutDTO() {
		
	}

	public ProdutoHistoricoOutDTO(Produto produto) {
		super();
		
		nome = produto.getNome();
		categoria = produto.getCategoria();		
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
