/*
 * Atributos: devem ser privados
• numeroCaixaDeBonus - long
• valor – double
• dataHoraLancamento – java.time.LocalDateTime
Construtor: deve inicializar todos os atributos.
Métodos:
• gets públicos para todos os atributos.
 */

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