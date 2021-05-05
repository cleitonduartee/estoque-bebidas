package com.estoqueBebidas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estoqueBebidas.entities.Secao;
import com.estoqueBebidas.entities.enuns.Categoria;
import com.estoqueBebidas.repository.SecaoRepository;
import com.estoqueBebidas.service.exception.ResourceNotFoundException;

@Service
public class SecaoService {

	@Autowired
	private SecaoRepository secaoRepo;

	public Secao salvarSecao(Secao secao) {
		secao.setNome(secao.getNome().toUpperCase());
		return secaoRepo.save(secao);
	}

	public Secao buscarPorId(Integer id) {
		Optional<Secao> secao = secaoRepo.findById(id);
		System.out.println("Chegou secao: "+id);
		return secao.orElseThrow(() -> new ResourceNotFoundException("Secão não foi encontrado. ID informado: " + id));
	}

	public Secao buscarPorNome(String nome) {

		Secao secao = secaoRepo.findByNome(nome);
		if (secao == null) {
			List<Secao> list = secaoRepo.findAll();
			String buffer = "";
			for (Secao x: list) {
				buffer += x.getNome()+" ";
			}
			throw new ResourceNotFoundException(
					"Parâmetro informado na busca da Secão não foi encontrado. NOME INFORMADO: " + nome+". Secões cadastradas: "+buffer);
		}
		return secao;

	}

	public List<Secao> buscarTodos() {
		return secaoRepo.findAll();
	}

	public Double convertDouble(String strValor) {
		try {
			Double valor = Double.parseDouble(strValor);
			return valor;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(
					"Valor informado no parâmetro não é um número. Valor informado: " + strValor);
		}

	}

	public Categoria converteCategoria(String strValor) {
		try {
			return Categoria.valueOf(strValor.toUpperCase());
		} catch (IllegalArgumentException e) {
			String buffer = "";
			for(Categoria x: Categoria.values()) {
				buffer += x+" ";
			}
			throw new IllegalArgumentException(
					"Valor informado no parâmetro não é uma Categoria. Valor informado: " + strValor+". Valores aceito: "+buffer);
		}

	}
}
