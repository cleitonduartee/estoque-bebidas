package com.estoqueBebidas.entities.dto;

import com.estoqueBebidas.entities.Historico;

public class SecaoHistoricoOutDto {

	private Integer id;
	private String nome;
	private Double capacidade;	
	private ProdutoHistoricoOutDTO produto;
	
	
	public SecaoHistoricoOutDto( Historico obj) {
		id = obj.getSecao().getId();
		nome = obj.getSecao().getNome();
		capacidade = obj.getSecao().getCapacidade();
		setProduto(new ProdutoHistoricoOutDTO( obj.getProduto()));
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Double getCapacidade() {
		return capacidade;
	}


	public void setCapacidade(Double capacidade) {
		this.capacidade = capacidade;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public ProdutoHistoricoOutDTO getProduto() {
		return produto;
	}


	public void setProduto(ProdutoHistoricoOutDTO produto) {
		this.produto = produto;
	}

}
