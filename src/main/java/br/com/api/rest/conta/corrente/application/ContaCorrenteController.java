package br.com.api.rest.conta.corrente.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.rest.conta.corrente.core.ContaCorrenteService;
import br.com.api.rest.conta.corrente.core.domain.ContaCorrente;
import br.com.api.rest.conta.corrente.core.ports.dto.request.CriaContaCorrenteRequest;

@RestController
@RequestMapping("/conta/corrente")
public class ContaCorrenteController {

	@Autowired
	private ContaCorrenteService contaContaService;

	@PostMapping()
	public ResponseEntity<String> criar(@RequestBody CriaContaCorrenteRequest contaCorrente) {
		try {
			contaContaService.criaContaCorrente(new ContaCorrente(contaCorrente));
			return new ResponseEntity<>("Conta Corrente criado com successo!", HttpStatus.CREATED);
		} catch (NotFoundException e) {
			return new ResponseEntity<>("Agencia não encontrada", HttpStatus.NOT_FOUND);
		}
		catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/lista")
	public ResponseEntity<List<ContaCorrente>> getAllContaCorrente(@RequestParam(required = false) String nome) {
		try {
			List<ContaCorrente> contaCorrente = new ArrayList<ContaCorrente>();
			if (nome == null)
				contaContaService.findAll().forEach(contaCorrente::add);
			else if (contaCorrente.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(contaCorrente, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<String> getContaCorrenteById(@PathVariable("id") Long id) {
		ContaCorrente contaCorrente = contaContaService.findById(id);
		if (contaCorrente != null) {
			return new ResponseEntity<>("Saldo " + contaCorrente.getSaldo(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/transferencia/{id}")
	public ResponseEntity<String> transferencia(@PathVariable("id") long id, @RequestBody ContaCorrente contaCorrente) {

		ContaCorrente cc = contaContaService.findById(id);
		if (cc != null) {
			cc.setId(id);
			cc.setSaldo(cc.getSaldo());
			contaContaService.transferir(cc);
			return new ResponseEntity<>("Transferencia feita com sucesso!", HttpStatus.OK);
		}
		return new ResponseEntity<>("Não foi possivel fazer a transferencia! id= " + id, HttpStatus.NOT_FOUND);
	}

}
