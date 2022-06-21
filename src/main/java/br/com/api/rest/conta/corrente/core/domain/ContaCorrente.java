package br.com.api.rest.conta.corrente.core.domain;

import br.com.api.rest.agencia.core.domain.Agencia;
import br.com.api.rest.conta.corrente.core.ports.dto.request.CriaContaCorrenteRequest;
import br.com.api.rest.correntista.core.domain.Correntista;

public class ContaCorrente {

	private Long id;
	private int id_Correntista;
	private int id_Agencia;
	private Double limite;
	private Double saldo;
	private char ativa;

	private Correntista correntista;
	private Agencia agencia;

	public ContaCorrente(CriaContaCorrenteRequest request) {
		this.agencia = new Agencia(request.getAgencia());
		this.correntista = new Correntista(request.getCorrentista());
		this.limite = request.getLimite();
		this.saldo = request.getSaldoInicial();
	}

	public ContaCorrente() {
	}

	public ContaCorrente(Long id, Correntista correntista, Agencia agencia, Double limite, Double saldo, char ativa) {
		this.id = id;
		this.correntista = correntista;
		this.agencia = agencia;
		this.limite = limite;
		this.saldo = saldo;
		this.ativa = ativa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getId_Correntista() {
		return id_Correntista;
	}

	public void setId_Correntista(int id_Correntista) {
		this.id_Correntista = id_Correntista;
	}

	public int getId_Agencia() {
		return id_Agencia;
	}

	public void setId_Agencia(int id_Agencia) {
		this.id_Agencia = id_Agencia;
	}

	public Double getLimite() {
		return limite;
	}

	public void setLimite(Double limite) {
		this.limite = limite;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public char getAtiva() {
		return ativa;
	}

	public void setAtiva(char ativa) {
		this.ativa = ativa;
	}

	public Correntista getCorrentista() {
		return correntista;
	}

	public void setCorrentista(Correntista correntista) {
		this.correntista = correntista;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	@Override
	public String toString() {
		return "ContaCorrente [id=" + id + ", limite=" + limite + ", saldo=" + saldo + ", ativa=" + ativa
				+ ", correntista=" + correntista + ", agencia=" + agencia + "]";
	}

	public void getSacar(Double valor) {
		if (valor > 0 && this.getSaldo() >= valor) {
			setSaldo(getSaldo() - valor);
			System.out.println("Saque realizado!");
		} else {
			System.out.println("Não foi possível realizar o saque!");
		}
	}

	public void getTransferir(ContaCorrente contaDepositar, Double valor) {
		if (valor > 0 && this.getSaldo() >= valor) {
			setSaldo(getSaldo() - valor);
			contaDepositar.saldo = contaDepositar.getSaldo() + valor;
			System.out.println("Tranferencia feita!");
		} else {
			System.out.println("Não foi feita a tranferencia!");
		}
	}

	public void getDepositar(Double valor) {
		if (valor > 0) {
			setSaldo(getSaldo() + valor);
			System.out.println("Depósito realizado!");
		} else {
			System.out.println("Depósito não foi realizado!");
		}
	}

}
