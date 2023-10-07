package br.gov.cesarschool.poo.bonusvendas.entidade;

import java.time.LocalDateTime;

public class CaixaDeBonus {
    private long numero;
    private double saldo;
    private LocalDateTime dataHoraAtualizacao;

    public CaixaDeBonus(long numero){
        this.numero = numero;
    }
    
    void creditar(double valor){
        this.saldo = this.saldo + valor;
        this.dataHoraAtualizacao = LocalDateTime.now();
    }

    void debitar(double valor){
        this.saldo = this.saldo - valor;
        this.dataHoraAtualizacao = LocalDateTime.now();
    }

    long getNumero(){
        return this.numero;
    }

    void setNumero(long numero){
        this.numero = numero;
    }

    double getSaldo(){
        return this.saldo;
    }

    void setSaldo(double saldo){
        this.saldo = saldo;
    }

    LocalDateTime getDataHoraAtualizacao(){
        return this.dataHoraAtualizacao;
    }

}