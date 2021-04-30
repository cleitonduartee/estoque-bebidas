package com.estoqueBebidas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estoqueBebidas.entities.Produto;
import com.estoqueBebidas.entities.Secao;
import com.estoqueBebidas.entities.dto.ProdutoInsertDTO;
import com.estoqueBebidas.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired
	private SecaoService secaoService;
	
	public Produto salvaProduto(ProdutoInsertDTO objtDto) {		
		if(!verificaCadastro(objtDto.getNome())) {
			Secao secao =secaoService.buscarPorId(objtDto.getSecao_id());
			Produto produto = new Produto(null, objtDto.getNome(), objtDto.getCategoria(), secao);
			return produtoRepo.save(produto);
		}
		return null;
	}
	private boolean verificaCadastro(String nome) {		
		Produto p = produtoRepo.findByNome(nome);		
		if(p == null) return false;
		return true;
	}
}
