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
		Secao secao1 = secao.get();
		if(secao1.verificaEspacoDisponivel(historico.getVolume())) {
			historico = historicoRepo.save(historico);	
			//System.out.println(secao1.getHistoricos());
//			secao1.addHistorico(historico);
//			secaoRepo.save(secao1);
		}
		return historico;
	}
	
}
