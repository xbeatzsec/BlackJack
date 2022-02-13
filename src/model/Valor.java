package model;

/**
 *  Enumerado que especifica o valor das cartas de um
 *  determinado baralho. Os valores estao distribuidos da seguinte forma:
 *  - As, 2, 3, 4, 5, 6, 7, 8, 9, 10, Rei, Dama, Valete
 *  Podemos aceder ao valor atraves de Valor.AS, Valor.DOIS, etc.
 */
public enum Valor {

    AS("As", "A"),
    DOIS("Dois", "2"),
    TRES("Tres", "3"),
    QUATRO("Quatro", "4"),
    CINCO("Cinco", "5"),
    SEIS("Seis", "6"),
    SETE("Sete", "7"),
    OITO("Oito", "8"),
    NOVE("Nove", "9"),
    DEZ("Dez", "10"),
    REI("Rei", "K"),
    DAMA("Dama", "Q"),
    VALETE("Valete", "J");

    private final String nomeValor;
    private final String simboloValor;

    Valor(String nameValue, String symbolValue) {
        nomeValor = nameValue;
        simboloValor = symbolValue;
    }

    /**
     * Devolve a descricao do valor de uma carta.
     * @return nomeValor - o valor da carta.
     */
    public String getNomeValor() {
        return nomeValor;
    }


    /**
     * O simbolo associado a este valor da carta. Devolve o simbolo mais
     * granular da carta, que normalmente e' representado apenas por um
     * caracter, a excepcao da carta Dez.
     * @return simboloValor - o simbolo que representa o valor da carta.
     */
    public String getSimboloValor() {
        return simboloValor;
    }

    /**
     * Verifica se o valor de uma carta é menor que outro (seguindo a ordem neste enumerado)
     * Objetivo: ordenar os valores numa mao para fins de apresentacao visual
     * @param r - recebe um valor (rank)
     * @return verdadeiro se menor, falso se maior ou igual
     */
    public boolean menorQue(Valor r) {
        return this.ordinal() < r.ordinal();
    }
}


