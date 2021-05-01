package com.estoqueBebidas.entities.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.estoqueBebidas.entities.Secao;
import com.estoqueBebidas.entities.enuns.Categoria;

public class SecaoVolumePorTipoOutDTO  implements Serializable {
	private static final long serialVersionUID = 1L;

	private Categoria categoria;
	private Double total = 0.0;
	private List<SecaoOutDTO> secoes = new ArrayList<>();
		
	public SecaoVolumePorTipoOutDTO(Categoria categoria) {
		this.categoria = categoria;		
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	public Double getTotal() {
		return total;
	}
	public void addTotal(Double totalPorTipo) {
		total += totalPorTipo;
	}
	public List<SecaoOutDTO> getSecoes() {
		return secoes;
	}

	public void addSecao(Secao secaos) {
		secoes.add(new SecaoOutDTO(secaos));
	}



	
		
}
