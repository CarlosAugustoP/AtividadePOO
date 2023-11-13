package br.gov.cesarschool.poo.bonusvendas.negocio;

import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.util.Comparador;

public class ComparadorVendedorNome implements Comparador {
	private static ComparadorVendedorNome instance;

	private ComparadorVendedorNome() {
	}

	public static ComparadorVendedorNome getInstance() {
		if (instance == null) {
			instance = new ComparadorVendedorNome();
		}
		return instance;
	}


	@Override
	public int comparar(Object o1, Object o2) {
		if (o1 == null || o2 == null || !(o1 instanceof Vendedor) || !(o2 instanceof Vendedor)) {
            return -1; // parametros invalidos
        }

        Vendedor vendedor1 = (Vendedor) o1;
        Vendedor vendedor2 = (Vendedor) o2;

        return vendedor1.getNomeCompleto().compareTo(vendedor2.getNomeCompleto());
    }


}
