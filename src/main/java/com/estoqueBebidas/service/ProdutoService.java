package com.estoqueBebidas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estoqueBebidas.entities.Historico;
import com.estoqueBebidas.entities.Produto;
import com.estoqueBebidas.entities.Secao;
import com.estoqueBebidas.entities.dto.ProdutoEntradaSaidaDTO;
import com.estoqueBebidas.entities.dto.ProdutoInsertDTO;
import com.estoqueBebidas.entities.enuns.Operacao;
import com.estoqueBebidas.repository.ProdutoRepository;
import com.estoqueBebidas.service.exception.LimitSecaoException;
import com.estoqueBebidas.service.exception.ProductAlreadyRegisteredtException;
import com.estoqueBebidas.service.exception.ResourceNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepo;

	@Autowired
	private SecaoService secaoService;

	@Autowired
	private HistoricoService historicoService;

	public List<Produto> buscarTodos() {
		return produtoRepo.findAll();
	}

	public Produto buscarPorId(Integer id) {
		Optional<Produto> produto = produtoRepo.findById(id);
		return produto
				.orElseThrow(() -> new ResourceNotFoundException("Produto não foi encontrado. ID informado: " + id));
	}

	@Transactional
	public Produto cadastrarProduto(ProdutoInsertDTO objDto) {

		try {
			objDto.setVolume(0.0);
			Secao secao = secaoService.buscarPorId(objDto.getSecao_id());
			if (!(secao.verificaEspacoDisponivel() >= objDto.getVolume())) {
				throw new LimitSecaoException("Volume informado excede o espaco disponível da Secao. Volume informado: "
						+ objDto.getVolume() + ", volume disponível na secão: " + secao.verificaEspacoDisponivel());

			}
			if (!produtoNaoCadastrado(objDto.getNome())) {
				throw new ProductAlreadyRegisteredtException("Produto já cadastrado no banco de dados. Nome: " + objDto.getNome());
			}
			Produto produto = produtoRepo.save((new Produto(null, objDto.getNome(), objDto.getCategoria(), secao)));
			Historico historico = historicoService.salvaHistorico(new Historico(null, objDto.getResponsavel(),
					objDto.getHorario(), objDto.getVolume(), secao, produto, Operacao.CADASTRO));
			secao.addHistorico(historico);
			secao.addProduto(produto);
			secao.addVolume(historico.getVolume());
			produto.addHistorico(historico);
			secaoService.salvarSecao(secao);
			return produtoRepo.save(produto);

		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	public Produto entradaDeProduto(ProdutoEntradaSaidaDTO objDto) {

		try {
			Secao secao = secaoService.buscarPorId(objDto.getSecao_id());
			if (!(secao.verificaEspacoDisponivel() >= objDto.getVolume())) {
				throw new LimitSecaoException("Volume informado excede o espaco disponível da Secao. Volume informado: "
						+ objDto.getVolume() + ", volume disponível na secão: " + secao.verificaEspacoDisponivel());

			}
			Produto produto = buscarPorId(objDto.getProduto_id());

			Historico historico = historicoService.salvaHistorico(new Historico(null, objDto.getResponsavel(),
					objDto.getHorario(), objDto.getVolume(), secao, produto, Operacao.COMPRA));
			secao.addHistorico(historico);
			secao.addVolume(historico.getVolume());
			produto.addHistorico(historico);
			secaoService.salvarSecao(secao);
			return produtoRepo.save(produto);

		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Transactional
	public Produto saidaDeProduto(ProdutoEntradaSaidaDTO objDto) {

		try {
			Secao secao = secaoService.buscarPorId(objDto.getSecao_id());
			if (!(secao.verificaEspacoDisponivel() >= objDto.getVolume())) {
				throw new LimitSecaoException(
						"Volume informado é maior que o espaco disponível da Secao. Volume informado: "
								+ objDto.getVolume() + ", volume disponível na secão: "
								+ secao.verificaEspacoDisponivel());

			}

			Produto produto = buscarPorId(objDto.getProduto_id());
			Historico historico = historicoService.salvaHistorico(new Historico(null, objDto.getResponsavel(),
					objDto.getHorario(), objDto.getVolume(), secao, produto, Operacao.CADASTRO));
			secao.addHistorico(historico);
			secao.removeVolume(historico.getVolume());
			produto.addHistorico(historico);
			secaoService.salvarSecao(secao);
			return produtoRepo.save(produto);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private boolean produtoNaoCadastrado(String nome) {
		Produto p = produtoRepo.findByNome(nome);
		if (p == null)
			return true;
		return false;
	}
}
