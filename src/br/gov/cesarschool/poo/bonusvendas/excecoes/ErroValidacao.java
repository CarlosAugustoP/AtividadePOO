package br.gov.cesarschool.poo.bonusvendas.excecoes;

public class ErroValidacao {
	
	private int codigo;
	private String mensagem;
	
	public ErroValidacao(int codigo, String mensagem) {
		super();
		this.codigo = codigo;
		this.mensagem = mensagem;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
}
