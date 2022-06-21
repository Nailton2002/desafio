package br.com.api.rest.correntista.core.domain;

import java.util.Date;

import br.com.api.rest.correntista.core.ports.dto.request.CriaCorrentistaRequest;

public class Correntista {
	private int id;
	private String nome;
	private String cpf;
	private Date dataNascimento;

	public Correntista(CriaCorrentistaRequest correntistaRequest) {
		this.id = correntistaRequest.getId();
		this.nome = correntistaRequest.getNomeCompleto();
		this.cpf = correntistaRequest.getCpf();
		this.dataNascimento = correntistaRequest.getDataNascimento();		
	}

	public Correntista() {
	}

	public Correntista(int id, String nome, String cpf, Date dataNascimento) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}

	public Correntista(String nome, String cpf, Date dataNascimento) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return "Correntista [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento + "]";
	}

}
