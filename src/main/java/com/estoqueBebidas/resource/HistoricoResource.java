package com.estoqueBebidas.resource;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.estoqueBebidas.entities.Historico;
import com.estoqueBebidas.entities.dto.HistoricoInsertDTO;
import com.estoqueBebidas.service.HistoricoService;

@RestController
@RequestMapping(value = "/historicos")
public class HistoricoResource {
	
	@Autowired
	private HistoricoService historicoService;

	@PostMapping()
	public ResponseEntity<Void> salvarProduto(@RequestBody HistoricoInsertDTO objDto){
		Historico historico = historicoService.salvaHistorico(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(historico.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
