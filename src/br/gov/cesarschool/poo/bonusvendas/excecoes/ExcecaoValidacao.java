package br.gov.cesarschool.poo.bonusvendas.excecoes;

import java.util.ArrayList;
import java.util.List;

public class ExcecaoValidacao extends Exception {
  private List<ErroValidacao> errosValidacao = new ArrayList<ErroValidacao>();

  public ExcecaoValidacao(String mensagem) {
    super(mensagem);
  }

  public ExcecaoValidacao(List<ErroValidacao> errosValidacao) {
    if (errosValidacao != null) {
      this.errosValidacao = errosValidacao;
    }
  }

  public List<ErroValidacao> getErrosValidacao() {
    return errosValidacao;
  }

}