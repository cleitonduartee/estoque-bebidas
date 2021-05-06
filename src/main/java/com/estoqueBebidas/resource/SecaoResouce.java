package com.estoqueBebidas.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estoqueBebidas.entities.dto.SecaoDisponivelEntradaOutDTO;
import com.estoqueBebidas.entities.dto.SecaoDisponivelSaidaOutDTO;
import com.estoqueBebidas.entities.dto.VolumePorCategoriaOutDTO;
import com.estoqueBebidas.entities.enuns.Categoria;
import com.estoqueBebidas.service.SecaoService;
import com.estoqueBebidas.service.exception.ResourceNotFoundException;

@RestController
@RequestMapping(value = "/secao")
public class SecaoResouce {

	@Autowired
	private SecaoService secaoService;
	


	@GetMapping(value = "/volumePorCategoria")
	public ResponseEntity<List<VolumePorCategoriaOutDTO>> volumePorCategoria() {
		
		List<VolumePorCategoriaOutDTO> lisTest = secaoService.volumePorCategoria();

		return ResponseEntity.ok().body(lisTest);

	}

	@GetMapping(value = "/disponivelEntrada")
	public ResponseEntity<List<SecaoDisponivelEntradaOutDTO>> disponivelParaEntrada(
			@RequestParam(value = "valor", defaultValue = "1") String strValor) {
		try {
			Double valor = secaoService.convertDouble(strValor);
			List<SecaoDisponivelEntradaOutDTO> list = secaoService.disponivelParaEntrada(valor);//			
			return ResponseEntity.ok().body(list);
		} catch (IllegalArgumentException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}

	}

	@GetMapping(value = "/disponivelSaida")
	public ResponseEntity<List<SecaoDisponivelSaidaOutDTO>> disponivelParaSaida(
			@RequestParam(value = "categoria") String strCategoriar) {
		try {
			Categoria categoria = secaoService.converteCategoria(strCategoriar);
			List<SecaoDisponivelSaidaOutDTO> listDto = secaoService.disponivelParaSaida(categoria);//			
			return ResponseEntity.ok().body(listDto);
		} catch (IllegalArgumentException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}
}
