package br.com.api.rest.conta.corrente.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import br.com.api.rest.agencia.core.AgenciaService;
import br.com.api.rest.conta.corrente.core.domain.ContaCorrente;
import br.com.api.rest.conta.corrente.core.ports.ContaCorrenteRepository;

@Service
public class ContaCorrenteService {

	@Autowired
	private ContaCorrenteRepository contaCorrenteRepositoy;

	@Autowired
	private AgenciaService agenciaService;

	public void criaContaCorrente(ContaCorrente novaContaCorrente) throws NotFoundException {
		
		if (agenciaService.buscaAgenciaPorId(novaContaCorrente.getAgencia().getId()) == null) {
			throw new NotFoundException();	
		} else {
			contaCorrenteRepositoy.save(novaContaCorrente);
		}
		
		
	}

	public Iterable<ContaCorrente> findAll() {
		return null;
	}

	public ContaCorrente findById(Long id) {
		return null;
	}

	public void transferir(ContaCorrente cc) {
		// TODO Auto-generated method stub

	}

}
