package br.gov.cesarschool.poo.bonusvendas.daov2;

import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;

public class CaixaDeBonusDAO {
    private DAOGenerico dao; 

    public CaixaDeBonusDAO() {
        this.dao = new DAOGenerico(CaixaDeBonus.class, "Caixa");
    }

    public void incluir(CaixaDeBonus caixa) throws ExcecaoObjetoJaExistente {
        dao.incluir(caixa); 
    }

    public void alterar(CaixaDeBonus caixa) throws ExcecaoObjetoNaoExistente {
        dao.alterar(caixa);
    }

    public void excluir(long numero) throws ExcecaoObjetoNaoExistente {
        dao.excluir(numero + "");
    }

    public CaixaDeBonus buscar(long numero) throws ExcecaoObjetoNaoExistente {
        return (CaixaDeBonus) dao.buscar(numero + "");
    }

    public CaixaDeBonus[] buscarTodos() {
        Registro[] rets = dao.buscarTodos();
        CaixaDeBonus[] caixas = new CaixaDeBonus[rets.length];

        int i = 0;
        while (i < rets.length) {
            caixas[i] = (CaixaDeBonus) rets[i];
            i++;
        }

        return caixas;
    } 
}
