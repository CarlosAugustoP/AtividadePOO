package br.gov.cesarschool.poo.bonusvendas.dao;

import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;

public class VendedorDAO {
	private DAOGenerico dao;

    public VendedorDAO() {
        this.dao = new DAOGenerico(Vendedor.class);
    }

    public boolean incluir(Vendedor vend) {
        Vendedor vendBusca = buscar(vend.getCpf());
        if (vendBusca != null) {
            return false;
        } else {
            dao.incluir(vend);
            return true;
        }
    }

    public boolean alterar(Vendedor vend) {
        Vendedor vendBusca = buscar(vend.getCpf());
        if (vendBusca == null) {
            return false;
        } else {
            dao.alterar(vend);
            return true;
        }
    }

    public Vendedor buscar(String cpf) {
        return (Vendedor) dao.buscar(cpf);
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
