package br.gov.cesarschool.poo.bonusvendas.excecoes;

public class ExcecaoObjetoNaoExistente extends Exception {

	// Construtor sem parâmetros
	public ExcecaoObjetoNaoExistente() {
		super("Objeto nao existente");
	}

	// Construtor que recebe uma mensagem como parâmetro
	public ExcecaoObjetoNaoExistente(String mensagem) {
		super(mensagem);
	}
}