package com.estoqueBebidas.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.estoqueBebidas.entities.enuns.Categoria;

@Entity
public class Secao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double capacidade;
	private Double totalArmazenado;
	
	private Integer categoria;
	@OneToMany(mappedBy = "secao")
	private List<Historico> historicos = new ArrayList<>();
	@OneToMany(mappedBy = "secao")
	private List<Produto> produtos = new ArrayList<>();
	
	public Secao() {
		
	}

	public Secao(Integer id, String nome, Categoria categoria) {
		super();
		this.id = id;
		this.nome = nome;
		this.categoria = categoria != null ? categoria.getCod():null;
		informaCapacidade();
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

	public Double getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Double capacidade) {
		this.capacidade = capacidade;
	}

	public Double getTotalArmazenado() {
		return totalArmazenado;
	}

	public void setTotalArmazenado(Double totalArmazenado) {
		this.totalArmazenado = totalArmazenado;
	}

	public Categoria getCategoria() {
		return Categoria.convertCategoria(categoria);
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria.getCod();
	}
	private void informaCapacidade() {
		capacidade = getCategoria().getLimit();
	}
	public void addHistorico(Historico historico) {
		historicos.add(historico);
	}
	public void addProduto(Produto produto) {
		produtos.add(produto);
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
		Secao other = (Secao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
