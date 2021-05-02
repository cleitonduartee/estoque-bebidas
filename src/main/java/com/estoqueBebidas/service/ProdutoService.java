package com.estoqueBebidas.service;

import java.text.SimpleDateFormat;
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
import com.estoqueBebidas.entities.enuns.Categoria;
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
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

	public Produto buscarPorId(Integer id) {
		Optional<Produto> produto = produtoRepo.findById(id);
		return produto
				.orElseThrow(() -> new ResourceNotFoundException("Produto não foi encontrado. ID informado: " + id));
	}

	public List<Produto> buscarPorCategoria(String categoria) {
		try {
			Categoria convertCategoria = Categoria.valueOf(categoria);
			return produtoRepo.findByCategoria(convertCategoria.getCod());

		} catch (IllegalArgumentException e) {
			throw new ResourceNotFoundException(
					"Parmetro informado na busca do produto não foi encontrado. CATEGORIA: " + categoria);
		}

	}

	public Produto buscarPorNome(String nome) {

		Produto produto = produtoRepo.findByNome(nome);
		if (produto == null) {
			throw new ResourceNotFoundException(
					"Parmetro informado na busca do produto não foi encontrado. NOME: " + nome);
		}

		return produto;
	}

	@Transactional
	public Produto cadastrarProduto(ProdutoInsertDTO objDto) {

		try {
			verificaSeProdutoJaECadastrado(objDto.getNome());

			Secao secao = secaoService.buscarPorId(objDto.getSecao_id());
			Produto produto = produtoRepo.save((new Produto(null, objDto.getNome(), objDto.getCategoria(), secao)));

			verificaSeIgualdadeDeCategoria(produto, secao);

			objDto.setVolume(0.0);
			Historico historico = historicoService.salvaHistorico(new Historico(null, objDto.getResponsavel(),
					objDto.getHorario(), objDto.getVolume(), secao, produto, Operacao.CADASTRO));
			secao.addHistorico(historico);
			secao.addProduto(produto);
			secao.addVolumeNoEstoque(historico.getVolume());
			produto.addHistorico(historico);
			secaoService.salvarSecao(secao);
			return produtoRepo.save(produto);

		} catch (ProductAlreadyRegisteredtException e) {
			throw new ProductAlreadyRegisteredtException(e.getMessage());

		} catch (IllegalArgumentException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch (DataIntegrityViolationException e) {
			System.out.println(
					"Classe: ProdotoService, Funcão: cadastrarProduto, Exception: DataIntegrityViolationException, Mensagem: "
							+ e.getMessage());
			return null;
		}
	}

	@Transactional
	public Produto entradaDeProduto(ProdutoEntradaSaidaDTO objDto) {

		try {
			Secao secao = secaoService.buscarPorId(objDto.getSecao_id());
			verificaVolumeDeEntrada(secao, objDto.getVolume());
			
			Produto produto = buscarPorId(objDto.getProduto_id());
			verificaSeIgualdadeDeCategoria(produto, secao);

			Historico historico = historicoService.salvaHistorico(new Historico(null, objDto.getResponsavel(),
					objDto.getHorario(), objDto.getVolume(), secao, produto, Operacao.COMPRA));
			secao.addHistorico(historico);
			secao.addVolumeNoEstoque(historico.getVolume());
			produto.addHistorico(historico);
			secaoService.salvarSecao(secao);
			return produtoRepo.save(produto);
		} catch (IllegalArgumentException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch (LimitSecaoException e) {
			throw new LimitSecaoException(e.getMessage());
		} catch (DataIntegrityViolationException e) {
			System.out.println(
					"Classe: ProdotoService, Funcão: entradaDeProduto, Exception: DataIntegrityViolationException, Mensagem: "
							+ e.getMessage());
			return null;
		} catch (Exception e) {
			System.out.println("Classe: ProdotoService, Funcão: entradaDeProduto, Exception: Exception, Mensagem: "
					+ e.getMessage());
			return null;
		}

	}

	@Transactional
	public Produto saidaDeProduto(ProdutoEntradaSaidaDTO objDto) {

		try {
			Secao secao = secaoService.buscarPorId(objDto.getSecao_id());

			verificaVolumeDeSaida(secao, objDto.getVolume());

			Produto produto = buscarPorId(objDto.getProduto_id());
			verificaSeIgualdadeDeCategoria(produto, secao);
			
			Historico historico = historicoService.salvaHistorico(new Historico(null, objDto.getResponsavel(),
					objDto.getHorario(), objDto.getVolume(), secao, produto, Operacao.VENDA));
			secao.addHistorico(historico);
			secao.removeVolumeDoEstoque(historico.getVolume());
			produto.addHistorico(historico);
			secaoService.salvarSecao(secao);
			return produtoRepo.save(produto);
		} catch (IllegalArgumentException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch (LimitSecaoException e) {
			throw new LimitSecaoException(e.getMessage());
		} catch (DataIntegrityViolationException e) {
			System.out.println(
					"Classe: ProdotoService, Funcão: saidaDeProduto, Exception: DataIntegrityViolationException, Mensagem: "
							+ e.getMessage());
			return null;
		} catch (Exception e) {
			System.out.println("Classe: ProdotoService, Funcão: saidaDeProduto, Exception: Exception, Mensagem: "
					+ e.getMessage());
			return null;
		}

	}

	private void verificaVolumeDeEntrada(Secao secao, Double volume) {
		if (secao.getVolumeLivreNoEstoque() < volume) {
			throw new LimitSecaoException(
					"Volume de entrada ultrapassa o volume disponível no estoque dessa Secao. Volume informado: "
							+ volume + ", volume disponível na secão para entrada: " + secao.getVolumeLivreNoEstoque());
		}

	}

	private void verificaVolumeDeSaida(Secao secao, Double volume) {
		if (secao.getVolumeNoEstoque() < volume) {
			throw new LimitSecaoException("Volume de saida é maior que o volume disponível na Secao. Volume informado: "
					+ volume + ", volume disponível na secão para venda: " + secao.getVolumeNoEstoque());
		}
	}

	private void verificaSeProdutoJaECadastrado(String nome) {
		Produto p = produtoRepo.findByNome(nome);
		if (p != null) {
			throw new ProductAlreadyRegisteredtException("Produto já cadastrado no banco de dados de nome: " + nome);

		}
	}

	private void verificaSeIgualdadeDeCategoria(Produto produto, Secao secao) {

		if (produto.getCategoria().getCod() != secao.getCategoria().getCod()) {
			throw new IllegalArgumentException(
					"A categoria do produto é diferente da categoria da secao. Categoria do Produto: "
							+ produto.getCategoria() + ", categoria da secao: " + secao.getCategoria());

		}
	}	
	
}
