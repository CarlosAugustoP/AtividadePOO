package br.gov.cesarschool.poo.bonusvendas.entidade;

import java.util.Date;

/* Lançamento Bonus aqui
 * Atributos: devem ser privados
• numeroCaixaDeBonus - long
• valor – double
• dataHoraLancamento – java.time.LocalDateTime
Construtor: deve inicializar todos os atributos.
Métodos:
• gets públicos para todos os atributos.
 */

public class LancamentoBonusCredito extends LancamentoBonus {
    private long numeroCaixaDeBonus;
    private double valor;
    private Date dataHoraLancamento;
    
    }
}

