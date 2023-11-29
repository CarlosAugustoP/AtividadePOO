package br.gov.cesarschool.poo.bonusvendas.negociov2;

import java.time.LocalDate;
import java.util.ArrayList;

import br.gov.cesarschool.poo.bonusvendas.daov2.VendedorDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ErroValidacao;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoValidacao;
import br.gov.cesarschool.poo.bonusvendas.negocio.ComparadorVendedorNome;
import br.gov.cesarschool.poo.bonusvendas.negocio.ComparadorVendedorRenda;
import br.gov.cesarschool.poo.bonusvendas.negocio.geral.ValidadorCPF;
import br.gov.cesarschool.poo.bonusvendas.util.Ordenadora;

public class VendedorMediator {
  private static VendedorMediator instance;

  public static VendedorMediator getInstancia() {
    if (instance == null) {
      instance = new VendedorMediator();
    }
    return instance;
  }

  private VendedorDAO repositorioVendedor;
  private AcumuloResgateMediator caixaDeBonusMediator;

  public VendedorMediator() {
    repositorioVendedor = new VendedorDAO();
    caixaDeBonusMediator = AcumuloResgateMediator.getInstancia();
  }

  public void validar(Vendedor vendedor) throws ExcecaoValidacao {
    ArrayList<ErroValidacao> erros = new ArrayList<>();
    if (vendedor.getCpf() == null || vendedor.getCpf().trim().isEmpty()) {
      erros.add(new ErroValidacao(0, "CPF nao informado"));
    } else if (ValidadorCPF.ehCpfValido(vendedor.getCpf()) == false) {
      erros.add(new ErroValidacao(1, "CPF invalido"));
    }
    if (vendedor.getNomeCompleto() == null || vendedor.getNomeCompleto().trim().isEmpty()) {
      erros.add(new ErroValidacao(2, "Nome completo nao informado"));
    }
    if (vendedor.getSexo() == null) {
      erros.add(new ErroValidacao(3, "Sexo nao informado"));
    }
    if (vendedor.getDataNascimento() == null) {
      erros.add(new ErroValidacao(4, "Data de nascimento nao informada"));
    } else if (vendedor.getDataNascimento().isAfter(LocalDate.now().minusYears(17))) {
      erros.add(new ErroValidacao(5, "Data de nascimento invalida"));
    }
    if (vendedor.getRenda() < 0) {
      erros.add(new ErroValidacao(6, "Renda menor que zero"));
    }
    if (vendedor.getEndereco() == null) {
      erros.add(new ErroValidacao(7, "Endereco nao informado"));
    } else{ 
      if (vendedor.getEndereco().getLogradouro() == null
        || vendedor.getEndereco().getLogradouro().trim().isEmpty()) {
        erros.add(new ErroValidacao(8, "Logradouro nao informado"));
      } else if (vendedor.getEndereco().getLogradouro().length() < 4) {
        erros.add(new ErroValidacao(9, "Logradouro tem menos de 04 caracteres"));
      } if (vendedor.getEndereco().getNumero() < 0) {
        erros.add(new ErroValidacao(10, "Numero menor que zero"));
      } if (vendedor.getEndereco().getCidade() == null || vendedor.getEndereco().getCidade().trim().isEmpty()) {
        erros.add(new ErroValidacao(11, "Cidade nao informada"));
      } if (vendedor.getEndereco().getEstado() == null || vendedor.getEndereco().getEstado().trim().isEmpty()) {
        erros.add(new ErroValidacao(12, "Estado nao informado"));
      } if (vendedor.getEndereco().getPais() == null || vendedor.getEndereco().getPais().trim().isEmpty()) {
        erros.add(new ErroValidacao(13, "Pais nao informado"));
        }
    } 
    if (!erros.isEmpty()) {
      throw new ExcecaoValidacao(erros);
    }
  }

  public long incluir(Vendedor vendedor) throws ExcecaoObjetoJaExistente, ExcecaoValidacao {
      validar(vendedor);
      repositorioVendedor.incluir(vendedor);
      long num = caixaDeBonusMediator.gerarCaixaDeBonus(vendedor);
      return num;
  }

  public void alterar(Vendedor vendedor) throws ExcecaoObjetoNaoExistente, ExcecaoValidacao {
    validar(vendedor);
    repositorioVendedor.alterar(vendedor);
  }

  public Vendedor buscar(String cpf) throws ExcecaoObjetoNaoExistente {
    return repositorioVendedor.buscar(cpf);
  }

  public Vendedor[] gerarListagemClienteOrdenadaPorNome() {
    Vendedor[] vendedores = repositorioVendedor.buscarTodos();
    Ordenadora.ordenar(vendedores, ComparadorVendedorNome.getInstance());
    return vendedores;
  }

  public Vendedor[] gerarListagemClienteOrdenadaPorRenda() {
    Vendedor[] vendedores = repositorioVendedor.buscarTodos();
    Ordenadora.ordenar(vendedores, ComparadorVendedorRenda.getInstance());
    return vendedores;
  }
}