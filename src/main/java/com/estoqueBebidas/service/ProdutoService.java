package com.estoqueBebidas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estoqueBebidas.entities.Produto;
import com.estoqueBebidas.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepo;
	
	public Produto salvaProduto(Produto produto) {		
		if(!verificaCadastro(produto.getNome())) {
			produto = produtoRepo.save(produto);
			return produto;
		}
		return null;
	}
	private boolean verificaCadastro(String nome) {		
		Produto p = produtoRepo.findByNome(nome);		
		if(p == null) return false;
		return true;
	}
}
