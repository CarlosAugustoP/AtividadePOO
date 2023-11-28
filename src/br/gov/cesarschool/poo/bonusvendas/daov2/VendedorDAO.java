package br.gov.cesarschool.poo.bonusvendas.daov2;

import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;


public class VendedorDAO {
	private DAOGenerico dao;

	public VendedorDAO() {
	    this.dao = new DAOGenerico(Vendedor.class, "CaixaDeBonusDAO", "LancamentoBonusDAO");
	}

	public void incluir(Vendedor vend) throws ExcecaoObjetoJaExistente, ExcecaoObjetoNaoExistente {
        Vendedor vendBusca = buscar(vend.getCpf());

        if (vendBusca != null) {
            throw new ExcecaoObjetoJaExistente("Vendedor ja existente");
        } else {
            dao.incluir(vend);
        }
    }

	public void alterar(Vendedor vend) throws ExcecaoObjetoNaoExistente {
        Vendedor vendBusca = buscar(vend.getCpf());

        if (vendBusca == null) {
            throw new ExcecaoObjetoNaoExistente("Vendedor nao existente");
        } else {
            dao.alterar(vend);
        }
    }

    public Vendedor buscar(String cpf) throws ExcecaoObjetoNaoExistente {
        Vendedor vendedor = (Vendedor) dao.buscar(cpf);

        if (vendedor == null) {
            throw new ExcecaoObjetoNaoExistente("Vendedor nao existente");
        }

        return vendedor;
    }

    public Vendedor[] buscarTodos() {
        Registro[] registros = dao.buscarTodos();
        Vendedor[] vends = new Vendedor[registros.length];
        for (int i = 0; i < registros.length; i++) {
            vends[i] = (Vendedor) registros[i];
        }
        return vends;
    }
}
