package br.gov.cesarschool.poo.bonusvendas.daov2;

import java.io.Serializable;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;

public class DAOGenericoTp<T extends Serializable> {
	private String nomeEntidade;
	private CadastroObjetos cadastro;

	public void incluir(T reg) throws ExcecaoObjetoJaExistente {
		String idUnico = ((Registro) reg).getIdUnico();
		try {
			buscar(idUnico);
			throw new ExcecaoObjetoJaExistente(nomeEntidade + " ja existente");
		} catch (ExcecaoObjetoNaoExistente e) {
			cadastro.incluir(reg, idUnico);
		}
	}

	public void alterar(T reg) throws ExcecaoObjetoNaoExistente {
		String idUnico = ((Registro) reg).getIdUnico();
		try {
			buscar(idUnico);
			cadastro.alterar(reg, idUnico);
		} catch (ExcecaoObjetoNaoExistente e) {
			throw new ExcecaoObjetoNaoExistente(nomeEntidade + " nao existente");
		}
	}

	public boolean excluir(String id) throws ExcecaoObjetoNaoExistente {
		try {
			T regBusca = buscar(id);
			if (regBusca == null) {
				return false;
			} else {
				cadastro.excluir(id);
				return true;
			}
		} catch (ExcecaoObjetoNaoExistente e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public T buscar(String id) throws ExcecaoObjetoNaoExistente {
		if (cadastro.buscar(id) == null) {
			throw new ExcecaoObjetoNaoExistente(nomeEntidade + " nao existente");
		} else {
			return (T) cadastro.buscar(id);
		}
	}


	@SuppressWarnings("unchecked")
	public T[] buscarTodos() {
		Serializable[] regs = cadastro.buscarTodos();
		T[] regsRet = (T[]) new Object[regs.length];

		int i = 0;
		while (i < regs.length) {
			regsRet[i] = (T) regs[i];
			i++;
		}
		return regsRet;
	}
}
