package br.gov.cesarschool.poo.bonusvendas.negocio.geral;

public class ValidadorCPF {

	public static boolean ehCpfValido(String CPF) {
		if (CPF == null || CPF.length() != 11)
			return false;

		if (CPF.matches("(\\d)\\1{10}"))
			return false;

		int[] digits = new int[11];

		for (int i = 0; i < 11; i++) {
			digits[i] = CPF.charAt(i) - '0';
		}
		int sm = 0;
		for (int i = 0; i < 9; i++) {
			sm += digits[i] * (10 - i);
		}

		int r = 11 - (sm % 11);
		char dig10 = (r == 10 || r == 11) ? '0' : (char) (r + '0');

		sm = 0;
		for (int i = 0; i < 10; i++) {
			sm += digits[i] * (11 - i);
		}

		r = 11 - (sm % 11);
		char dig11 = (r == 10 || r == 11) ? '0' : (char) (r + '0');

		return dig10 == CPF.charAt(9) && dig11 == CPF.charAt(10);
	}

}