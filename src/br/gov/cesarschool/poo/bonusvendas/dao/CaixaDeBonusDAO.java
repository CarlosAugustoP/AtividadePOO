package br.gov.cesarschool.poo.bonusvendas.dao;

import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;

public class CaixaDeBonusDAO {
	private static final String BRANCO = "";
	private DAOGenerico dao;

	public CaixaDeBonusDAO() {
		this.dao = new DAOGenerico(CaixaDeBonus.class);
	}

	public boolean incluir(CaixaDeBonus caixaBonus) {
		CaixaDeBonus caixaBonusBusca = buscar(caixaBonus.getNumero());
		if (caixaBonusBusca != null) {
			return false;
		} else {
			dao.incluir(caixaBonus);
			return true;
		}
	}

	public boolean alterar(CaixaDeBonus caixaBonus) {
		CaixaDeBonus caixaBonusBusca = buscar(caixaBonus.getNumero());
		if (caixaBonusBusca == null) {
			return false;
		} else {
			dao.alterar(caixaBonus);
			return true;
		}
	}

	public CaixaDeBonus buscar(long codigo) {
		return (CaixaDeBonus) dao.buscar(BRANCO + codigo);
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
