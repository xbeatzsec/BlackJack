



import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.IntConsumer;

/**
 * Controlador principal do motor de jogo BlackJack
 * Responsavel por iniciar a interface grafica e toda
 * a logica associada ao jogo
 */

public class BlackJack extends JComponent {

    ArrayList<Carta> maoDealer;
    ArrayList<Carta> maoJogador;

    public boolean cartaDealerViradaParaBaixo;
    public boolean dealerGanhou;
    private int saldoAtual;
    public static int valorAposta;
    public volatile boolean fimDaRonda;

    GUI guiTeste;
    GUI cartaComponente;

    JFrame frame;
    Baralho baralho;

    JButton botaoHit;
    JButton botaoStand;
    JButton botaoSair;
    JButton botaoApostar;

    public BlackJack(JFrame j) {
        baralho = new Baralho();
        baralho.shuffleBaralho();
        maoDealer = new ArrayList<Carta>();
        maoJogador = new ArrayList<Carta>();
        guiTeste = new GUI(maoDealer, maoJogador);
        frame = j;
        saldoAtual = 500;
        cartaDealerViradaParaBaixo = true;
        dealerGanhou = true;
        fimDaRonda = false;
    }

    public void inicioJogo() {

        Color lightRed = new Color(255, 99, 71);

        frame.setTitle("Piaget Casino");
        frame.setSize(1130, 665);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        botaoHit = new JButton("Hit");
        botaoHit.setBounds(450, 550, 100, 50);
        botaoHit.setBorderPainted(true);
        botaoHit.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        botaoStand = new JButton("Stand");
        botaoStand.setBounds(570, 550, 100, 50);
        botaoStand.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        botaoSair = new JButton("Sair");
        botaoSair.setBounds(900, 550, 190, 50);
        botaoSair.setBackground(lightRed);
        botaoSair.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        botaoApostar = new JButton("Apostar");
        botaoApostar.setBounds(50, 300, 200, 100);
        botaoApostar.setFont(new Font("Comic Sans MS", Font.BOLD, 16));

        frame.add(botaoHit);
        frame.add(botaoStand);
        frame.add(botaoSair);
        frame.add(botaoApostar);

        Color green = new Color(50, 205, 5);
        Color red = new Color(192, 0, 0);

        botaoApostar.setBackground(green);

        ImageIcon dinheiroIcon = new ImageIcon("assets/dinheiro.png");

        botaoApostar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] apostasPossiveis = new String[]{"5", "10", "25", "50", "100"};
                if (Main.saldoAtual <= 0) {
                    JOptionPane.showMessageDialog(null, "Ficou sem dinheiro, vai ser desconectado :(","Banco", JOptionPane.INFORMATION_MESSAGE , dinheiroIcon);
                    System.exit(0);
                }
                int aposta = JOptionPane.showOptionDialog(null, "Aposta a ser feita!", "Aposta", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, dinheiroIcon, apostasPossiveis, apostasPossiveis[0]);
                if (aposta == 0) {
                    valorAposta = 5;
                    saldoAtual -= 5;
                }
                if (aposta == 1) {
                    valorAposta = 10;
                    saldoAtual -= 10;
                }
                if (aposta == 2){
                    valorAposta = 25;
                    saldoAtual -= 50;
                }
                if (aposta == 3) {
                    valorAposta = 50;
                    saldoAtual -= 50;
                }
                if (aposta == 4) {
                    valorAposta = 100;
                    saldoAtual -= 100;
                }
                Main.novoJogo.iniciarJogo();

            }
        });

        botaoSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Saiste do casino do Piaget com " + Main.saldoAtual + " euros");
                System.exit(0);
            }
        });

        guiTeste = new GUI(maoDealer, maoJogador);
        guiTeste.setBounds(0, 0, 1130, 665);
        frame.add(guiTeste);
        frame.setVisible(true);
        JOptionPane.showMessageDialog(frame, "Aposta primeiro antes de jogar!!", "Piaget Casino | Aviso", JOptionPane.INFORMATION_MESSAGE, dinheiroIcon);


    }

    public void iniciarJogo() {
        for (int i = 0; i < 2; i++) {
            maoDealer.add(baralho.getCarta(i));
        }
        for (int i = 2; i < 4; i++) {
            maoJogador.add(baralho.getCarta(i));
        }
        for (int i = 0; i < 4; i++) {
            baralho.removerCarta(0);
        }

        cartaComponente = new GUI(maoDealer, maoJogador);
        cartaComponente.setBounds(0, 0, 1130, 665);
        frame.add(cartaComponente);
        frame.setVisible(true);

        verificarMao(maoDealer);
        verificarMao(maoJogador);

        botaoHit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarCarta(maoJogador);
                verificarMao(maoJogador);

                if (getSomaDaMao(maoJogador) < 17 && getSomaDaMao(maoDealer) < 17) {
                    adicionarCarta(maoDealer);
                    verificarMao(maoDealer);
                }


            }
        });
        botaoStand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                while (getSomaDaMao(maoDealer) < 17) {
                    adicionarCarta(maoDealer);
                    verificarMao(maoDealer);
                }
                if (getSomaDaMao(maoJogador) == getSomaDaMao(maoDealer)) {
                    cartaDealerViradaParaBaixo = false;
                    dealerGanhou = false;
                    JOptionPane.showMessageDialog(frame, "HOUVE UM EMPATE !!");
                    sleep();
                    fimDaRonda = true;
                }
                if ((getSomaDaMao(maoDealer) < 21) && getSomaDaMao(maoJogador) < 21) {
                    if (getSomaDaMao(maoJogador) > getSomaDaMao(maoDealer)) {
                        cartaDealerViradaParaBaixo = false;
                        dealerGanhou = false;
                        JOptionPane.showMessageDialog(frame, "JOGADOR GANHOU POR TER MELHOR MAO");
                        sleep();
                        fimDaRonda = true;
                    }
                    if (getSomaDaMao(maoDealer) > getSomaDaMao(maoJogador)) {
                        cartaDealerViradaParaBaixo = false;
                        JOptionPane.showMessageDialog(frame, "DEALER GANHOU POR TER MELHOR MAO");
                        sleep();
                        fimDaRonda = true;
                    }
                }

            }
        });


    }

    public void verificarMao(ArrayList<Carta> mao) {
        if (mao.equals(maoJogador)) {
            if (getSomaDaMao(mao) == 21) {
                cartaDealerViradaParaBaixo = false;
                dealerGanhou = false;
                JOptionPane.showMessageDialog(frame, "BLACKJACK PARA O JOGADOR");
                sleep();
                fimDaRonda = true;
            } else if (getSomaDaMao(mao) > 21) {
                cartaDealerViradaParaBaixo = false;
                JOptionPane.showMessageDialog(frame, "JOGADOR TEVE UM BUST E O DEALER GANHOU");
                sleep();
                fimDaRonda = true;
            }
        } else {
            if (getSomaDaMao(mao) == 21) {
                cartaDealerViradaParaBaixo = false;
                JOptionPane.showMessageDialog(frame, "DEALER CONSEGUIU UM BLACKJACK E GANHOU");
                sleep();
                fimDaRonda = true;
            } else if (getSomaDaMao(mao) > 21) {
                cartaDealerViradaParaBaixo = false;
                dealerGanhou = false;
                JOptionPane.showMessageDialog(frame, "DEALER TEVE UM BUST E O JOGADOR GANHOU");
                sleep();
                fimDaRonda = true;
            }
        }
    }

    public void adicionarCarta(ArrayList<Carta> mao) {
        mao.add(baralho.getCarta(0));
        baralho.removerCarta(0);
        cartaDealerViradaParaBaixo = true;
    }

    public boolean verificarSeExisteAS(ArrayList<Carta> mao) {
        for (int i = 0; i < mao.size(); i++) {
            if (mao.get(i).getValor() == 11) {
                return true;
            }
        }
        return false;

    }

    public int contagemDeASnaMao(ArrayList<Carta> mao) {
        int contagemdeAS = 0;
        for (int i = 0; i < mao.size(); i++) {
            if (mao.get(i).getValor() == 11) {
                contagemdeAS++;
            }
        }
        return contagemdeAS;
    }

    public int getSomaDoASMaior(ArrayList<Carta> mao) {
        int soma = 0;
        for (int i = 0; i < mao.size(); i++) {
            soma = soma + mao.get(i).getValor();
        }
        return soma;
    }

    public int getSomaDaMao(ArrayList<Carta> mao) {
        if (verificarSeExisteAS(mao)) {
            if (getSomaDoASMaior(mao) <= 21) {
                return getSomaDoASMaior(mao);
            } else {
                for (int i = 0; i < contagemDeASnaMao(mao); i++) {
                    int soma = getSomaDoASMaior(mao) - (i + 1) * 10;
                    if (soma <= 21) {
                        return soma;
                    }
                }
            }
        } else {
            int soma = 0;
            for (int i = 0; i < mao.size(); i++) {
                soma = soma + mao.get(i).getValor();
            }
            return soma;
        }
        return 22;
    }

    public static void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {

        }
    }


}
