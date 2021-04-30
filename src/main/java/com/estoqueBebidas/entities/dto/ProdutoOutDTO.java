package com.estoqueBebidas.entities.dto;

import java.io.Serializable;

import com.estoqueBebidas.entities.Produto;
import com.estoqueBebidas.entities.enuns.Categoria;

public class ProdutoOutDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Categoria categoria;
	private ProdutoSecaoOutDTO secao;
	
	public ProdutoOutDTO() {
		
	}

	public ProdutoOutDTO(Produto produto) {
		super();
		id = produto.getId();
		nome = produto.getNome();
		categoria = produto.getCategoria();
		secao = new ProdutoSecaoOutDTO(produto.getSecao());
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

	public ProdutoSecaoOutDTO getSecao() {
		return secao;
	}

	public void setSecao(ProdutoSecaoOutDTO secao) {
		this.secao = secao;
	}

}
