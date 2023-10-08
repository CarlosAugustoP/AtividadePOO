package br.gov.cesarschool.poo.bonusvendas.negocio.geral;

public class ValidadorCPF {

  private ValidadorCPF() {
  }

  public static boolean ehCpfValido(String cpf) {

    // REMOVE CARACTERES ESPECIAIS
    cpf = cpf.replaceAll("[^0-9]", "");

    // CPF TEM 11 DIGITOS
    if (cpf.length() != 11) {
      return false;
    }

    // VALIDA DIGITOS VERIFICADORES
    for (int i = 0; i < cpf.length(); i++) {
      if (cpf.charAt(i) != '0' && cpf.charAt(i) != '1' && cpf.charAt(i) != '2' && cpf.charAt(i) != '3'
          && cpf.charAt(i) != '4' && cpf.charAt(i) != '5' && cpf.charAt(i) != '6' && cpf.charAt(i) != '7'
          && cpf.charAt(i) != '8' && cpf.charAt(i) != '9') {
        return false;
      }
    }

    // VALIDA PRIMEIRO DIGITO VERIFICADOR
    int seq = 10;
    boolean ok = true;
    int somatorio = 0;

    for (int i = 0; i < 9; i++) {
      somatorio += Character.getNumericValue(cpf.charAt(i)) * seq;
      seq--;
    }
    if ((somatorio % 11) - 11 != Character.getNumericValue(cpf.charAt(10))) {
      ok = false;
    }

    // VALIDA SEGUNDO DIGITO VERIFICADOR
    seq = 11;
    somatorio = 0;

    for (int i = 0; i < 10; i++) {
      somatorio += Character.getNumericValue(cpf.charAt(i)) * seq;
      seq--;
    }

    if ((somatorio % 11) - 11 != Character.getNumericValue(cpf.charAt(11))) {
      ok = false;
    }

    if (ok == true) {
      return true;
    }
  }
}