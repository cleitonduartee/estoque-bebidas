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

import com.estoqueBebidas.entities.Historico;
import com.estoqueBebidas.entities.dto.HistoricoCompraDTO;
import com.estoqueBebidas.entities.dto.HistoricoOutDTO;
import com.estoqueBebidas.service.HistoricoService;

@RestController
@RequestMapping(value = "/historicos")
public class HistoricoResource {
	
	@Autowired
	private HistoricoService historicoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<HistoricoOutDTO> buscarPorId(@PathVariable Integer id){
		Historico historico = historicoService.buscarPorId(id);
		HistoricoOutDTO objDto = new HistoricoOutDTO(historico);
		return ResponseEntity.ok().body(objDto);
	}
	
	@GetMapping
	public ResponseEntity<List<HistoricoOutDTO>> buscarTodos(){
		List<Historico> list = historicoService.buscarTodos();
		List<HistoricoOutDTO> listDto = list.stream().map(x -> new HistoricoOutDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

//	@PostMapping()
//	public ResponseEntity<Void> salvarProduto(@RequestBody HistoricoCompraDTO objDto){
//		Historico historico = historicoService.salvaHistorico(objDto);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(historico.getId()).toUri();
//		return ResponseEntity.created(uri).build();
//	}
}
