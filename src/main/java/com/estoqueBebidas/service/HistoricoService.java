package com.estoqueBebidas.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estoqueBebidas.entities.Historico;
import com.estoqueBebidas.entities.Secao;
import com.estoqueBebidas.entities.enuns.Categoria;
import com.estoqueBebidas.repository.HistoricoRepository;
import com.estoqueBebidas.service.exception.ResourceNotFoundException;

@Service
public class HistoricoService {

	@Autowired
	private HistoricoRepository historicoRepo;

	@Autowired
	private SecaoService secaoService;

	public Page<Historico> buscarTodos(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
		return historicoRepo.findAll(pageRequest);
	}

	public Historico buscarPorId(Integer id) {
		Optional<Historico> historico = historicoRepo.findById(id);
		return historico
				.orElseThrow(() -> new ResourceNotFoundException("Historico não foi encontrado. ID informado: " + id));
	}

	public Secao buscarPorCategoriaESecao(String StrCategoria, String StrHistorico) {
		try {
			Categoria categoria = converteCategoria(StrCategoria.toUpperCase());
			Secao secao = secaoService.buscarPorNome(StrHistorico.toUpperCase());
			verificaSeIgualdadeDeCategoria(categoria, secao);
			secao.getHistoricos().stream().filter(x -> (x.getSecao() == secao && x.getSecao().getCategoria().getCod() == categoria.getCod()))
			.collect(Collectors.toList());
			
			return secao;
		} catch (Exception e) {			
			throw new ResourceNotFoundException(e.getMessage());
		}
	}

	@Transactional
	public Historico salvaHistorico(Historico obj) {

		return historicoRepo.save(new Historico(null, obj.getResponsavel(), obj.getHorario(), obj.getVolume(),
				obj.getSecao(), obj.getProduto(), obj.getOperacao()));

	}

	private Categoria converteCategoria(String categoria) {
		try {
			return Categoria.valueOf(categoria);

		} catch (IllegalArgumentException e) {
			
			throw new ResourceNotFoundException(
					 "Parâmetro informado na busca da Categoria não foi encontrado. NOME INFORMADO: " + categoria + ". Tipos de Categorias: "+Categoria.tiposDeCategorias());
		}

	}
	private void verificaSeIgualdadeDeCategoria(Categoria categoria, Secao secao) {

		if (categoria.getCod() != secao.getCategoria().getCod()) {
			throw new IllegalArgumentException(
					"A categoria informada no parâmetro não é a mesma categoria da secao. Categoria: "
							+ categoria+ ", categoria da secao: " + secao.getCategoria());

		}
	}	

}
