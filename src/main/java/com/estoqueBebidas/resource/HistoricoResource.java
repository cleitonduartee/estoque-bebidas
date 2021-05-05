package com.estoqueBebidas.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estoqueBebidas.entities.Historico;
import com.estoqueBebidas.entities.Secao;
import com.estoqueBebidas.entities.dto.HistoricoOutDTO;
import com.estoqueBebidas.entities.dto.SecaoOutDto;
import com.estoqueBebidas.service.HistoricoService;
import com.estoqueBebidas.service.exception.ResourceNotFoundException;

@RestController
@RequestMapping(value = "/historicos")
public class HistoricoResource {
	
	@Autowired
	private HistoricoService historicoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<HistoricoOutDTO> buscarPorId(@PathVariable Integer id){
		try {
			Historico historico = historicoService.buscarPorId(id);
			HistoricoOutDTO objDto = new HistoricoOutDTO(historico);
			return ResponseEntity.ok().body(objDto);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity<List<HistoricoOutDTO>> buscarTodos(){
		List<Historico> list = historicoService.buscarTodos();
		List<HistoricoOutDTO> listDto = list.stream().map(x -> new HistoricoOutDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	@GetMapping(value = "/porCategoriaSecao")
	public ResponseEntity<SecaoOutDto> buscarPorCategoriaESecao(
			@RequestParam(value = "categoria", defaultValue = "")String categoria,
			@RequestParam(value = "secao", defaultValue = "") String secao){
		try {
			Secao respSecao = historicoService.buscarPorCategoriaESecao(categoria,secao);
			SecaoOutDto secaoDto = new SecaoOutDto(respSecao);
			return ResponseEntity.ok().body(secaoDto);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}
}
