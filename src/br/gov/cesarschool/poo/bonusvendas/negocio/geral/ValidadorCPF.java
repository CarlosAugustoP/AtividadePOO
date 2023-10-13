package br.gov.cesarschool.poo.bonusvendas.negocio.geral;

public class ValidadorCPF {

    private ValidadorCPF() {
       
    }

    public static boolean ehCpfValido(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            return false;
        }
        int[] digitos = new int[9];
        for (int i = 0; i < 9; i++) {
            digitos[i] = Character.getNumericValue(cpf.charAt(i));
        }
        int primeiroDigito = calcularDigitoVerificador(digitos, 10);
        int segundoDigito = calcularDigitoVerificador(digitos, 11);
        return primeiroDigito == Character.getNumericValue(cpf.charAt(9)) &&
               segundoDigito == Character.getNumericValue(cpf.charAt(10));
    }

    private static int calcularDigitoVerificador(int[] digitos, int pesoInicial) {
        int soma = 0;
        int peso = pesoInicial;

        for (int digito : digitos) {
            soma += digito * peso;
            peso--;
        }

        int resto = soma % 11;
        return (resto < 2) ? 0 : (11 - resto);
    }
}
