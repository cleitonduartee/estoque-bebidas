package com.estoqueBebidas.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.estoqueBebidas.entities.enuns.Operacao;

@Entity
public class Historico implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String responsavel; 
	private Instant horario;
	private Double volume;
	private Integer operacao;
	
	@ManyToOne
	@JoinColumn(name = "secao_id")
	private Secao secao;
	
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
	
	public Historico() {
		
	}

	public Historico(Integer id, String responsavel, Instant horario, Double volume,Secao secao, Produto produto,Operacao operacao) {
		super();
		this.id = id;
		this.responsavel = responsavel;
		this.horario = horario;
		this.volume = volume;
		this.secao = secao;
		this.produto =produto;
		this.operacao = operacao != null ? operacao.getCod() : null;
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
	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}	

	public Operacao getOperacao() {
		return Operacao.convertOperacao(operacao);
	}

	public void setOperacao(Operacao operacao) {
		this.operacao = operacao.getCod();
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
