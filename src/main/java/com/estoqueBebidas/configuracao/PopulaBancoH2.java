package com.estoqueBebidas.configuracao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.estoqueBebidas.entities.Secao;
import com.estoqueBebidas.entities.dto.ProdutoInsertDTO;
import com.estoqueBebidas.entities.enuns.Categoria;
import com.estoqueBebidas.service.ProdutoService;
import com.estoqueBebidas.service.SecaoService;

@Configuration
public class PopulaBancoH2 implements CommandLineRunner {

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
				
		produtoService.cadastrarProduto( new ProdutoInsertDTO(null,"Itaipava",Categoria.ALCOOLICA,1,"Cleiton Duarte",0.0));
		produtoService.cadastrarProduto( new ProdutoInsertDTO(null,"Bohemia",Categoria.ALCOOLICA,1,"Cleiton Duarte",0.0));
		produtoService.cadastrarProduto(new ProdutoInsertDTO(null,"Suco de Laranja",Categoria.NAOALCOOLICA,2,"Cleiton Duarte",0.0));
		produtoService.cadastrarProduto(new ProdutoInsertDTO(null,"Suco de Uva",Categoria.NAOALCOOLICA,2,"Cleiton Duarte",0.0));
		produtoService.cadastrarProduto(new ProdutoInsertDTO(null,"Suco de Abacaxi",Categoria.NAOALCOOLICA,2,"Cleiton Duarte",0.0));
		produtoService.cadastrarProduto(new ProdutoInsertDTO(null,"Coca-Cola",Categoria.NAOALCOOLICA,3,"Cleiton Duarte",0.0));
		produtoService.cadastrarProduto(new ProdutoInsertDTO(null,"Fanta",Categoria.NAOALCOOLICA,3,"Cleiton Duarte",0.0));
		produtoService.cadastrarProduto(new ProdutoInsertDTO(null,"Vinho Suave ",Categoria.ALCOOLICA,4,"Cleiton Duarte",0.0));
		produtoService.cadastrarProduto(new ProdutoInsertDTO(null,"Vinho Branco",Categoria.ALCOOLICA,4,"Cleiton Duarte",0.0));
		produtoService.cadastrarProduto(new ProdutoInsertDTO(null,"Red Bull",Categoria.NAOALCOOLICA,5,"Cleiton Duarte",0.0));
		produtoService.cadastrarProduto(new ProdutoInsertDTO(null,"Monster",Categoria.NAOALCOOLICA,5,"Cleiton Duarte",0.0));
		produtoService.cadastrarProduto(new ProdutoInsertDTO(null,"Fusion",Categoria.NAOALCOOLICA,5,"Cleiton Duarte",0.0));
		
	}

}
