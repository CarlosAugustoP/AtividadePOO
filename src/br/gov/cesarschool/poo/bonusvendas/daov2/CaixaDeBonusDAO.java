package br.gov.cesarschool.poo.bonusvendas.daov2;

import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;

public class CaixaDeBonusDAO {
	private static final String BRANCO = "";
	private DAOGenerico dao;

	public CaixaDeBonusDAO() {
	    this.dao = new DAOGenerico(CaixaDeBonus.class, "Vendedor", "Lancamento");
	}

	public void incluir(CaixaDeBonus caixaBonus) throws ExcecaoObjetoJaExistente, ExcecaoObjetoNaoExistente {
        CaixaDeBonus caixaBonusBusca = buscar(caixaBonus.getNumero());

        if (caixaBonusBusca != null) {
            throw new ExcecaoObjetoJaExistente("Caixa de Bonus ja existente");
        } else {
            dao.incluir(caixaBonus);
        }
    }

	public void alterar(CaixaDeBonus caixaBonus) throws ExcecaoObjetoNaoExistente {
        CaixaDeBonus caixaBonusBusca = buscar(caixaBonus.getNumero());

        if (caixaBonusBusca == null) {
            throw new ExcecaoObjetoNaoExistente("Caixa de Bonus nao existente");
        } else {
            dao.alterar(caixaBonus);
        }
    }

	public CaixaDeBonus buscar(long codigo) throws ExcecaoObjetoNaoExistente {
        CaixaDeBonus caixa = (CaixaDeBonus) dao.buscar(BRANCO + codigo);

        if (caixa == null) {
            throw new ExcecaoObjetoNaoExistente("Caixa de Bonus nao existente");
        }

        return caixa;
    }

	public CaixaDeBonus[] buscarTodos() {
		Registro[] registros = dao.buscarTodos();
		CaixaDeBonus[] caixaBonus = new CaixaDeBonus[registros.length];
		for (int i = 0; i < registros.length; i++) {
			caixaBonus[i] = (CaixaDeBonus) registros[i];
		}
		return caixaBonus;
	}
}
