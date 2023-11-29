package br.gov.cesarschool.poo.bonusvendas.negociov2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.gov.cesarschool.poo.bonusvendas.daov2.CaixaDeBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.daov2.LancamentoBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonusCredito;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonusDebito;
import br.gov.cesarschool.poo.bonusvendas.entidade.TipoResgate;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoValidacao;
import br.gov.cesarschool.poo.bonusvendas.negocio.ComparadorCaixaDeBonusSaldoDec;
import br.gov.cesarschool.poo.bonusvendas.negocio.ComparadorLancamentoBonusDHDec;
import br.gov.cesarschool.poo.bonusvendas.util.Ordenadora;

public class AcumuloResgateMediator {
	private static AcumuloResgateMediator instance;

	private CaixaDeBonusDAO repositorioCaixaBonus;
	private LancamentoBonusDAO repositorioLancamento;

	private AcumuloResgateMediator() {
		this.repositorioCaixaBonus = new CaixaDeBonusDAO();
		this.repositorioLancamento = new LancamentoBonusDAO();
	}

	public static AcumuloResgateMediator getInstancia() {
		if (instance == null) {
			instance = new AcumuloResgateMediator();

		}
		return instance;
	}

	public long gerarCaixaDeBonus(Vendedor vendedor) throws ExcecaoObjetoJaExistente {
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

			repositorioCaixaBonus.incluir(new CaixaDeBonus(numCaixaDeBonus));

			return numCaixaDeBonus;
		} catch (NumberFormatException e) {
			throw new ExcecaoObjetoJaExistente("Erro na geração do número da caixa de bônus");
		}
	}

	public void acumularBonus(long num, double valor) throws ExcecaoObjetoNaoExistente, ExcecaoValidacao {
		LocalDateTime dataHoraLancamento = LocalDateTime.now();

		if (valor <= 0) {
			throw new ExcecaoValidacao("Valor menor ou igual a zero");
		}

		CaixaDeBonus caixaDeBonus = repositorioCaixaBonus.buscar(num);

		//ver se precisa tirar
		if (caixaDeBonus == null) {
			throw new ExcecaoObjetoNaoExistente("Caixa de bonus inexistente");
		} else {
			try {
				caixaDeBonus.creditar(valor);
				repositorioCaixaBonus.alterar(caixaDeBonus);

				LancamentoBonusCredito lancamento = new LancamentoBonusCredito(num, valor, dataHoraLancamento);
				repositorioLancamento.incluir(lancamento);
			} catch (ExcecaoObjetoJaExistente e) {

				throw new ExcecaoValidacao("Inconsistência no cadastro de lançamento");
			}
		}
	}

	public void resgatar(long numeroCaixaDeBonus, double valor, TipoResgate tipo)
			throws ExcecaoObjetoNaoExistente, ExcecaoValidacao {

		LocalDateTime dataHoraLancamento = LocalDateTime.now();

		if (valor <= 0) {
			throw new ExcecaoValidacao("Valor menor ou igual a zero");
		}

		CaixaDeBonus caixaDeBonus = repositorioCaixaBonus.buscar(numeroCaixaDeBonus);

		if (caixaDeBonus == null) {
			throw new ExcecaoObjetoNaoExistente();
		}

		if (caixaDeBonus.getSaldo() < valor) {
			throw new ExcecaoValidacao("Saldo insuficiente");
		}

		try {
			caixaDeBonus.debitar(valor);
			repositorioCaixaBonus.alterar(caixaDeBonus);

			LancamentoBonusDebito lancamentoResgate = new LancamentoBonusDebito(tipo, numeroCaixaDeBonus, valor,
					dataHoraLancamento);
			repositorioLancamento.incluir(lancamentoResgate);

		} catch (ExcecaoObjetoJaExistente e) {
			 
			throw new ExcecaoValidacao("Inconsistência no cadastro de lançamento");
		}
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