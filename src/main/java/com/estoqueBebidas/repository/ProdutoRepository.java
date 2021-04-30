package com.estoqueBebidas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estoqueBebidas.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	Produto findByNome(String nome);
	List<Produto> findByCategoria(Integer cat);
}
