package br.gov.cesarschool.poo.bonusvendas.negocio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.gov.cesarschool.poo.bonusvendas.dao.CaixaDeBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.dao.LancamentoBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonusCredito;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonusDebito;
import br.gov.cesarschool.poo.bonusvendas.entidade.TipoResgate;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.util.Ordenadora;

public class AcumuloResgateMediator {
	private static AcumuloResgateMediator instance;

	private CaixaDeBonusDAO repositorioCaixaBonus;
	private LancamentoBonusDAO repositorioLancamento;

	// Construtor privado
	private AcumuloResgateMediator() {
		// Inicializa os atributos com novas instâncias
		this.repositorioCaixaBonus = new CaixaDeBonusDAO();
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
			if (repositorioCaixaBonus.buscar(numCaixaDeBonus) == null) {
				CaixaDeBonus caixa = new CaixaDeBonus(numCaixaDeBonus);
				repositorioCaixaBonus.incluir(caixa);
				return numCaixaDeBonus;
			} else {
				return 0;
			}
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public String acumularBonus(long num, double valor) {
		LocalDateTime dataHoraLancamento = LocalDateTime.now(); // obter a data e hora do lançamento
		if (valor <= 0) {
			return "Valor menor ou igual a zero";
		}
		CaixaDeBonus caixaDeBonus = repositorioCaixaBonus.buscar(num);
		if (caixaDeBonus == null) {
			return "Caixa de bonus inexistente";
		} else {
			caixaDeBonus.creditar(valor);
			repositorioCaixaBonus.alterar(caixaDeBonus);
			// criar um novo lançamento de bônus com a data e hora do lançamento
			LancamentoBonusCredito lancamento = new LancamentoBonusCredito(num, valor, dataHoraLancamento);
			repositorioLancamento.incluir(lancamento);
			return null;

		}
	}

	public String resgatar(long numeroCaixaDeBonus, double valor, TipoResgate tipo) {
		LocalDateTime dataHoraLancamento = LocalDateTime.now();
		if (valor <= 0) {
			return "Valor menor ou igual a zero";
		}

		CaixaDeBonus caixaDeBonus = repositorioCaixaBonus.buscar(numeroCaixaDeBonus);
		if (caixaDeBonus == null) {
			return "Caixa de bonus inexistente";
		}

		if (caixaDeBonus.getSaldo() < valor) {
			return "Saldo insuficiente";
		}

		caixaDeBonus.debitar(valor); // Usando o método debitar da CaixaDeBonus
		repositorioCaixaBonus.alterar(caixaDeBonus);
		LancamentoBonusDebito lancamentoResgate = new LancamentoBonusDebito(tipo, numeroCaixaDeBonus, valor,
				dataHoraLancamento);
		repositorioLancamento.incluir(lancamentoResgate);

		return null;
	}

	public CaixaDeBonus[] listaCaixaDeBonusPorSaldoMaior(double saldoInicial) {

		CaixaDeBonus[] todasCaixas = repositorioCaixaBonus.buscarTodos();

		CaixaDeBonus[] caixasFiltradas = Arrays.stream(todasCaixas).filter(caixa -> caixa.getSaldo() >= saldoInicial)
				.toArray(CaixaDeBonus[]::new);

		// caixasFiltradas é um subtipo de Object ent n precisa de cast,
		// ComparadorCaixaDeBonusSaldoDec.getInstance é de uma classe
		// que implementa Comparador e tem uma comparação propria por isso passa como
		// parametro
		Ordenadora.ordenar(caixasFiltradas, ComparadorCaixaDeBonusSaldoDec.getInstance());

		return caixasFiltradas;
	}

	// ve se da pra ignorar o tanto de warning
	public LancamentoBonus[] listaLancamentosPorFaixaData(LocalDate d1, LocalDate d2) {
		// Obter todos os lançamentos do DAO
		LancamentoBonus[] todosLancamentos = repositorioLancamento.buscarTodos();

		// Filtrar lançamentos com base nas datas

		List lancamentosFiltrados = new ArrayList();
		for (Object lancamento : todosLancamentos) {
			LocalDate dataLancamento = ((LancamentoBonus) lancamento).getDataHoraLancamento().toLocalDate();

			if ((dataLancamento.isEqual(d1) || dataLancamento.isAfter(d1))
					&& (dataLancamento.isEqual(d2) || dataLancamento.isBefore(d2))) {
				lancamentosFiltrados.add(lancamento);
			}
		}

		// Ordenar a lista filtrada por data e hora em ordem decrescente
		Collections.sort(lancamentosFiltrados, ComparadorLancamentoBonusDHDec.getInstance());

		// Converter a lista ordenada para um array
		LancamentoBonus[] resultado = (LancamentoBonus[]) lancamentosFiltrados.toArray(new LancamentoBonus[0]);

		return resultado;
	}

}