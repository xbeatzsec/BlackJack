

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Classe inicial que invoca o motor do jogo
 */

public class Main {
    public static JFrame menu = new JFrame();
    public static JFrame jogo = new JFrame();

    private static int pontosJogador = 0;
    private static int pontosDealer = 0;
    private static int saldoAtual = 500;

    public static BlackJack novoJogo = new BlackJack(jogo);
    private static boolean primeiraVez = true;

    public static enum ESTADO {
        MENU, JOGO
    }

    public static ESTADO currentState = ESTADO.MENU;

    public static void main(String[] args) throws InterruptedException {
        if (currentState == ESTADO.MENU) {
            abrirMenu();
        }
    }

    public static void abrirMenu() {
        menu.setTitle("Piaget Casino");
        menu.setSize(1130, 665);
        menu.setLocationRelativeTo(null);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setResizable(false);
        Menu menuIncial = new Menu();
        menu.add(menuIncial);
        menu.setVisible(true);
    }

    public static Thread refreshJogoThread = new Thread() {
        public void run() {
            while (true) {
                novoJogo.guiTeste.refresh(pontosJogador, pontosDealer - 1, novoJogo.cartaDealerViradaParaBaixo);
                novoJogo.guiTeste.refreshSaldo(saldoAtual);

            }
        }
    };

    public static Thread checkThreadJogo = new Thread() {
        public void run() {
            while (true) {
                if (primeiraVez || novoJogo.fimDaRonda) {
                    if (novoJogo.dealerGanhou) {
                        pontosDealer++;
                        saldoAtual -= BlackJack.valorAposta;
                    } else {
                        pontosJogador++;
                        saldoAtual += BlackJack.valorAposta;
                    }
                    jogo.getContentPane().removeAll();
                    novoJogo = new BlackJack(jogo);
                    novoJogo.inicioJogo();
                    primeiraVez = false;
                }
            }
        }
    };
}