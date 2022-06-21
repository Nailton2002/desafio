package br.com.api.rest.conta.corrente.core.ports;

import java.util.List;

import br.com.api.rest.conta.corrente.core.domain.ContaCorrente;

public interface ContaCorrenteRepository {

    int save(ContaCorrente contaCorrente);

	ContaCorrente findById(Long id);

	int transferir(ContaCorrente contaCorrente);

	List<ContaCorrente> findAll();

}
