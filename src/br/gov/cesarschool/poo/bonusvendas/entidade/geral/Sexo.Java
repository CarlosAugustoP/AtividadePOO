package br.gov.cesarschool.poo.bonusvendas.entidade.geral;

public enum Sexo{
    MASCULINO(1,"Masculino"),
    FEMININO(2,"Feminino");

    int codigo;
    String descricao;

    Sexo(int codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

}