package br.com.api.rest.correntista.infrastructure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.api.rest.correntista.core.domain.Correntista;
import br.com.api.rest.correntista.core.ports.CorrentistaRepository;

@Repository
public class JdbcCorrentistaRepositoryAdapter implements CorrentistaRepository {


	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int save(Correntista correntista) {
	    return jdbcTemplate.update("INSERT INTO correntista (nome, cpf, dataNascimento) VALUES(?,?,?)",
	    		new Object[] {
	    				correntista.getNome(), correntista.getCpf(), correntista.getDataNascimento() });
	}

	@Override
	public int update(Correntista correntista) {
		return jdbcTemplate.update("UPDATE correntista SET nome=?, cpf=?, dataNascimento=? WHERE id=?", new Object[] {
				correntista.getCpf(), correntista.getNome(), correntista.getDataNascimento(), correntista.getId() });
	}

	@Override
	public Correntista findById(Long id) {
		try {
			Correntista correntista = jdbcTemplate.queryForObject("SELECT * FROM correntista WHERE id=?",
					BeanPropertyRowMapper.newInstance(Correntista.class), id);
			return correntista;
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}

	@Override
	public int deleteById(Long id) {
		return jdbcTemplate.update("DELETE FROM correntista WHERE id=?", id);
	}

	@Override
	public List<Correntista> findAll() {
		return jdbcTemplate.query("SELECT * from correntista", BeanPropertyRowMapper.newInstance(Correntista.class));
	}

	@Override
	public List<Correntista> findByPublished(boolean published) {
		return jdbcTemplate.query("SELECT * from correntista WHERE published=?",
				BeanPropertyRowMapper.newInstance(Correntista.class), published);
	}

	@Override
	public List<Correntista> findByTitleContaining(String title) {
		String q = "SELECT * from correntista WHERE title ILIKE '%" + title + "%'";
		return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Correntista.class));
	}

	@Override
	public int deleteAll() {
		return jdbcTemplate.update("DELETE from correntista");
	}
}
