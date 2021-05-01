package com.estoqueBebidas.resource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estoqueBebidas.entities.Secao;
import com.estoqueBebidas.entities.dto.SecaoDisponivelEntradaOutDTO;
import com.estoqueBebidas.entities.dto.SecaoDisponivelSaidaOutDTO;
import com.estoqueBebidas.entities.dto.VolumePorTipoOutDTO;
import com.estoqueBebidas.entities.enuns.Categoria;
import com.estoqueBebidas.service.SecaoService;
import com.estoqueBebidas.service.exception.ResourceNotFoundException;

@RestController
@RequestMapping(value = "/secao")
public class SecaoResouce {

	@Autowired
	private SecaoService secaoService;

	@GetMapping(value = "/volumePorTipo")
	public ResponseEntity<List<VolumePorTipoOutDTO>> volumePortipo() {
		List<Secao> listSecao = secaoService.buscarTodos();
		VolumePorTipoOutDTO tipoAlcoolica = new VolumePorTipoOutDTO(Categoria.ALCOOLICA);
		VolumePorTipoOutDTO tipoNaoAlcoolica = new VolumePorTipoOutDTO(Categoria.NAOALCOOLICA);

		listSecao.stream().forEach(x -> {
			if (x.getCategoria().getCod() == tipoAlcoolica.getCategoria().getCod()) {
				tipoAlcoolica.addTotal(x.getVolumeNoEstoque());
				tipoAlcoolica.addSecao(x);
			} else {
				tipoNaoAlcoolica.addTotal(x.getVolumeNoEstoque());
				tipoNaoAlcoolica.addSecao(x);
			}
		});

		List<VolumePorTipoOutDTO> listPorTipo = Arrays.asList(tipoAlcoolica, tipoNaoAlcoolica);
		return ResponseEntity.ok().body(listPorTipo);

	}

	@GetMapping(value = "/disponivelEntrada")
	public ResponseEntity<List<SecaoDisponivelEntradaOutDTO>> disponivelParaEntrada(
			@RequestParam(value = "valor", defaultValue = "1") String strValor) {
		try {
			Double valor = secaoService.convertDouble(strValor);
			List<Secao> listSecao = secaoService.buscarTodos();
			List<SecaoDisponivelEntradaOutDTO> listDto = listSecao.stream()
					.filter(x -> (x.getVolumeLivreNoEstoque() >= valor)).map(x -> new SecaoDisponivelEntradaOutDTO(x))
					.collect(Collectors.toList());
			return ResponseEntity.ok().body(listDto);
		} catch (IllegalArgumentException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}

	}

	@GetMapping(value = "/volumePorCategoria")
	public ResponseEntity<List<SecaoDisponivelSaidaOutDTO>> disponivelParaSaida(
			@RequestParam(value = "categoria") String strCategoriar) {
		try {
			Categoria categoria = secaoService.converteCategoria(strCategoriar);
			List<Secao> listSecao = secaoService.buscarTodos();
			List<SecaoDisponivelSaidaOutDTO> listDto = listSecao.stream()
					.filter(x -> x.getCategoria().getCod() == categoria.getCod())
					.map(x -> new SecaoDisponivelSaidaOutDTO(x)).collect(Collectors.toList());
			return ResponseEntity.ok().body(listDto);
		} catch (IllegalArgumentException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}
}
