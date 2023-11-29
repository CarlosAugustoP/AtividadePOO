package br.gov.cesarschool.poo.bonusvendas.daov2;

import java.io.Serializable;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;

public class DAOGenericoTp<T extends Serializable> {
    private String nomeEntidade;
    private CadastroObjetos cadastro;

    public DAOGenericoTp(Class<T> tipo, String nomeEntidade) {
        cadastro = new CadastroObjetos(tipo);
        this.nomeEntidade = nomeEntidade;
    }

    public void incluir(T entidade) throws ExcecaoObjetoJaExistente {
        String idUnico = ((Registro) entidade).getIdUnico();
        try {
            buscar(idUnico);
            throw new ExcecaoObjetoJaExistente(nomeEntidade + " já existente");
        } catch (ExcecaoObjetoNaoExistente e) {
            cadastro.incluir(entidade, idUnico);
        }
    }

    public void alterar(T entidade) throws ExcecaoObjetoNaoExistente {
        String idUnico = ((Registro) entidade).getIdUnico();
        try {
            buscar(idUnico);
            cadastro.alterar(entidade, idUnico);
        } catch (ExcecaoObjetoNaoExistente e) {
            throw new ExcecaoObjetoNaoExistente(nomeEntidade + " não existente");
        }
    }

    public boolean excluir(String id) throws ExcecaoObjetoNaoExistente {
        try {
            T entidadeBusca = buscar(id);
            if (entidadeBusca == null) {
                return false;
            } else {
                cadastro.excluir(id);
                return true;
            }
        } catch (ExcecaoObjetoNaoExistente e) {
            return false;
        }
    }

    public T buscar(String id) throws ExcecaoObjetoNaoExistente {
        if (cadastro.buscar(id) == null) {
            throw new ExcecaoObjetoNaoExistente(nomeEntidade + " não existente");
        } else {
            return (T) cadastro.buscar(id);
        }
    }

    public T[] buscarTodos() {
        Serializable[] entidades = cadastro.buscarTodos(cadastro.getTipo());
        T[] entidadesRet = (T[]) java.lang.reflect.Array.newInstance(cadastro.getTipo(), entidades.length);
        System.arraycopy(entidades, 0, entidadesRet, 0, entidades.length);
        return entidadesRet;
    }
}
