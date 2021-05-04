package com.estoqueBebidas.configuracao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.estoqueBebidas.entities.Secao;
import com.estoqueBebidas.entities.dto.ProdutoEntradaSaidaDTO;
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
		secaoService.salvarSecao(new Secao(null, "Refrigerantes", Categoria.NAOALCOOLICA));
		secaoService.salvarSecao(new Secao(null, "Vinhos", Categoria.ALCOOLICA));
		secaoService.salvarSecao(new Secao(null, "Energeticos", Categoria.NAOALCOOLICA));
				
		produtoService.cadastrarProduto( new ProdutoInsertDTO(null,"Itaipava",Categoria.ALCOOLICA,1,"Cleiton Duarte",0.0));
		produtoService.entradaDeProduto( new ProdutoEntradaSaidaDTO(1,1,"Cleiton Duarte",130.0));
		produtoService.cadastrarProduto(new ProdutoInsertDTO(null,"Suco de Laranja",Categoria.NAOALCOOLICA,2,"Cleiton Duarte",0.0));
		produtoService.cadastrarProduto(new ProdutoInsertDTO(null,"Suco de Uva",Categoria.NAOALCOOLICA,2,"Cleiton Duarte",0.0));
		produtoService.entradaDeProduto(new ProdutoEntradaSaidaDTO(3,2,"Cleiton Duarte",255.0));
		produtoService.cadastrarProduto(new ProdutoInsertDTO(null,"Coca-Cola",Categoria.NAOALCOOLICA,3,"Cleiton Duarte",0.0));
		produtoService.cadastrarProduto(new ProdutoInsertDTO(null,"Fanta",Categoria.NAOALCOOLICA,3,"Cleiton Duarte",0.0));
		produtoService.entradaDeProduto(new ProdutoEntradaSaidaDTO(4,3,"Cleiton Duarte",59.0));
		produtoService.entradaDeProduto(new ProdutoEntradaSaidaDTO(5,3,"Cleiton Duarte",93.0));
		produtoService.entradaDeProduto(new ProdutoEntradaSaidaDTO(5,3,"Cleiton Duarte",108.0));
		produtoService.entradaDeProduto(new ProdutoEntradaSaidaDTO(5,3,"Cleiton Duarte",111.0));
		produtoService.cadastrarProduto(new ProdutoInsertDTO(null,"Red Bull",Categoria.NAOALCOOLICA,5,"Cleiton Duarte",0.0));
		produtoService.cadastrarProduto(new ProdutoInsertDTO(null,"Monster",Categoria.NAOALCOOLICA,5,"Cleiton Duarte",0.0));
		produtoService.cadastrarProduto(new ProdutoInsertDTO(null,"Fusion",Categoria.NAOALCOOLICA,5,"Cleiton Duarte",0.0));
		produtoService.entradaDeProduto(new ProdutoEntradaSaidaDTO(6,5,"Cleiton Duarte",73.0));
		produtoService.entradaDeProduto(new ProdutoEntradaSaidaDTO(7,5,"Cleiton Duarte",98.0));
		produtoService.entradaDeProduto(new ProdutoEntradaSaidaDTO(8,5,"Cleiton Duarte",38.0));
		produtoService.saidaDeProduto(new ProdutoEntradaSaidaDTO(6,5,"Cleiton Duarte",9.0));
		produtoService.saidaDeProduto(new ProdutoEntradaSaidaDTO(7,5,"Cleiton Duarte",100.0));
		produtoService.saidaDeProduto(new ProdutoEntradaSaidaDTO(7,5,"Cleiton Duarte",10.0));
		produtoService.cadastrarProduto(new ProdutoInsertDTO(null, "Vinho Suave",Categoria.ALCOOLICA,4,"Cleiton Duarte",0.0));
		produtoService.entradaDeProduto(new ProdutoEntradaSaidaDTO(9,4,"Cleiton Duarte",38.0));
		produtoService.entradaDeProduto(new ProdutoEntradaSaidaDTO(9,4,"Cleiton Duarte",132.0));
		produtoService.entradaDeProduto(new ProdutoEntradaSaidaDTO(9,4,"Cleiton Duarte",99.0));
		produtoService.entradaDeProduto(new ProdutoEntradaSaidaDTO(9,4,"Cleiton Duarte",123.0));
		produtoService.saidaDeProduto(new ProdutoEntradaSaidaDTO(9,4,"Cleiton Duarte",11.0));
		produtoService.saidaDeProduto(new ProdutoEntradaSaidaDTO(9,4,"Cleiton Duarte",29.0));
		produtoService.saidaDeProduto(new ProdutoEntradaSaidaDTO(9,4,"Cleiton Duarte",74.0));
	}

}
