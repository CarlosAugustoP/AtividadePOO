
package br.gov.cesarschool.poo.bonusvendas.negocio;

import java.time.LocalDate;

import br.gov.cesarschool.poo.bonusvendas.dao.VendedorDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.negocio.geral.ValidadorCPF;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Endereco;

public class VendedorMediator {
	private VendedorDAO repositorioVendedor;
	private AcumuloResgateMediator caixaDeBonusMediator;
	
  private static VendedorMediator instance;
  public static VendedorMediator getInstancia() {//singleton
    if (instance == null) {
      instance = new VendedorMediator();
    }
    return instance;
  }
  
/* sem parâmetros, deve inicializar o atributo repositorioVendedor com uma nova
instância do VendedorDAO, e o caixaDeBonusMediator com a instância única da classe
CaixaDeBonusMediator.*/
  public VendedorMediator() {
    repositorioVendedor = new VendedorDAO();
    caixaDeBonusMediator = AcumuloResgateMediator.getInstancia();
  }
  public Vendedor buscar(String cpf) {
	  return repositorioVendedor.buscar(cpf);
	}
  public String alterar(Vendedor vendedor) {
	    ResultadoInclusaoVendedor resultado = validar(vendedor);    
	    if (resultado.getMensagemErroValidacao() != null) {
	        return resultado.getMensagemErroValidacao();
	    }	    
	    Vendedor existenteVendedor = repositorioVendedor.buscar(vendedor.getCpf());
	    if (existenteVendedor == null) {
	        return "Vendedor inexistente";
	    } else {
	        repositorioVendedor.alterar(vendedor);
	        return null;
	    }
	}
  public ResultadoInclusaoVendedor incluir(Vendedor vendedor) {
	    ResultadoInclusaoVendedor resultado = validar(vendedor);
	    if (resultado.getMensagemErroValidacao() != null) {
	        return resultado;
	    }

	    if (repositorioVendedor.incluir(vendedor)) {
	        long numeroCaixaDeBonus = caixaDeBonusMediator.gerarCaixaDeBonus(vendedor);

	        if (numeroCaixaDeBonus == 0) {
	            return new ResultadoInclusaoVendedor(0, "Caixa de bonus não foi gerada");
	        }

	        return new ResultadoInclusaoVendedor(numeroCaixaDeBonus, null);
	    } else {
	        return new ResultadoInclusaoVendedor(0, "Vendedor ja existente");
	    }
	}
  
  public ResultadoInclusaoVendedor validar(Vendedor vendedor) {
	    String cpf = vendedor.getCpf();
	    String nomeCompleto = vendedor.getNomeCompleto();
	    LocalDate dataNascimento = vendedor.getDataNascimento();
	    double renda = vendedor.getRenda();
	    LocalDate today = LocalDate.now().minusYears(17);
	    
	    if (vendedor.getCpf() == null || cpf.trim().isEmpty()) {
	        return new ResultadoInclusaoVendedor(0, "CPF nao informado");
	    }
	    if (dataNascimento == null) {
	        return new ResultadoInclusaoVendedor(0, "Data de nascimento nao informada");
	    }
	    if (vendedor.getEndereco() == null) {
	        return new ResultadoInclusaoVendedor(0, "Endereco nao informado");
	    }

	    if (vendedor.getEndereco().getPais() == null || vendedor.getEndereco().getPais().trim().isEmpty()) {
	        return new ResultadoInclusaoVendedor(0, "Pais nao informado");
	    }

	   

	    if (vendedor.getSexo() == null) {
	        return new ResultadoInclusaoVendedor(0, "Sexo nao informado");
	    }

	    if (dataNascimento.isAfter(today)) {
	        return new ResultadoInclusaoVendedor(0, "Data de nascimento invalida");
	    }

	    if (vendedor.getEndereco().getNumero() < 0) {
	        return new ResultadoInclusaoVendedor(0, "Numero menor que zero");
	    }

	    if (nomeCompleto == null) {
	        return new ResultadoInclusaoVendedor(0, "Nome completo nao informado");
	    }
	    if (vendedor.getEndereco().getLogradouro() == null || vendedor.getEndereco().getLogradouro().trim().isEmpty()) {
	        return new ResultadoInclusaoVendedor(0, "Logradouro nao informado");
	    }
	    if (vendedor.getEndereco().getLogradouro().length() < 4) {
	        return new ResultadoInclusaoVendedor(0, "Logradouro tem menos de 04 caracteres");
	    }

	   
	    if (vendedor.getEndereco().getCidade() == null || vendedor.getEndereco().getCidade().trim().isEmpty()) {
	        return new ResultadoInclusaoVendedor(0, "Cidade nao informada");
	    }

	    if (vendedor.getEndereco().getEstado() == null || vendedor.getEndereco().getEstado().trim().isEmpty()) {
	        return new ResultadoInclusaoVendedor(0, "Estado nao informado");
	    }
	    
	    if (renda < 0) {
	        return new ResultadoInclusaoVendedor(0, "Renda menor que zero");
	    }
	    if (ValidadorCPF.ehCpfValido(cpf) == false) {
	        return new ResultadoInclusaoVendedor(0, "CPF invalido");
	    }

	    else {
	        return new ResultadoInclusaoVendedor(0, null);
	    }
	}

	  }
  




 
