package com.estoqueBebidas.service;

import java.util.List;
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
		return secao.orElseThrow(() -> new ResourceNotFoundException("Secão não foi encontrado. ID informado: " + id));
	}

	public List<Secao> buscarTodos() {
		return secaoRepo.findAll();
	}

	public Double verificaValor(String strValor) {
		try {
			Double valor = Double.parseDouble(strValor);
			return valor;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(
					"Valor informado no parâmetro não é um número. Valor informado: " + strValor);
		}

	}
}
