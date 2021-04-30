package com.estoqueBebidas.configuracao;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.estoqueBebidas.entities.Historico;
import com.estoqueBebidas.entities.Produto;
import com.estoqueBebidas.entities.Secao;
import com.estoqueBebidas.entities.dto.ProdutoInsertDTO;
import com.estoqueBebidas.entities.enuns.Categoria;
import com.estoqueBebidas.repository.HistoricoRepository;
import com.estoqueBebidas.repository.ProdutoRepository;
import com.estoqueBebidas.service.HistoricoService;
import com.estoqueBebidas.service.ProdutoService;
import com.estoqueBebidas.service.SecaoService;

@Configuration
public class PopulaBancoH2 implements CommandLineRunner {

	@Autowired
	private HistoricoRepository historicoRepo;
		
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired
	private HistoricoService historicoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private SecaoService secaoService;
	
	@Override
	public void run(String... args) throws Exception {
	
		Secao s1 = secaoService.salvarSecao(new Secao(null, "Cervejas", Categoria.ALCOOLICA));
		Secao s2 = secaoService.salvarSecao(new Secao(null, "Sucos", Categoria.NAOALCOOLICA));
		Secao s3 = secaoService.salvarSecao(new Secao(null, "Refirgerantes", Categoria.NAOALCOOLICA));
		Secao s4 = secaoService.salvarSecao(new Secao(null, "Vinhos", Categoria.ALCOOLICA));
		Secao s5 = secaoService.salvarSecao(new Secao(null, "Energeticos", Categoria.NAOALCOOLICA));
				
		Produto p1 = produtoService.salvaProduto( new ProdutoInsertDTO(null,"Itaipava",Categoria.ALCOOLICA,1));
		Produto p2 = produtoService.salvaProduto( new ProdutoInsertDTO(null,"Bohemia",Categoria.ALCOOLICA,1));
		Produto p3 = produtoService.salvaProduto(new ProdutoInsertDTO(null,"Suco de Laranja",Categoria.NAOALCOOLICA,2));
		Produto p4 = produtoService.salvaProduto(new ProdutoInsertDTO(null,"Suco de Uva",Categoria.NAOALCOOLICA,2));
		Produto p5 = produtoService.salvaProduto(new ProdutoInsertDTO(null,"Suco de Abacaxi",Categoria.NAOALCOOLICA,2));
		Produto p6 = produtoService.salvaProduto(new ProdutoInsertDTO(null,"Coca-Cola",Categoria.NAOALCOOLICA,3));
		Produto p7 = produtoService.salvaProduto(new ProdutoInsertDTO(null,"Fanta",Categoria.NAOALCOOLICA,3));
		Produto p8 = produtoService.salvaProduto(new ProdutoInsertDTO(null,"Vinho Suave ",Categoria.ALCOOLICA,4));
		Produto p9 = produtoService.salvaProduto(new ProdutoInsertDTO(null,"Vinho Branco",Categoria.ALCOOLICA,4));
		Produto p10 = produtoService.salvaProduto(new ProdutoInsertDTO(null,"Red Bull",Categoria.NAOALCOOLICA,5));
		Produto p11 = produtoService.salvaProduto(new ProdutoInsertDTO(null,"Monster",Categoria.NAOALCOOLICA,5));
		Produto p12 = produtoService.salvaProduto(new ProdutoInsertDTO(null,"Fusion",Categoria.NAOALCOOLICA,5));
				
		Historico h1 = historicoService.salvaHistorico(new Historico(null, "José de Jesus", Instant.now(), 70.0, s5, p12));
		Historico h2 = historicoService.salvaHistorico(new Historico(null, "Pedro Henrique", Instant.now(), 170.0, s5, p10));
		Historico h3 = historicoService.salvaHistorico(new Historico(null, "Cleiton Duarte", Instant.now(), 95.0, s1, p1));
		Historico h4 = historicoService.salvaHistorico(new Historico(null, "Mario Cesar", Instant.now(), 86.0, s1, p2));
		Historico h5 = historicoService.salvaHistorico(new Historico(null, "Beatriz da Silva", Instant.now(), 59.0, s4, p8));
		Historico h6 = historicoService.salvaHistorico(new Historico(null, "Carlos Goncalves", Instant.now(), 98.0, s4, p9));
		Historico h7 = historicoService.salvaHistorico(new Historico(null, "Stephany Perreira", Instant.now(), 117.0, s3, p6));
		Historico h8 = historicoService.salvaHistorico(new Historico(null, "Maria Auxiliadora", Instant.now(), 273.0, s2, p4));
		
		//historicoRepo.saveAll(Arrays.asList(h1,h2,h3,h4,h5,h6,h7,h8));
		
		s1.addHistorico(h3);
		s1.addHistorico(h4);
		s2.addHistorico(h8);
		s3.addHistorico(h7);
		s4.addHistorico(h5);
		s4.addHistorico(h6);
		s5.addHistorico(h1);
		s5.addHistorico(h2);		
		System.out.println(s1.getHistoricos());
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
		
		secaoService.salvarSecao(s1);
		secaoService.salvarSecao(s2);
		secaoService.salvarSecao(s3);
		secaoService.salvarSecao(s4);
		secaoService.salvarSecao(s5);
		
	}

}
