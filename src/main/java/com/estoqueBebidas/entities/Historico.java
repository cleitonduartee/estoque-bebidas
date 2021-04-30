package com.estoqueBebidas.entities;

import java.io.Serializable;
import java.time.Instant;

public class Historico implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String responsavel; 
	private Instant horario;
	private Double volume;
	
	public Historico() {
		
	}

	public Historico(Integer id, String responsavel, Instant horario, Double volume) {
		super();
		this.id = id;
		this.responsavel = responsavel;
		this.horario = horario;
		this.volume = volume;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Historico other = (Historico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}