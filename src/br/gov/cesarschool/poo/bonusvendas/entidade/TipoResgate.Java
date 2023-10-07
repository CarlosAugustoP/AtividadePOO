package br.gov.cesarschool.poo.bonusvendas.entidade;

public Enum TipoResgate{
  PRODUTO(1, "Produto"),
  SERVICO(2, "Servico"),
  CASH(3, "Cash");
  
  private int codigo;
  private String descricao;

  TipoResgate(int codigo, String descricao){
    this.codigo = codigo;
    this.descricao = descricao;
  }
}