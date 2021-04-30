package com.estoqueBebidas.entities.dto;

import java.io.Serializable;

import com.estoqueBebidas.entities.enuns.Categoria;

public class ProdutoInsertDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Categoria categoria;
	private Integer secao_id;
	
	public ProdutoInsertDTO() {
		
	}

	public ProdutoInsertDTO(Integer id, String nome, Categoria categoria, Integer secao_id) {
		super();
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
		this.secao_id = secao_id;
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

	public Integer getSecao_id() {
		return secao_id;
	}

	public void setSecao_id(Integer secao_id) {
		this.secao_id = secao_id;
	}
	
	
}
