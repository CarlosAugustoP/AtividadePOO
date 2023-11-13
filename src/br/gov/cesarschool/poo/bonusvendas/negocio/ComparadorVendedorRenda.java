package br.gov.cesarschool.poo.bonusvendas.negocio;

import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.util.Comparador;

public class ComparadorVendedorRenda implements Comparador {
	private static ComparadorVendedorRenda instance;

	private ComparadorVendedorRenda() {
	}

	public static ComparadorVendedorRenda getInstance() {
		if (instance == null) {
			instance = new ComparadorVendedorRenda();
		}
		return instance;
	}

	public int comparar(Object o1, Object o2) {

		if (o1 == null || o2 == null || !(o1 instanceof Vendedor) || !(o2 instanceof Vendedor)) {
			return 0; // parâmetros são inválidos
		}

		Vendedor vendedor1 = (Vendedor) o1;
		Vendedor vendedor2 = (Vendedor) o2;

		if (vendedor1.getRenda() > vendedor2.getRenda()) {
			return 1;
		} else if (vendedor1.getRenda() < vendedor2.getRenda()) {
			return -1;
		} else {
			return 0;
		}
	}

}
