package com.estoqueBebidas.entities.dto;

import java.io.Serializable;
import java.util.Date;

import com.estoqueBebidas.entities.Historico;
import com.estoqueBebidas.entities.enuns.Operacao;
import com.fasterxml.jackson.annotation.JsonFormat;

public class HistoricoOutDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private String responsavel;	
	@JsonFormat(pattern = "HH:mm",timezone = "Brazil/East")
	private Date horario;
	private Double volume;	
	private ProdutoHistoricoOutDTO produtos;
	private Operacao operacao;
	
	
	public HistoricoOutDTO() {
		
	}
	public HistoricoOutDTO(Historico obj) {
		
		responsavel = obj.getResponsavel();
		horario = obj.getHorario();
		volume = obj.getVolume();
		produtos = new ProdutoHistoricoOutDTO(obj.getProduto());	
		setOperacao(obj.getOperacao());		
	}
	
	public String getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	public Date getHorario() {
		return horario;
	}
	public void setHorario(Date horario) {
		this.horario = horario;
	}
	public Double getVolume() {
		return volume;
	}
	public void setVolume(Double volume) {
		this.volume = volume;
	}
	public ProdutoHistoricoOutDTO getProdutos() {
		return produtos;
	}
	public void setProduto(ProdutoHistoricoOutDTO produto) {
		this.produtos = produto;
	}
	public Operacao getOperacao() {
		return operacao;
	}
	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}
	
}
