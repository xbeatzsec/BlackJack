package model;

/**
 * Enumerado que representa a entidade Naipe. Possui 4
 * valores possiveis: ESPADAS, COPAS, PAUS e OUROS e
 * o seu valor pode ser acedido via Naipe.PAUS, Naipe.ESPADAS, etc.
 */

public enum Naipe {
    ESPADAS("Espadas", "E"),
    COPAS("Copas", "C"),
    PAUS("Paus", "P"),
    OUROS("Ouros", "O");


    private String nomeNaipe;
    private String simboloNaipe;

    Naipe(String nomeNaipe, String simboloNaipe) {
        this.nomeNaipe = nomeNaipe;
        this.simboloNaipe = simboloNaipe;
    }

    /**
     *  Devolve uma descricao do naipe.
     *  @return the name of the suit.
     */
    public String getNomeNaipe() {
        return nomeNaipe;
    }

    /**
     *  O símbolo associado a este naipe.
     *  Devolve o símbolo, que normalmente constitui um único carácter,
     *  sob a forma de uma String.
     *  @return string que contem o simbolo que representa o naipe.
     */
    public String getSimboloNaipe() {
        return simboloNaipe;
    }

    /**
     * Verifica se um naipe é menor que outro (seguindo a ordem neste enumerado)
     * Objetivo: ordenar os naipes numa mao para fins de apresentacao visual
     * @param n - recebe um naipe
     * @return verdadeiro se menor, falso se maior ou igual
     */
    public boolean menorQue(Naipe n){
        return this.ordinal()<n.ordinal();
    }
}
