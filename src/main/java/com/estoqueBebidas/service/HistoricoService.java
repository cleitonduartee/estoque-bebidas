package com.estoqueBebidas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estoqueBebidas.entities.Historico;
import com.estoqueBebidas.entities.Secao;
import com.estoqueBebidas.repository.HistoricoRepository;
import com.estoqueBebidas.repository.SecaoRepository;
	
@Service
public class HistoricoService {

	@Autowired
	private HistoricoRepository historicoRepo;
	
	@Autowired
	private SecaoRepository secaoRepo;
	
	public Historico salvaHistorico(Historico historico) {
		Optional<Secao> secao = secaoRepo.findById(historico.getSecao().getId());
		
		if(secao.get().verificaEspacoDisponivel(historico.getVolume())) {
			historico = historicoRepo.save(historico);
			secao.get().addHistorico(historico);
			secaoRepo.save(secao.get());
		}
		return historico;
	}
	
}
