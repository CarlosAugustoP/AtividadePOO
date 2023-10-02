package br.gov.cesarschool.poo.bonusvendas.entidade;

import java.time.LocalDateTime;

public class CaixaDeBonus() {
    private long int numero;
    private double saldo = 0;
    private LocalDateTime dataHoraAtualizacao;
    
    CaixaDeBonus(long int numero){
        this.numero = numero;
    }
    
    void creditar(double valor){
        this.saldo = this.saldo + valor;
        this.dataHoraAtualizacao = LocaDateTime.now();
    }

    void debitar(double valor){
        this.saldo = this.saldo - valor;
        this.dataHoraAtualizacao = LocaDateTime.now();
    }

    long int getNumero(){
        return this.numero;
    }

    void setNumero(long int numero){
        this.numero = numero;
    }

    double getSaldo(){
        return this.saldo;
    }

    void setSaldo(double saldo){
        this.saldo = saldo;
    }

    LocaDateTime getDataHoraAtualizacao(){
        return this.dataHoraAtualizacao;
    }

}