package br.gov.cesarschool.poo.bonusvendas.negocio;

import java.util.Comparator;

import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;

public class ComparadorLancamentoBonusDHDec implements Comparator {
	private static ComparadorLancamentoBonusDHDec instance;

	private ComparadorLancamentoBonusDHDec() {
	}

	public static ComparadorLancamentoBonusDHDec getInstance() {
		if (instance == null) {
			instance = new ComparadorLancamentoBonusDHDec();
		}
		return instance;
	}

	@Override
	public int compare(Object o1, Object o2) {

		if (o1 == null || o2 == null || !(o1 instanceof LancamentoBonus) || !(o2 instanceof LancamentoBonus)) {
			return 0; // parâmetros são inválidos
		}

		LancamentoBonus lancamento1 = (LancamentoBonus) o1;
		LancamentoBonus lancamento2 = (LancamentoBonus) o2;

		return lancamento2.getDataHoraLancamento().compareTo(lancamento1.getDataHoraLancamento());
	}
}
