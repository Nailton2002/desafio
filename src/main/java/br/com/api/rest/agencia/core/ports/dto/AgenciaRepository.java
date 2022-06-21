package br.com.api.rest.agencia.core.ports.dto;

import java.util.List;

import br.com.api.rest.agencia.core.domain.Agencia;


public interface AgenciaRepository {

	int save(Agencia agencia);

	int update(Agencia agencia);

	Agencia findById(int id);

	int deleteById(int id);

	List<Agencia> findAll();

	Iterable<Agencia> findByNome(String nome);

	

}
