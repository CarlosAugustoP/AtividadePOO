package br.gov.cesarschool.poo.bonusvendas.negocio.geral;

class Main {
    public static void main(String[] args) {
        boolean result = ValidadorCPF.ehCpfValido("06474255480");
        System.out.println(result); 
    }
}
