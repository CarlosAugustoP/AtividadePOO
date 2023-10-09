package br.gov.cesarschool.poo.bonusvendas.entidade;

import java.time.LocalDateTime;
import java.io.Serializable;

public class CaixaDeBonus implements Serializable {
    private long numero;
    private double saldo;
    private LocalDateTime dataHoraAtualizacao;

    public CaixaDeBonus(long numero) {
        this.numero = numero;
    }

    public CaixaDeBonus(Object numero2) {
    }

    public void creditar(double valor) {
        this.saldo = this.saldo + valor;
        this.dataHoraAtualizacao = LocalDateTime.now();
    }

    public void debitar(double valor) {
        this.saldo = this.saldo - valor;
        this.dataHoraAtualizacao = LocalDateTime.now();
    }

    public long getNumero() {
        return this.numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public LocalDateTime getDataHoraAtualizacao() {
        return this.dataHoraAtualizacao;
    }
}