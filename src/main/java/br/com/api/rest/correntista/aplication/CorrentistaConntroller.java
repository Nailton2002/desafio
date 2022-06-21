package br.com.api.rest.correntista.aplication;

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

import br.com.api.rest.correntista.core.domain.Correntista;
import br.com.api.rest.correntista.core.ports.CorrentistaRepository;

@RestController
@RequestMapping("/correntista")
public class CorrentistaConntroller {

	@Autowired
	private CorrentistaRepository correntistaRepository;

	@GetMapping
	public ResponseEntity<List<Correntista>> getAllCorrentista(@RequestParam(required = false) String nome) {
		try {
			List<Correntista> correntista = new ArrayList<Correntista>();
			if (nome == null)
				correntistaRepository.findAll().forEach(correntista::add);
			else
				correntistaRepository.findByTitleContaining(nome).forEach(correntista::add);
			if (correntista.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(correntista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Correntista> getCorrentistaById(@PathVariable("id") Long id) {
		Correntista correntista = correntistaRepository.findById(id);
		if (correntista != null) {
			return new ResponseEntity<>(correntista, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<String> createCorrentista(@RequestBody Correntista correntista) {
		try {
			correntistaRepository.save(
					new Correntista(correntista.getNome(), correntista.getCpf(), correntista.getDataNascimento()));
			return new ResponseEntity<>("Correntista was created successfully.", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
