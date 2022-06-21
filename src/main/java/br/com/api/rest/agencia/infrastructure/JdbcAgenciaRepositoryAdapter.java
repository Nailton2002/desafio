package br.com.api.rest.agencia.infrastructure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.api.rest.agencia.core.domain.Agencia;
import br.com.api.rest.agencia.core.ports.dto.AgenciaRepository;

@Repository
public class JdbcAgenciaRepositoryAdapter implements AgenciaRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int save(Agencia agencia) {
		return jdbcTemplate.update("INSERT INTO agencia (nome, endereco) VALUES (?,?)",
				new Object[] { agencia.getNome(), agencia.getEndereco() });
	}

	@Override
	public int update(Agencia agencia) {
		return jdbcTemplate.update("UPDATE agencia SET nome=?, endereco=? WHERE id=?",
				new Object[] { agencia.getNome(), agencia.getEndereco(), agencia.getId() });
	}

	@Override
	public Agencia findById(int id) {
		try {		

			return jdbcTemplate.queryForObject("SELECT * FROM agencia WHERE id=?", new Object[]{id}, (rs, rowNum) ->
                new Agencia(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("endereco")
                ));
			
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}

	@Override
	public int deleteById(int id) {
		return jdbcTemplate.update("DELETE FROM agencia WHERE id=?", id);
	}

	@Override
	public List<Agencia> findAll() {
		 return jdbcTemplate.query("SELECT * from agencia", BeanPropertyRowMapper.newInstance(Agencia.class));
	}

	  @Override
	  public List<Agencia> findByNome(String nome) {
	    String q = "SELECT * from agencia WHERE nome ILIKE '%" + nome + "%'";
	    return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Agencia.class));
	  }
}
