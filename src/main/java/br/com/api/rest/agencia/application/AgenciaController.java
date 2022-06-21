package br.com.api.rest.agencia.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.rest.agencia.core.domain.Agencia;
import br.com.api.rest.agencia.core.ports.dto.AgenciaRepository;

@RestController
@RequestMapping("/api")
public class AgenciaController {

	@Autowired
	private AgenciaRepository agenciaRepository;
	
	@GetMapping("/agencia")
	  public ResponseEntity<List<Agencia>> getAgencia(@RequestParam(required = false) String nome) {
	    try {
	      List<Agencia> agencia = new ArrayList<Agencia>();
	      if (nome == null)
	    	  agenciaRepository.findAll().forEach(agencia::add);
	      else
	    	  agenciaRepository.findByNome(nome).forEach(agencia::add);
	      if (agencia.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(agencia, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	@GetMapping("/agencia/{id}")
	public ResponseEntity<Agencia> getAgenciaById(@PathVariable("id") int id) {
		Agencia agencia = agenciaRepository.findById(id);
		if (agencia != null) {
			return new ResponseEntity<>(agencia, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	  public ResponseEntity<String> createAgencia(@RequestBody Agencia agencia) {
	    try {
	    	agenciaRepository.save(new Agencia(agencia.getNome(), agencia.getEndereco()));
	      return new ResponseEntity<>("Agencia criada com sucesso!.", HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>("Erro", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
}
