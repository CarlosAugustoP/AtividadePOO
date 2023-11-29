package br.gov.cesarschool.poo.bonusvendas.daov2;

import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;

public class VendedorDAO {
    private DAOGenerico dao;

    // Construtor
    public VendedorDAO() {
        this.dao = new DAOGenerico(Vendedor.class, "Vendedor");
    }

    // Incluir um vendedor
    public void incluir(Vendedor vendedor) throws ExcecaoObjetoJaExistente {
        dao.incluir(vendedor);
    }

    // Alterar um vendedor
    public void alterar(Vendedor vendedor) throws ExcecaoObjetoNaoExistente {
        dao.alterar(vendedor);
    }

    // Excluir um vendedor pelo CPF
    public void excluir(String cpf) throws ExcecaoObjetoNaoExistente {
        dao.excluir(cpf);
    }

    // Buscar um vendedor pelo CPF
    public Vendedor buscar(String cpf) throws ExcecaoObjetoNaoExistente {
        return (Vendedor) dao.buscar(cpf);
    }

    // Buscar todos os vendedores
    public Vendedor[] buscarTodos() {
        Registro[] registros = dao.buscarTodos();
        Vendedor[] vendedores = new Vendedor[registros.length];
        
        for (int i = 0; i < registros.length; i++) {
            vendedores[i] = (Vendedor) registros[i];
        }
        
        return vendedores;
    }
}
