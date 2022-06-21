package br.com.api.rest.agencia.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.rest.agencia.core.domain.Agencia;
import br.com.api.rest.agencia.core.ports.dto.AgenciaRepository;
import br.com.api.rest.agencia.core.ports.dto.AgenciaRepository;
import br.com.api.rest.conta.corrente.core.domain.ContaCorrente;
import br.com.api.rest.conta.corrente.core.ports.ContaCorrenteRepository;

@Service
public class AgenciaService {

	@Autowired
    private AgenciaRepository agenciaRepositoy;

    public Agencia buscaAgenciaPorId(int id){
        return agenciaRepositoy.findById(id);        
    }

	public Iterable<ContaCorrente> findAll() {
		return null;
	}

	public void transferir(ContaCorrente cc) {
		// TODO Auto-generated method stub
		
	}
	
}
