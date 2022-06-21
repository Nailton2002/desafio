package br.com.api.rest.conta.corrente.core.ports.dto.request;

import br.com.api.rest.correntista.core.ports.dto.request.CriaCorrentistaRequest;

public class CriaContaCorrenteRequest {

	private int numeroAgencia;
	private CriaCorrentistaRequest correntista;
	private AgenciaContaCorrenteRequest agencia;
	private Double limite;
	private Double saldoInicial;

	public int getNumeroAgencia() {
		return numeroAgencia;
	}

	public void setNumeroAgencia(int numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}

	public AgenciaContaCorrenteRequest getAgencia() {
		return agencia;
	}

	public void setAgencia(AgenciaContaCorrenteRequest agencia) {
		this.agencia = agencia;
	}

	public CriaCorrentistaRequest getCorrentista() {
		return correntista;
	}

	public void setCorrentista(CriaCorrentistaRequest correntista) {
		this.correntista = correntista;
	}

	public Double getLimite() {
		return limite;
	}

	public void setLimite(Double limite) {
		this.limite = limite;
	}

	public Double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

}
