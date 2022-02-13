

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Baralho {

    private ArrayList<Carta> baralho;

    public Baralho() {
        int naipesPossiveisNumBaralho = 4;
        int valorDosNaipesPossiveisNumBaralho = 13;
        baralho = new ArrayList<Carta>();

        // 4 naipes | 1 - Paus | 2 - Ouros | 3 - Copas | 4 - Espadas

        for (int i = 0; i < naipesPossiveisNumBaralho; i++) {
            for (int v = 0; v < valorDosNaipesPossiveisNumBaralho; v++) {
                if (v == 0) { // se for zero temos um ÁS logo o valor é 11
                    Carta carta = new Carta(i, v, 11);
                    baralho.add(carta);
                } else if (v >= 10) {
                    Carta carta = new Carta(i, v, 10);
                    baralho.add(carta);
                } else {
                    Carta carta = new Carta(i, v, v + 1);
                    baralho.add(carta);
                }
            }
        }
    }

    public Carta getCarta(int c) {
        return baralho.get(c);
    }

    public void shuffleBaralho() {
        Collections.shuffle(baralho);
    }

    public Carta removerCarta(int i) {
        return baralho.remove(i);
    }

}
