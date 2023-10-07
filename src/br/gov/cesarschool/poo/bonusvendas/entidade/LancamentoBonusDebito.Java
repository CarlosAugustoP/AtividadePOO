// Deve herdar de LancamentoBonus

public class LancamentoBonusDebito extends LancamentoBonus {
  private String tipoResgate;

  public LancamentoBonusDebito(String tipoResgate, long numeroCaixaDeBonus, double valor, LocalDateTime dataHoraLancamento) {
    this.tipoResgate = tipoResgate;
    this.numeroCaixaDeBonus = numeroCaixaDeBonus;
    this.valor = valor;
    this.dataHoraLancamento = dataHoraLancamento;
  }

  public getTipoResgate() {
    return getTipoResgate();
  }
}