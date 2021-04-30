package com.estoqueBebidas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estoqueBebidas.entities.Historico;
import com.estoqueBebidas.repository.HistoricoRepository;
import com.estoqueBebidas.service.exception.ResourceNotFoundException;
	
@Service
public class HistoricoService {

	@Autowired
	private HistoricoRepository historicoRepo;
	
	public List<Historico> buscarTodos(){
		return historicoRepo.findAll();
	}
	public Historico buscarPorId(Integer id) {
		Optional<Historico> historico = historicoRepo.findById(id);
		return historico.orElseThrow(()-> new ResourceNotFoundException("Historico não foi encontrado. ID informado: "+id));
	}
	
	@Transactional
	public Historico salvaHistorico(Historico obj) {
				
		Historico historico = historicoRepo.save(new Historico(null, obj.getResponsavel(), obj.getHorario(),
				obj.getVolume(), obj.getSecao(), obj.getProduto(),obj.getOperacao()));
		return historico;

	}	
	
}
