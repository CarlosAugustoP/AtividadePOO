package br.gov.cesarschool.poo.bonusvendas.dao;

import java.util.List;
import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.lib.PersistenciaObjetos.br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

public class CaixaDeBonusDAO{
        // Usando a Cadastro.Objetos.class
        private CadastroObjetos cadastroObjetos;

        public CaixaDeBonusDAO(){
            this.CadastroObjetos = new CadastroObjetos(CaixaDeBonus.class);
        }

        public void salvarCaixaDeBonus(CaixaDeBonus caixaDeBonus, String chave){
            cadastroObjetos.incluir(caixaDeBonus, chave);   
        }
}