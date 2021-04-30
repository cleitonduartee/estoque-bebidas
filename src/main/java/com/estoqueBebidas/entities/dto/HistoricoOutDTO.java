package com.estoqueBebidas.entities.dto;

import java.io.Serializable;
import java.time.Instant;

import com.estoqueBebidas.entities.Historico;

public class HistoricoOutDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String responsavel; 
	private Instant horario;
	private Double volume;	
	private ProdutoOutDTO produto;
	
	public HistoricoOutDTO() {
		
	}
	public HistoricoOutDTO(Historico obj) {
		id = obj.getId();
		responsavel = obj.getResponsavel();
		horario = obj.getHorario();
		volume = obj.getVolume();
		produto = new ProdutoOutDTO(obj.getProduto());		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Double getVolume() {
		return volume;
	}
	public void setVolume(Double volume) {
		this.volume = volume;
	}
	public ProdutoOutDTO getProduto() {
		return produto;
	}
	public void setProduto(ProdutoOutDTO produto) {
		this.produto = produto;
	}
	
}
