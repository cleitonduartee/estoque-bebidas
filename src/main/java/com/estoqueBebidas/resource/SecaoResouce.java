package com.estoqueBebidas.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estoqueBebidas.entities.Secao;
import com.estoqueBebidas.entities.dto.SecaoVolumePorTipoOutDTO;
import com.estoqueBebidas.entities.enuns.Categoria;
import com.estoqueBebidas.repository.ProdutoRepository;
import com.estoqueBebidas.service.HistoricoService;
import com.estoqueBebidas.service.SecaoService;

@RestController
@RequestMapping(value = "/secao")
public class SecaoResouce {
	
	@Autowired
	private ProdutoRepository produtoRepo;

	@Autowired
	private SecaoService secaoService;

	@Autowired
	private HistoricoService historicoService;

	@GetMapping(value = "/volumePorTipo")
	public ResponseEntity<List<SecaoVolumePorTipoOutDTO>> volumePortipo(){
		List<Secao> listSecao = secaoService.buscarTodos();
		SecaoVolumePorTipoOutDTO tipoAlcoolica = new SecaoVolumePorTipoOutDTO(Categoria.ALCOOLICA);
		SecaoVolumePorTipoOutDTO tipoNaoAlcoolica = new SecaoVolumePorTipoOutDTO(Categoria.NAOALCOOLICA);
		for(Secao x : listSecao) {
			if(x.getCategoria().getCod() == tipoAlcoolica.getCategoria().getCod()) {
				tipoAlcoolica.addTotal(x.getVolumeNoEstoque());
				tipoAlcoolica.addSecao(x);
			}
			else {
				tipoNaoAlcoolica.addTotal(x.getVolumeNoEstoque());
				tipoNaoAlcoolica.addSecao(x);
			}
		}
		List<SecaoVolumePorTipoOutDTO> listPorTipo = new ArrayList<>() {
			{
				add(tipoAlcoolica);
				add(tipoNaoAlcoolica);
			}
		};
		return ResponseEntity.ok().body(listPorTipo);
		
	}
//	@GetMapping(value = "/volumePorTipo")
//	public ResponseEntity<SecaoVolumePorTipoOutDTO> disponivelParaEntrada(){
//		
//	}
//	@GetMapping(value = "/volumePorTipo")
//	public ResponseEntity<SecaoVolumePorTipoOutDTO> desponivelParaSaida(){
//		
//	}
}
