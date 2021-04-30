package com.estoqueBebidas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estoqueBebidas.entities.Historico;
import com.estoqueBebidas.entities.Produto;
import com.estoqueBebidas.entities.Secao;
import com.estoqueBebidas.entities.dto.HistoricoInsertDTO;
import com.estoqueBebidas.repository.HistoricoRepository;
	
@Service
public class HistoricoService {

	@Autowired
	private HistoricoRepository historicoRepo;
	
	@Autowired
	private SecaoService secaoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	public List<Historico> buscarTodos(){
		return historicoRepo.findAll();
	}
	public Historico buscarPorId(Integer id) {
		Optional<Historico> historico = historicoRepo.findById(id);
		return historico.orElse(null);
	}
	
	@Transactional
	public Historico salvaHistorico(HistoricoInsertDTO objDto) {
		Secao secao = secaoService.buscarPorId(objDto.getSecao_id());
		
		if(secao.verificaEspacoDisponivel(objDto.getVolume())) {
			Produto produto = produtoService.buscarPorId(objDto.getProduto_id());			
			Historico historico =historicoRepo.save(new Historico(null, objDto.getResponsavel(), objDto.getHorario(), objDto.getVolume(), secao, produto));							
			secao.addHistorico(historico);
			secao.addProduto(produto);
			secao.addVolume(historico.getVolume());
			secaoService.salvarSecao(secao);
			return historico;
		}
		return null;
	}
	
}
