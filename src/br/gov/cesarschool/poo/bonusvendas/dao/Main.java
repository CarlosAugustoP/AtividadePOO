package br.gov.cesarschool.poo.bonusvendas.dao;

import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Endereco;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Sexo;

public class Main {
  public static void main(String[] args) {
    VendedorDAO dao = new VendedorDAO();

      // Criar um vendedor para teste
      Endereco endereco = new Endereco("Rua da Paz", 123, "Apto 101", "12345-678", "Recife", "PE", "Brasil");
      Vendedor vendedor = new Vendedor("12345678901", "João da Silva", Sexo.MASCULINO, java.time.LocalDate.now(), 5000.00, endereco);

      // Incluir o vendedor
      if (dao.incluir(vendedor)) {
          System.out.println("Vendedor incluído com sucesso!");
      } else {
          System.out.println("Falha ao incluir o vendedor.");
      }

      // Alterar o vendedor
      vendedor.setNomeCompleto("João Pedro da Silva");
      if (dao.alterar(vendedor)) {
          System.out.println("Vendedor alterado com sucesso!");
      } else {
          System.out.println("Falha ao alterar o vendedor.");
      }

      // Buscar vendedor pelo CPF
      Vendedor vendedorBuscado = dao.buscar("12345678901");
      if (vendedorBuscado != null) {
          System.out.println("Vendedor encontrado: " + vendedorBuscado.getNomeCompleto());
      } else {
          System.out.println("Vendedor não encontrado!");
      }

      // Listar todos os vendedores
      Vendedor[] todosVendedores = dao.buscarTodos();
      System.out.println("Listando todos os vendedores:");
      for (Vendedor v : todosVendedores) {
          System.out.println(v.getNomeCompleto());
      }

      // Você também pode adicionar a lógica de exclusão aqui se quiser.
  }
}
