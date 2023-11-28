package br.gov.cesarschool.poo.bonusvendas.daov2;

import java.io.Serializable;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;

public class DAOGenerico {
	CadastroObjetos cadastro;
	private String nomeEntidade;

	public DAOGenerico(Class<?> tipo, String... nomesEntidades) {
        // Selecione a primeira entidade da lista, ou use um padrão se a lista estiver vazia
        this.cadastro = new CadastroObjetos(tipo);
        this.nomeEntidade = nomesEntidades.length > 0 ? nomesEntidades[0] : "EntidadePadrao";
    }
	
	

	public void incluir(Registro reg) throws ExcecaoObjetoJaExistente, ExcecaoObjetoNaoExistente {
        String idUnico = reg.getIdUnico();
        Registro regBusca = buscar(idUnico);

        if (regBusca != null) {
            String mensagem = nomeEntidade + " ja existente";
            throw new ExcecaoObjetoJaExistente(mensagem);
        } else {
            cadastro.incluir(reg, idUnico);
        }
    }

	public void alterar(Registro reg) throws ExcecaoObjetoNaoExistente {
        Registro regBusca = buscar(reg.getIdUnico());

        if (regBusca == null) {
            throw new ExcecaoObjetoNaoExistente(nomeEntidade + " nao existente");
        } else {
            cadastro.alterar(reg, reg.getIdUnico());
        }
    }

	public Registro buscar(String id) throws ExcecaoObjetoNaoExistente {
        Registro registro = (Registro) cadastro.buscar(id);

        if (registro == null) {
            String mensagem = nomeEntidade + " nao existente";
            throw new ExcecaoObjetoNaoExistente(mensagem);
        }

        return registro;
    }

	public Registro[] buscarTodos() {
		Serializable[] rets = cadastro.buscarTodos();
		Registro[] registros = new Registro[rets.length];
		for (int i = 0; i < rets.length; i++) {
			registros[i] = (Registro) rets[i];
		}
		return registros;
	}
}