package com.estoqueBebidas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.estoqueBebidas.entities.Secao;
import com.estoqueBebidas.entities.dto.SecaoDisponivelEntradaOutDTO;
import com.estoqueBebidas.entities.dto.VolumePorCategoriaOutDTO;

public interface SecaoRepository extends JpaRepository<Secao, Integer> {

	Secao findByNome(String nome);
	@Query("SELECT new com.estoqueBebidas.entities.dto.VolumePorCategoriaOutDTO (obj.categoria, SUM(obj.volumeNoEstoque)) "
			+ "FROM Secao AS obj GROUP BY obj.categoria")
	List<VolumePorCategoriaOutDTO> buscarPorVolume();
	
	@Query("SELECT new com.estoqueBebidas.entities.dto.SecaoDisponivelEntradaOutDTO(obj) "
			+ "FROM Secao obj WHERE :valor <=obj.volumeLivreNoEstoque")
	List<SecaoDisponivelEntradaOutDTO> disponivelParaEntrada(@Param("valor") Double valor);
}
