package br.gov.cesarschool.poo.bonusvendas.entidade
public class LancamentoBonus {

    private long numeroCaixaDeBonus;
    private double valor;
    private LocalDateTime dataHoraLancamento;

    public LancamentoBonus(long numeroCaixaDeBonus, double valor, LocalDateTime dataHoraLancamento){
        this.numeroCaixaDeBonus = numeroCaixaDeBonus;
        this.valor = valor;
        this.dataHoraLancamento = dataHoraLancamento;
    }

    public long getNumeroCaixaDeBonus(){
        return numeroCaixaDeBonus;
    }

    public double getValor(){
        return valor;
    }

    public LocalDateTime getDataHoraLancamento(){
        return dataHoraLancamento;
    }
}