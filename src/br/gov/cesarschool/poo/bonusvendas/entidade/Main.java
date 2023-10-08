package br.gov.cesarschool.poo.bonusvendas.entidade;

public class Main {
    public static void main(String[] args) {
        // Criar uma instância de CaixaDeBonus
        CaixaDeBonus caixa = new CaixaDeBonus(123456);

        // Exibir o número do caixa
        System.out.println("Número do Caixa: " + caixa.getNumero());

        // Creditar e debitar valores
        caixa.creditar(100.0);
        caixa.debitar(50.0);

        // Exibir o saldo e a data/hora de atualização
        System.out.println("Saldo: " + caixa.getSaldo());
        System.out.println("Data/Hora de Atualização: " + caixa.getDataHoraAtualizacao());
    }
}
