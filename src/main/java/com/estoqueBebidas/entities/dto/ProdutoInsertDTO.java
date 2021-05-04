package com.estoqueBebidas.entities.dto;

import java.io.Serializable;

import com.estoqueBebidas.entities.enuns.Categoria;

public class ProdutoInsertDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private String nome;
	private Categoria categoria;
	private String responsavel;			
	private Integer secao_id;
	
	
	public ProdutoInsertDTO() {
		
	}

	public ProdutoInsertDTO(String nome, Categoria categoria, Integer secao_id,String responsavel) {
		super();		
		this.nome = nome;
		this.categoria = categoria;
		this.secao_id = secao_id;
		this.setResponsavel(responsavel);		
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

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	
}
