

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Carta {

    private int naipe; // paus, ouros, copas e espadas
    private int valorNaipe; // 13 cartas de cada naipe
    private int valorEmBlackJack; // vai ser o valor em blackjack da carta
    private int x;
    private int y;

    public Carta() {
        naipe = 0;
        valorNaipe = 0;
        valorEmBlackJack = 0;
    }

    public Carta(int naipeCarta, int valorDoNaipe, int valorBlackJack) {
        naipe = naipeCarta;
        valorNaipe = valorDoNaipe;
        valorEmBlackJack = valorBlackJack;
    }

    public void apresentarCarta(Graphics2D g2, boolean jogadaDealer, boolean viradaParaBaixo, int numeroDaCarta) throws IOException {
        BufferedImage cartasAgrupadas = ImageIO.read(new File("assets/cartasTodasAgrupadas.png"));
        int larguraImagem = 950;
        int alturaImagem = 392;

        BufferedImage[][] cartaApresentada = new BufferedImage[4][13];
        BufferedImage cartaViradaParaBaixo = ImageIO.read(new File("assets/cartaViradaParaBaixo.png"));

        for (int n = 0; n < 4; n++) { // naipe
            for (int vn = 0; vn < 13; vn++) { // n -> valor do naipe
                cartaApresentada[n][vn] = cartasAgrupadas.getSubimage(vn * larguraImagem / 13, n * alturaImagem / 4, larguraImagem / 13, alturaImagem / 4);
            }
        }

        if (jogadaDealer) {
            y = 75;
        } else {
            y = 400;
        }

        x = 500 + 75 * numeroDaCarta;

        if (viradaParaBaixo) {
            g2.drawImage(cartaViradaParaBaixo, x, y, null);
        } else {
            g2.drawImage(cartaApresentada[naipe][valorNaipe], x, y, null);
        }
    }

    public int getValor() {
        return valorEmBlackJack;
    }
}
