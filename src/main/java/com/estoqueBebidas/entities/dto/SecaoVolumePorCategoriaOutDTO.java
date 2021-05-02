package com.estoqueBebidas.entities.dto;

import java.io.Serializable;

import com.estoqueBebidas.entities.Secao;

public class SecaoVolumePorCategoriaOutDTO  implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;	
	private Double volumeNoEstoque ;
	
		
	public SecaoVolumePorCategoriaOutDTO (Secao obj) {
		id = obj.getId();
		nome = obj.getNome();		
		volumeNoEstoque = obj.getVolumeNoEstoque();		
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


	public Double getVolumeNoEstoque() {
		return volumeNoEstoque;
	}

	public void setVolumeNoEstoque(Double volumeNoEstoque) {
		this.volumeNoEstoque = volumeNoEstoque;
	}

	
		
}
