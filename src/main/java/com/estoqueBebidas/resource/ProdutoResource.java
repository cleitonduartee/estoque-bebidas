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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.estoqueBebidas.entities.Produto;
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
	
	@PostMapping(value="/cadastra")
	public ResponseEntity<Void> cadastrarProduto(@RequestBody ProdutoInsertDTO objDto){
		Produto produto = produtoService.cadastrarProduto(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
