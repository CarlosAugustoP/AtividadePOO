package br.gov.cesarschool.poo.bonusvendas.dao;

import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;

public class Main {
  public static void main(String[] args) {
        CaixaDeBonusDAO dao = new CaixaDeBonusDAO();

        // Criar um vendedor para teste
        CaixaDeBonus caixa = new CaixaDeBonus(10);

        if (dao.incluir(caixa)) {
            System.out.println("Vendedor incluído com sucesso!");
        } else {
            System.out.println("Falha ao incluir o vendedor.");
        }

        // // Alterar o vendedor
        // vendedor.setNomeCompleto("João Pedro da Silva");
        // if (dao.alterar(vendedor)) {
        //     System.out.println("Vendedor alterado com sucesso!");
        // } else {
        //     System.out.println("Falha ao alterar o vendedor.");
        // }
        caixa = dao.buscar(10);
        caixa.creditar(100);
        // Buscar vendedor pelo CPF
        dao.alterar(caixa);
         caixa = dao.buscar(10);
        if (caixa != null) {
            System.out.println("Caixa encontrado: " + caixa.getSaldo());
        } else {
            System.out.println("Caixa não encontrado!");
        }
        System.out.println("Saldo: " + caixa.getSaldo());
        // Listar todos os Caixaes
        CaixaDeBonus[] todasCaixas = dao.buscarTodos();
        System.out.println("Listando todos os caixa:");
        for (CaixaDeBonus v : todasCaixas) {
            System.out.println(v.getNumero());
        }

        // Você também pode adicionar a lógica de exclusão aqui se quiser.
  }
}
