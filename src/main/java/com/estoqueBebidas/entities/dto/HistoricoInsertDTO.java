package com.estoqueBebidas.entities.dto;

import java.io.Serializable;
import java.time.Instant;

public class HistoricoInsertDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String responsavel; 
	private Instant horario = Instant.now();
	private Double volume;
	private Integer secao_id;
	private Integer produto_id;
	
	public HistoricoInsertDTO() {
		
	}

	public HistoricoInsertDTO(Integer id, String responsavel, Double volume, Integer secao_id,
			Integer produto_id) {
		super();
		this.id = id;
		this.responsavel = responsavel;		
		this.volume = volume;
		this.secao_id = secao_id;
		this.produto_id = produto_id;
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

	public Integer getSecao_id() {
		return secao_id;
	}

	public void setSecao_id(Integer secao_id) {
		this.secao_id = secao_id;
	}

	public Integer getProduto_id() {
		return produto_id;
	}

	public void setProduto_id(Integer produto_id) {
		this.produto_id = produto_id;
	}
		
}
