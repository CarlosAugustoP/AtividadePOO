package br.gov.cesarschool.poo.bonusvendas.dao;

import java.util.List;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;

public class CaixaDeBonusDAO{
        // Usando a Cadastro.Objetos.class
        private CadastroObjetos cadastroObjetos;

        public CaixaDeBonusDAO(){
            this.cadastroObjetos = new CadastroObjetos(CaixaDeBonus.class);
        }

        public void salvarCaixaDeBonus(CaixaDeBonus caixaDeBonus, String chave){
            cadastroObjetos.incluir(caixaDeBonus, chave);   
        }
}