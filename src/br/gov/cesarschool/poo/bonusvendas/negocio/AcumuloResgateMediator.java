package br.gov.cesarschool.poo.bonusvendas.negocio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.gov.cesarschool.poo.bonusvendas.dao.CaixaDeBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.dao.LancamentoBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonusCredito;

import java.time.LocalDate;

import br.gov.cesarschool.poo.bonusvendas.dao.CaixaDeBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.dao.LancamentoBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.TipoResgate;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;

public class AcumuloResgateMediator {
	private static AcumuloResgateMediator instance;

//     Atributos privados
	private CaixaDeBonusDAO repositorioCaixaDeBonus;
	private LancamentoBonusDAO repositorioLancamento;

	// Construtor privado
	private AcumuloResgateMediator() {
		// Inicializa os atributos com novas instâncias
		this.repositorioCaixaDeBonus = new CaixaDeBonusDAO();
		this.repositorioLancamento = new LancamentoBonusDAO();
	}

	/***
	 * Método público para obter a instância única
	 * 
	 * @return
	 */
	public static AcumuloResgateMediator getInstancia() {
		if (instance == null) {
			instance = new AcumuloResgateMediator();

		}
		return instance;
	}

	public long gerarCaixaDeBonus(Vendedor vendedor) {
		String cpf = vendedor.getCpf();
		cpf = cpf.replaceAll("[^0-9]", "");
		if (cpf.length() < 2) {
			return 0;
		}
		String cpfNumerico = cpf.substring(0, cpf.length() - 2);
		String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String numCaixaDeBonusString = cpfNumerico + hoje;
		try {
			long numCaixaDeBonus = Long.parseLong(numCaixaDeBonusString);
			if (repositorioCaixaDeBonus.buscar(numCaixaDeBonus) == null) {
				CaixaDeBonus caixa = new CaixaDeBonus(numCaixaDeBonus);
				repositorioCaixaDeBonus.incluir(caixa);
				return numCaixaDeBonus;
			} else {
				return 0;
			}
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public String acumularBonus(long numeroCaixaDeBonus, double valor) {
		if (valor <= 0) {
			return "Valor menor ou igual a zero";
		}

		CaixaDeBonus caixaDeBonus = repositorioCaixaDeBonus.buscar(numeroCaixaDeBonus);
		if (caixaDeBonus == null) {
			return "Caixa de bônus inexistente";
		}

		caixaDeBonus.creditar(valor);
		repositorioCaixaDeBonus.alterar(caixaDeBonus);

		// Obtenha a data e hora do lançamento
		LocalDateTime dataHoraLancamento = LocalDateTime.now();

		// Crie um novo lançamento de bônus com a data e hora do lançamento
		LancamentoBonusCredito lancamentoCredito = new LancamentoBonusCredito(numeroCaixaDeBonus, valor,
				dataHoraLancamento);
		repositorioLancamento.incluir(lancamentoCredito);

		return null;
	}

	public String resgatar(long numeroCaixaDeBonus, double valor, TipoResgate tipo) {
		if (valor <= 0) {
			return "Valor menor ou igual a zero";
		}

		CaixaDeBonus caixaDeBonus = repositorioCaixaDeBonus.buscar(numeroCaixaDeBonus);
		if (caixaDeBonus == null) {
			return "Caixa de bonus inexistente";
		}

		if (caixaDeBonus.getSaldo() < valor) {
			return "Saldo insuficiente";
		}

		caixaDeBonus.debitar(valor); // Usando o método debitar da CaixaDeBonus
		repositorioCaixaDeBonus.alterar(caixaDeBonus);
		// LancamentoBonusResgate lancamentoResgate = new
		// LancamentoBonusResgate(caixaDeBonus, valor, tipo);
		// repositorioLancamento.incluirLancamento(lancamentoResgate);

		return null;
	}

}