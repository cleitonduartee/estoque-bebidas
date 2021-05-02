package com.estoqueBebidas.entities.dto;

import java.io.Serializable;
import java.util.Date;

import com.estoqueBebidas.entities.enuns.Categoria;

public class ProdutoInsertDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Categoria categoria;
	private String responsavel; 
	private Date horario = new Date();		
	private Integer secao_id;
	private Double volume;
	
	public ProdutoInsertDTO() {
		
	}

	public ProdutoInsertDTO(Integer id, String nome, Categoria categoria, Integer secao_id,String responsavel,Double volume) {
		super();
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
		this.secao_id = secao_id;
		this.setResponsavel(responsavel);
		this.setVolume(volume);
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
	
	
}
