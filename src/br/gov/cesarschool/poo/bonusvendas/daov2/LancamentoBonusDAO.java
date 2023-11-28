package br.gov.cesarschool.poo.bonusvendas.daov2;

import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;

public class LancamentoBonusDAO {
	private DAOGenerico dao;

	public LancamentoBonusDAO() {
	    this.dao = new DAOGenerico(LancamentoBonus.class, "Vendedor", "CaixaDeBonus");
	}

	public void incluir(LancamentoBonus lancamento) throws ExcecaoObjetoJaExistente, ExcecaoObjetoNaoExistente {
        String idUnico = lancamento.getIdUnico();
        LancamentoBonus lancamentoBusca = buscar(idUnico);

        if (lancamentoBusca != null) {
            throw new ExcecaoObjetoJaExistente("Lancamento Bonus ja existente");
        } else {
            dao.incluir(lancamento);
        }
    }

	public void alterar(LancamentoBonus lancamento) throws ExcecaoObjetoNaoExistente {
        String idUnico = lancamento.getIdUnico();
        LancamentoBonus lancamentoBusca = buscar(idUnico);

        if (lancamentoBusca == null) {
            throw new ExcecaoObjetoNaoExistente("Lancamento Bonus nao existente");
        } else {
            dao.alterar(lancamento);
        }
    }

	public LancamentoBonus buscar(String codigo) throws ExcecaoObjetoNaoExistente {
        LancamentoBonus lancamentoBonus = (LancamentoBonus) dao.buscar(codigo);

        if (lancamentoBonus == null) {
            throw new ExcecaoObjetoNaoExistente("Lancamento Bonus nao existente");
        }

        return lancamentoBonus;
    }

	public LancamentoBonus[] buscarTodos() {
		Registro[] registros = dao.buscarTodos();
		LancamentoBonus[] lancamentos = new LancamentoBonus[registros.length];
		for (int i = 0; i < registros.length; i++) {
			lancamentos[i] = (LancamentoBonus) registros[i];
		}
		return lancamentos;
	}

}
