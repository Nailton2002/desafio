package br.com.api.rest.correntista.core.ports;

import java.util.List;

import br.com.api.rest.correntista.core.domain.Correntista;

public interface CorrentistaRepository {

	int save(Correntista correntista);

	int update(Correntista book);

	Correntista findById(Long id);

	int deleteById(Long id);

	List<Correntista> findAll();

	List<Correntista> findByPublished(boolean published);

	List<Correntista> findByTitleContaining(String title);

	int deleteAll();

}
