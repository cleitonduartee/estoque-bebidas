package com.estoqueBebidas.entities.dto;

import java.io.Serializable;
import java.time.Instant;

public class HistoricoCadastraProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String responsavel; 
	private Instant horario = Instant.now();	
	private Integer secao_id;
	private String movimentacao = "Cadastro realizado";
	
	public HistoricoCadastraProdutoDTO() {
		
	}

	public HistoricoCadastraProdutoDTO(ProdutoCadastraDTO objDto) {
		super();		
		responsavel = objDto.getResponsavel();			
		secao_id = objDto.getSecao_id();		
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public Instant getHorario() {
		return horario;
	}

	public void setHorario(Instant horario) {
		this.horario = horario;
	}

	public Integer getSecao_id() {
		return secao_id;
	}

	public void setSecao_id(Integer secao_id) {
		this.secao_id = secao_id;
	}

	public String getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(String movimentacao) {
		this.movimentacao = movimentacao;
	}

		
}
