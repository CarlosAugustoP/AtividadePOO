package br.gov.cesarschool.poo.bonusvendas.entidade;

import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Sexo;
import java.time.LocalDate;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Endereco;

public class Vendedor {
    private String cpf;
    private String nomeCompleto;
    private Sexo sexo;
    private LocalDate dataNascimento;
    private double renda;
    private Endereco endereco;

    public Vendedor(String cpf, String nomeCompleto, Sexo sexo, LocalDate dataNascimento, double renda, Endereco endereco){
        this.cpf = cpf;
        this.nomeCompleto = nomeCompleto;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.renda = renda;
        this.endereco = endereco;
    }
    public void setNomeCompleto(String nomeCompleto){
        this.nomeCompleto = nomeCompleto;
    }
    public void setSexo(Sexo sexo){
        this.sexo = sexo;
    }
    public void setDataNascimento(LocalDate dataNascimento){
        this.dataNascimento = dataNascimento;
    }
    public void setRenda(double renda){
        this.renda = renda;
    }
    public void setEndereco(Endereco endereco){
        this.endereco = endereco;
    }
}
