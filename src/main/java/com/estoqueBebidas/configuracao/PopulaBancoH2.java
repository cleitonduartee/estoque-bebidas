package com.estoqueBebidas.configuracao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.estoqueBebidas.entities.Secao;
import com.estoqueBebidas.entities.dto.HistoricoInsertDTO;
import com.estoqueBebidas.entities.dto.ProdutoInsertDTO;
import com.estoqueBebidas.entities.enuns.Categoria;
import com.estoqueBebidas.service.HistoricoService;
import com.estoqueBebidas.service.ProdutoService;
import com.estoqueBebidas.service.SecaoService;

@Configuration
public class PopulaBancoH2 implements CommandLineRunner {

	@Autowired
	private HistoricoService historicoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private SecaoService secaoService;
	
	@Override
	public void run(String... args) throws Exception {
	
		secaoService.salvarSecao(new Secao(null, "Cervejas", Categoria.ALCOOLICA));
		secaoService.salvarSecao(new Secao(null, "Sucos", Categoria.NAOALCOOLICA));
		secaoService.salvarSecao(new Secao(null, "Refirgerantes", Categoria.NAOALCOOLICA));
		secaoService.salvarSecao(new Secao(null, "Vinhos", Categoria.ALCOOLICA));
		secaoService.salvarSecao(new Secao(null, "Energeticos", Categoria.NAOALCOOLICA));
				
		produtoService.salvaProduto( new ProdutoInsertDTO(null,"Itaipava",Categoria.ALCOOLICA,1));
		produtoService.salvaProduto( new ProdutoInsertDTO(null,"Bohemia",Categoria.ALCOOLICA,1));
		produtoService.salvaProduto(new ProdutoInsertDTO(null,"Suco de Laranja",Categoria.NAOALCOOLICA,2));
		produtoService.salvaProduto(new ProdutoInsertDTO(null,"Suco de Uva",Categoria.NAOALCOOLICA,2));
		produtoService.salvaProduto(new ProdutoInsertDTO(null,"Suco de Abacaxi",Categoria.NAOALCOOLICA,2));
		produtoService.salvaProduto(new ProdutoInsertDTO(null,"Coca-Cola",Categoria.NAOALCOOLICA,3));
		produtoService.salvaProduto(new ProdutoInsertDTO(null,"Fanta",Categoria.NAOALCOOLICA,3));
		produtoService.salvaProduto(new ProdutoInsertDTO(null,"Vinho Suave ",Categoria.ALCOOLICA,4));
		produtoService.salvaProduto(new ProdutoInsertDTO(null,"Vinho Branco",Categoria.ALCOOLICA,4));
		produtoService.salvaProduto(new ProdutoInsertDTO(null,"Red Bull",Categoria.NAOALCOOLICA,5));
		produtoService.salvaProduto(new ProdutoInsertDTO(null,"Monster",Categoria.NAOALCOOLICA,5));
		produtoService.salvaProduto(new ProdutoInsertDTO(null,"Fusion",Categoria.NAOALCOOLICA,5));
				
		historicoService.salvaHistorico(new HistoricoInsertDTO(null, "José de Jesus",  70.0, 5, 12));
		historicoService.salvaHistorico(new HistoricoInsertDTO(null, "Pedro Henrique",  170.0, 5, 10));
		historicoService.salvaHistorico(new HistoricoInsertDTO(null, "Cleiton Duarte",  95.0, 1, 1));
		historicoService.salvaHistorico(new HistoricoInsertDTO(null, "Mario Cesar",  86.0, 1, 2));
		historicoService.salvaHistorico(new HistoricoInsertDTO(null, "Beatriz da Silva",  59.0, 4, 8));
		historicoService.salvaHistorico(new HistoricoInsertDTO(null, "Carlos Goncalves",  98.0, 4, 9));
		historicoService.salvaHistorico(new HistoricoInsertDTO(null, "Stephany Perreira", 117.0, 3, 6));
		historicoService.salvaHistorico(new HistoricoInsertDTO(null, "Maria Auxiliadora", 273.0, 2, 4));
	
		
	}

}
