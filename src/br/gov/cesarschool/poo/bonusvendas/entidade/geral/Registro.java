package br.gov.cesarschool.poo.bonusvendas.entidade.geral;

import java.io.Serializable;

public abstract class Registro implements Serializable {
	private static final long serialVersionUID = 1L;

	public abstract String getIdUnico();
}
