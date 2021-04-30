package com.estoqueBebidas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estoqueBebidas.entities.Historico;
import com.estoqueBebidas.entities.Produto;
import com.estoqueBebidas.entities.Secao;
import com.estoqueBebidas.entities.dto.ProdutoInsertDTO;
import com.estoqueBebidas.entities.enuns.Operacao;
import com.estoqueBebidas.repository.ProdutoRepository;
import com.estoqueBebidas.service.exception.ResourceNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired
	private SecaoService secaoService;
	
	@Autowired
	private HistoricoService historicoService;
	
	public List<Produto> buscarTodos(){
		return produtoRepo.findAll();
	}
	
	public Produto buscarPorId(Integer id) {
		Optional<Produto> produto = produtoRepo.findById(id);
		return produto.orElseThrow(()-> new ResourceNotFoundException("Produto não foi encontrado. ID informado: "+id));
	}
	
	public Produto salvaProduto(ProdutoInsertDTO objDto) {		
		if(!verificaCadastro(objDto.getNome())) {
			Secao secao =secaoService.buscarPorId(objDto.getSecao_id());
			Produto produto = new Produto(null, objDto.getNome(), objDto.getCategoria(), secao);			
			return produtoRepo.save(produto);
		}
		return null;
	}

	@Transactional
	public Produto cadastrarProduto(ProdutoInsertDTO objDto) {

		objDto.setVolume(0.0);
		Secao secao = secaoService.buscarPorId(objDto.getSecao_id());
		if(secao.verificaEspacoDisponivel(objDto.getVolume())) {
			if(verificaCadastro(objDto.getNome())) {
				Produto produto = produtoRepo.save((new Produto(null, objDto.getNome(), objDto.getCategoria(), secao)));
				Historico historico = historicoService.salvaHistorico(new Historico(null, objDto.getResponsavel(), objDto.getHorario(), objDto.getVolume(), secao, produto, Operacao.CADASTRO));
				secao.addHistorico(historico);
				secao.addProduto(produto);
				secao.addVolume(historico.getVolume());
				produto.addHistorico(historico);
				secaoService.salvarSecao(secao);
				return produtoRepo.save(produto);
			}
			throw new IllegalArgumentException("Produto já cadastrado no estoque. Nome: "+objDto.getNome());
			
		}
		throw new IllegalArgumentException("Volume informado excede o espaco disponível da Secao. Volume informado: "+objDto.getVolume());

	}
	private boolean verificaCadastro(String nome) {		
		Produto p = produtoRepo.findByNome(nome);		
		if(p == null) return false;
		return true;
	}
}
