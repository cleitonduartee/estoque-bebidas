package com.estoqueBebidas.entities.dto;

import java.util.ArrayList;
import java.util.List;

import com.estoqueBebidas.entities.Historico;
import com.estoqueBebidas.entities.Secao;

public class SecaoOutDto {

	
	private String nome;
	private Double capacidade;
	private Double volumeNoEstoque;
	
	List<HistoricoOutDTO> historicos = new ArrayList<>();
	
	public SecaoOutDto( Secao secao) {
		nome = secao.getNome();
		capacidade = secao.getCapacidade();
		volumeNoEstoque = secao.getVolumeNoEstoque();
		setHistoricos(secao.getHistoricos());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Double capacidade) {
		this.capacidade = capacidade;
	}

	public Double getVolumeNoEstoque() {
		return volumeNoEstoque;
	}

	public void setVolumeNoEstoque(Double volumeNoEstoque) {
		this.volumeNoEstoque = volumeNoEstoque;
	}

	public List<HistoricoOutDTO> getHistoricos() {
		return historicos;
	}
	private void addHistoricoOutDTO(HistoricoOutDTO historicoDto) {
		historicos.add(historicoDto);
	}

	public void setHistoricos(List<Historico> historicos) {
		for(Historico x : historicos ) {
			addHistoricoOutDTO(new HistoricoOutDTO(x));			
		}
	}
	
	
}
