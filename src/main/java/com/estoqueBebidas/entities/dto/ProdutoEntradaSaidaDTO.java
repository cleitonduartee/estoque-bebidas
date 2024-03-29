package com.estoqueBebidas.entities.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class ProdutoEntradaSaidaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer produto_id;	
	
	@NotEmpty(message = "Preenchimento obrigatório")	
	@Length(min=3, message = "O nome deve conter ao menos 3 caracteres")
	private String responsavel;		
	
	private Integer secao_id;
	
	private Double volume;
	
	public ProdutoEntradaSaidaDTO() {
		
	}

	public ProdutoEntradaSaidaDTO(Integer produto_id,Integer secao_id,String responsavel,Double volume) {
		super();
		this.produto_id = produto_id;		
		this.secao_id = secao_id;
		this.setResponsavel(responsavel);
		this.setVolume(volume);
	}

	public Integer getProduto_id() {
		return produto_id;
	}

	public void setProduto_id(Integer produto_id) {
		this.produto_id = produto_id;
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

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}
	
	
}
