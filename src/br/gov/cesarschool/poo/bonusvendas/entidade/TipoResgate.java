package br.gov.cesarschool.poo.bonusvendas.entidade;

public enum TipoResgate {
    PRODUTO(1, "Produto"),
    SERVICO(2, "Servico"),
    CASH(3, "Cash");

    private int codigo;
    private String descricao;

    private TipoResgate(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
