package br.gov.cesarschool.poo.bonusvendas.dao;

import java.time.format.DateTimeFormatter;

import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;

public class LancamentoBonusDAO {
	private DAOGenerico dao;

	public LancamentoBonusDAO() {
		this.dao = new DAOGenerico(LancamentoBonus.class);
	}

	public boolean incluir(LancamentoBonus lancamento) {
		String idUnico = lancamento.getIdUnico();
		LancamentoBonus lancamentoBusca = buscar(idUnico);
		if (lancamentoBusca != null) {
			return false;
		} else {
			dao.incluir(lancamento);
			return true;
		}
	}

	public boolean alterar(LancamentoBonus lancamento) {
		String idUnico = lancamento.getIdUnico();
		LancamentoBonus lancamentoBusca = buscar(idUnico);
		if (lancamentoBusca == null) {
			return false;
		} else {
			dao.alterar(lancamento);
			return true;
		}
	}

	public LancamentoBonus buscar(String codigo) {
		return (LancamentoBonus) dao.buscar(codigo);
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
