package br.com.api.rest.agencia.domain;

import br.com.api.rest.conta.corrente.core.ports.dto.request.AgenciaContaCorrenteRequest;

public class Agencia {

	private int id;
	private String nome;
	private String endereco;

	public Agencia() {
	}

	public Agencia(AgenciaContaCorrenteRequest agenciaContaCorrenteRequst) {
		this.id = agenciaContaCorrenteRequst.getId();
		this.nome = agenciaContaCorrenteRequst.getNome();
		this.endereco = agenciaContaCorrenteRequst.getEndereco();
	}

	public Agencia(int id, String nome, String endereco) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
	}

	public Agencia(String nome, String endereco) {
		this.nome = nome;
		this.endereco = endereco;
	}

	public Agencia(String nome, String endereco, boolean b) {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Agencia [id=" + id + ", nome=" + nome + ", endereco=" + endereco + "]";
	}

}
