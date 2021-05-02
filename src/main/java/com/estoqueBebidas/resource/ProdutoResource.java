package com.estoqueBebidas.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.estoqueBebidas.entities.Produto;
import com.estoqueBebidas.entities.dto.ProdutoEntradaSaidaDTO;
import com.estoqueBebidas.entities.dto.ProdutoInsertDTO;
import com.estoqueBebidas.entities.dto.ProdutoOutDTO;
import com.estoqueBebidas.service.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProdutoOutDTO> buscarPorId(@PathVariable Integer id){
		Produto produto = produtoService.buscarPorId(id);
		ProdutoOutDTO objDto = new ProdutoOutDTO(produto);
		return ResponseEntity.ok().body(objDto);
	}
	
	@GetMapping
	public ResponseEntity<List<ProdutoOutDTO>> buscarTodos(){
		List<Produto> list = produtoService.buscarTodos();
		List<ProdutoOutDTO> listDto = list.stream().map(x -> new ProdutoOutDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping(value = "/findByCategoria")
	public ResponseEntity<List<ProdutoOutDTO>> buscarPorCategoria(@RequestParam(value = "categoria") String categoria) {

		List<Produto> list = produtoService.buscarPorCategoria(categoria);
		List<ProdutoOutDTO> listDto = list.stream().map(x -> new ProdutoOutDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);

	}
	
	@GetMapping(value = "/findByNome")
	public ResponseEntity<ProdutoOutDTO> buscarPorNome(
			@RequestParam(value = "nome") String nome){		
		
		Produto produto = produtoService.buscarPorNome(nome);
		ProdutoOutDTO produtoDto = new ProdutoOutDTO(produto);
		return ResponseEntity.ok().body(produtoDto);
		
	}
	
	@PostMapping(value="/cadastrar")
	public ResponseEntity<Void> cadastrarProduto(@RequestBody ProdutoInsertDTO objDto){
		Produto produto = produtoService.cadastrarProduto(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@PostMapping(value="/compra")
	public ResponseEntity<Void> entradaProduto(@RequestBody ProdutoEntradaSaidaDTO objDto){
		Produto produto = produtoService.entradaDeProduto(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@PostMapping(value="/venda")
	public ResponseEntity<Void> saidaProduto(@RequestBody ProdutoEntradaSaidaDTO objDto){
		Produto produto = produtoService.saidaDeProduto(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
