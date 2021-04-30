package com.estoqueBebidas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estoqueBebidas.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
