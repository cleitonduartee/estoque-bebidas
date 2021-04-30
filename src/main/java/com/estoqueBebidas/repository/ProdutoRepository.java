package com.estoqueBebidas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estoqueBebidas.entities.Historico;

public interface ProdutoRepository extends JpaRepository<Historico, Integer> {

}
