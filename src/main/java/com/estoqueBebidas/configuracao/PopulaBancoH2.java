package com.estoqueBebidas.configuracao;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.estoqueBebidas.entities.Historico;
import com.estoqueBebidas.entities.Produto;
import com.estoqueBebidas.entities.Secao;
import com.estoqueBebidas.entities.enuns.Categoria;
import com.estoqueBebidas.repository.HistoricoRepository;
import com.estoqueBebidas.repository.ProdutoRepository;
import com.estoqueBebidas.repository.SecaoRepository;
import com.estoqueBebidas.service.HistoricoService;
import com.estoqueBebidas.service.ProdutoService;

@Configuration
public class PopulaBancoH2 implements CommandLineRunner {

	@Autowired
	private HistoricoRepository historicoRepo;
	
	@Autowired
	private SecaoRepository secaoRepo;
	
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired
	private HistoricoService historicoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Override
	public void run(String... args) throws Exception {
	
		Secao s1 = new Secao(null, "Cervejas", Categoria.ALCOOLICA);
		Secao s2 = new Secao(null, "Sucos", Categoria.NAOALCOOLICA);
		Secao s3 = new Secao(null, "Refirgerantes", Categoria.NAOALCOOLICA);
		Secao s4 = new Secao(null, "Vinhos", Categoria.ALCOOLICA);
		Secao s5 = new Secao(null, "Energeticos", Categoria.NAOALCOOLICA);
		
		secaoRepo.saveAll(Arrays.asList(s1,s2,s3,s4,s5));
		
		Produto p1 = produtoService.salvaProduto( new Produto(null,"Itaipava",Categoria.ALCOOLICA,s1));
		Produto p2 = produtoService.salvaProduto( new Produto(null,"Bohemia",Categoria.ALCOOLICA,s1));
		Produto p3 = produtoService.salvaProduto(new Produto(null,"Suco de Laranja",Categoria.NAOALCOOLICA,s2));
		Produto p4 = produtoService.salvaProduto(new Produto(null,"Suco de Uva",Categoria.NAOALCOOLICA,s2));
		Produto p5 = produtoService.salvaProduto(new Produto(null,"Suco de Abacaxi",Categoria.NAOALCOOLICA,s2));
		Produto p6 = produtoService.salvaProduto(new Produto(null,"Coca-Cola",Categoria.NAOALCOOLICA,s3));
		Produto p7 = produtoService.salvaProduto(new Produto(null,"Fanta",Categoria.NAOALCOOLICA,s3));
		Produto p8 = produtoService.salvaProduto(new Produto(null,"Vinho Suave ",Categoria.ALCOOLICA,s4));
		Produto p9 = produtoService.salvaProduto(new Produto(null,"Vinho Branco",Categoria.ALCOOLICA,s4));
		Produto p10 = produtoService.salvaProduto(new Produto(null,"Red Bull",Categoria.NAOALCOOLICA,s5));
		Produto p11 = produtoService.salvaProduto(new Produto(null,"Monster",Categoria.NAOALCOOLICA,s5));
		Produto p12 = produtoService.salvaProduto(new Produto(null,"Fusion",Categoria.NAOALCOOLICA,s5));
				
		Historico h1 = new Historico(null, "José de Jesus", Instant.now(), 70.0, s5, p12);
		Historico h2 = new Historico(null, "Pedro Henrique", Instant.now(), 170.0, s5, p10);
		Historico h3 = new Historico(null, "Cleiton Duarte", Instant.now(), 95.0, s1, p1);
		Historico h4 = new Historico(null, "Mario Cesar", Instant.now(), 86.0, s1, p2);
		Historico h5 = new Historico(null, "Beatriz da Silva", Instant.now(), 59.0, s4, p8);
		Historico h6 = new Historico(null, "Carlos Goncalves", Instant.now(), 98.0, s4, p9);
		Historico h7 = new Historico(null, "Stephany Perreira", Instant.now(), 117.0, s3, p6);
		Historico h8 = new Historico(null, "Maria Auxiliadora", Instant.now(), 273.0, s2, p4);
		
		historicoRepo.saveAll(Arrays.asList(h1,h2,h3,h4,h5,h6,h7,h8));
		
		s1.addHistorico(h3);
		s1.addHistorico(h4);
		s2.addHistorico(h8);
		s3.addHistorico(h7);
		s4.addHistorico(h5);
		s4.addHistorico(h6);
		s5.addHistorico(h1);
		s5.addHistorico(h2);		
		
		s1.addProduto(p1);
		s1.addProduto(p2);
		s2.addProduto(p3);
		s2.addProduto(p4);
		s2.addProduto(p5);
		s3.addProduto(p6);
		s3.addProduto(p7);
		s4.addProduto(p8);
		s4.addProduto(p9);
		s5.addProduto(p10);
		s5.addProduto(p11);
		s5.addProduto(p12);
		
		secaoRepo.saveAll(Arrays.asList(s1,s2,s3,s4,s5));
		
	}

}
