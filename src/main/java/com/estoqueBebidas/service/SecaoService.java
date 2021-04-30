package com.estoqueBebidas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estoqueBebidas.entities.Secao;
import com.estoqueBebidas.repository.SecaoRepository;

@Service
public class SecaoService {

	@Autowired
	private SecaoRepository secaoRepo;
	
	public Secao salvarSecao(Secao secao) {
		return secaoRepo.save(secao);
	}
}
