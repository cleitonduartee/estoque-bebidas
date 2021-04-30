package com.estoqueBebidas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estoqueBebidas.entities.Secao;
import com.estoqueBebidas.repository.SecaoRepository;
import com.estoqueBebidas.service.exception.ResourceNotFoundException;

@Service
public class SecaoService {

	@Autowired
	private SecaoRepository secaoRepo;
	
	public Secao salvarSecao(Secao secao) {
		return secaoRepo.save(secao);
	}
	public Secao buscarPorId(Integer id) {
		Optional<Secao> secao = secaoRepo.findById(id);
		return secao.orElseThrow(()-> new ResourceNotFoundException("Secão não foi encontrado. ID informado: "+id));
	}
}
