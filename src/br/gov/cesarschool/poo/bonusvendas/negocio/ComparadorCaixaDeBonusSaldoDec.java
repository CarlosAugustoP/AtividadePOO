package br.gov.cesarschool.poo.bonusvendas.negocio;

import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.util.Comparador;

public class ComparadorCaixaDeBonusSaldoDec implements Comparador {
	private static ComparadorCaixaDeBonusSaldoDec instance;

	private ComparadorCaixaDeBonusSaldoDec() {
	}

	public static ComparadorCaixaDeBonusSaldoDec getInstance() {
		if (instance == null) {
			instance = new ComparadorCaixaDeBonusSaldoDec();
		}
		return instance;
	}

	@Override
	public int comparar(Object o1, Object o2) {
		if (o1 == null || o2 == null || !(o1 instanceof CaixaDeBonus) || !(o2 instanceof CaixaDeBonus)) {
			return 0; // parâmetros são inválidos
		}

		CaixaDeBonus caixa1 = (CaixaDeBonus) o1;
		CaixaDeBonus caixa2 = (CaixaDeBonus) o2;

		if (caixa1.getSaldo() > caixa2.getSaldo()) {
			return -1;
		} else if (caixa1.getSaldo() < caixa2.getSaldo()) {
			return 1;
		} else {
			return 0;
		}
	}
}
