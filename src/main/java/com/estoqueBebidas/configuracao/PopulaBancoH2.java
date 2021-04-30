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

@Configuration
public class PopulaBancoH2 implements CommandLineRunner {

	@Autowired
	private HistoricoRepository historicoRepo;
	
	@Autowired
	private SecaoRepository secaoRepo;
	
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Override
	public void run(String... args) throws Exception {
	
		Secao s1 = new Secao(null, "Cervejas", Categoria.ALCOOLICA);
		Secao s2 = new Secao(null, "Sucos", Categoria.NAOALCOOLICA);
		Secao s3 = new Secao(null, "Refirgerantes", Categoria.NAOALCOOLICA);
		Secao s4 = new Secao(null, "Vinhos", Categoria.ALCOOLICA);
		Secao s5 = new Secao(null, "Energeticos", Categoria.NAOALCOOLICA);
		
		secaoRepo.saveAll(Arrays.asList(s1,s2,s3,s4,s5));
		
		Produto p1 = new Produto(null,"Itaipava",Categoria.ALCOOLICA,s1);
		Produto p2 = new Produto(null,"Bohemia",Categoria.ALCOOLICA,s1);
		Produto p3 = new Produto(null,"Suco de Laranja",Categoria.NAOALCOOLICA,s2);
		Produto p4 = new Produto(null,"Suco de Uva",Categoria.NAOALCOOLICA,s2);
		Produto p5 = new Produto(null,"Suco de Abacaxi",Categoria.NAOALCOOLICA,s2);
		Produto p6 = new Produto(null,"Coca-Cola",Categoria.NAOALCOOLICA,s3);
		Produto p7 = new Produto(null,"Fanta",Categoria.NAOALCOOLICA,s3);
		Produto p8 = new Produto(null,"Vinho Suave ",Categoria.ALCOOLICA,s4);
		Produto p9 = new Produto(null,"Vinho Branco",Categoria.ALCOOLICA,s4);
		Produto p10 = new Produto(null,"Red Bull",Categoria.NAOALCOOLICA,s5);
		Produto p11 = new Produto(null,"Monster",Categoria.NAOALCOOLICA,s5);
		Produto p12 = new Produto(null,"Fusion",Categoria.NAOALCOOLICA,s5);
		
		produtoRepo.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12));
		
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
