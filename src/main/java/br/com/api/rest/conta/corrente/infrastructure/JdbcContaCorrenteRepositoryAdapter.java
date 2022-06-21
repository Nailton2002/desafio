package br.com.api.rest.conta.corrente.infrastructure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.api.rest.conta.corrente.core.domain.ContaCorrente;
import br.com.api.rest.conta.corrente.core.ports.ContaCorrenteRepository;


@Repository
public class JdbcContaCorrenteRepositoryAdapter implements ContaCorrenteRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int save(ContaCorrente contaCorrente) {
		int idCorrentista = 0;
		int idContaCorrente = 0;
		try {		

			idCorrentista = jdbcTemplate.update("INSERT INTO correntista (nome, cpf, dataNascimento) VALUES(?,?,?)",
					new Object[] { contaCorrente.getCorrentista().getNome(), contaCorrente.getCorrentista().getCpf(),
							contaCorrente.getCorrentista().getDataNascimento() });

		} catch (Exception e) {
			throw e;
		} finally {
			idContaCorrente = jdbcTemplate.update(
					"INSERT INTO contacorrente (id_correntista, id_agencia, limite, saldo, ativa) VALUES(?,?,?,?,?)",
					new Object[] {idCorrentista, contaCorrente.getAgencia().getId(), contaCorrente.getLimite(), contaCorrente.getSaldo(),
							'T'});
		}
		return idContaCorrente;
	}

	@Override
	public int transferir(ContaCorrente contaCorrente) {
		return jdbcTemplate.update("UPDATE contacorrente SET id_Agencia=?, saldo=? WHERE id=?",
				new Object[] { contaCorrente.getSaldo(), contaCorrente.getId() });
	}

	@Override
	public List<ContaCorrente> findAll() {
		return jdbcTemplate.query(" SELECT * FROM correntista C INNER JOIN contaCorrente E ON C.id = E.id_Correntista ",
				BeanPropertyRowMapper.newInstance(ContaCorrente.class));
	}

	@Override
	public ContaCorrente findById(Long id) {
		try {
			ContaCorrente contaCorrente = jdbcTemplate.queryForObject("SELECT * FROM contaCorrente WHERE id=?",

					BeanPropertyRowMapper.newInstance(ContaCorrente.class), id);
			return contaCorrente;
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}


}
