package br.gov.cesarschool.poo.bonusvendas.entidade;

import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;

import java.time.LocalDateTime;

public class CaixaDeBonus extends Registro {
	private static final long serialVersionUID = 3L;
	private long numero;
	private double saldo;
	private LocalDateTime dataHoraAtualizacao;

	public CaixaDeBonus(long numero) {
		super();
		this.numero = numero;
	}

	public String getIdUnico() {
		return Long.toString(numero);
	}

	public long getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	public LocalDateTime getDataHoraAtualizacao() {
		this.dataHoraAtualizacao = LocalDateTime.now();
		return this.dataHoraAtualizacao;
	}

	public void creditar(double valor) {
		saldo = saldo + valor;
		dataHoraAtualizacao = LocalDateTime.now();
	}

	public void debitar(double valor) {
		saldo = saldo - valor;
		dataHoraAtualizacao = LocalDateTime.now();
	}
}
