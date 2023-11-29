package br.gov.cesarschool.poo.bonusvendas.daov2;

import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;

public class VendedorDAO {
    private DAOGenerico dao;

    public VendedorDAO() {
        this.dao = new DAOGenerico(Vendedor.class, "Vendedor");
    }

    public void incluir(Vendedor vendedor) throws ExcecaoObjetoJaExistente {
        dao.incluir(vendedor);
    }

    public void alterar(Vendedor vendedor) throws ExcecaoObjetoNaoExistente {
        dao.alterar(vendedor);
    }

    public void excluir(String cpf) throws ExcecaoObjetoNaoExistente {
        dao.excluir(cpf);
    }

    public Vendedor buscar(String cpf) throws ExcecaoObjetoNaoExistente {
        return (Vendedor) dao.buscar(cpf);
    }

    public Vendedor[] buscarTodos() {
        Registro[] registros = dao.buscarTodos();
        Vendedor[] vendedores = new Vendedor[registros.length];

        int i = 0;
        while (i < registros.length) {
            vendedores[i] = (Vendedor) registros[i];
            i++;
        }

        return vendedores;
    }
}
