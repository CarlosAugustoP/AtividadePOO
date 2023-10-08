package br.gov.cesarschool.poo.bonusvendas.negocio.geral;

public class StringUtil{

    private StringUtil(){

    }
    public static boolean ehNuloOuBranco(String str){
        if(str == null){
            return true;
        }
        // Remove espaços em branco à direita e à esquerda e verifica se a string está vazia
        str = str.trim();
        return str.isEmpty();
    }
}