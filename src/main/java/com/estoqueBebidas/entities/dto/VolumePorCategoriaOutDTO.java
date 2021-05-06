package com.estoqueBebidas.entities.dto;

import java.io.Serializable;

import com.estoqueBebidas.entities.enuns.Categoria;

public class VolumePorCategoriaOutDTO  implements Serializable {
	private static final long serialVersionUID = 1L;

	private Categoria categoria;	
	private Double totalPorCategoria;
	
		
	public VolumePorCategoriaOutDTO() {
		
	}
	
	public VolumePorCategoriaOutDTO(Integer categoria, Double volume) {
		this.categoria = Categoria.convertCategoria(categoria);		
		this.totalPorCategoria = volume;
		
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Double getTotalPorCategoria() {
		return totalPorCategoria;
	}

	public void setTotalPorCategoria(Double totalPorCategoria) {
		this.totalPorCategoria = totalPorCategoria;
	}
	
}
