
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Classe responsavel pela representacao grafica do jogo
 * Faz a ligacao com o controlador de jogo principal e no caso de
 * nao aplicar MVC, faz ligacao com os modelos a serem definidos
 */


public class GUI extends JComponent  {



    public BufferedImage imagemDeFundo;
    private ArrayList<Carta> maoDealer;
    private ArrayList<Carta> maoJogador;
    private int pontosDealer;
    private int pontosJogador;
    private int saldoAtual;
    public static BufferedImage trofeuImagem;
    public boolean cartaDealerViradaParaBaixo = true;
    private JPanel panel1;

    public GUI(ArrayList<Carta> dealerMao, ArrayList<Carta> jogadorMao) {
        maoDealer = dealerMao;
        maoJogador = jogadorMao;
        saldoAtual = 500;
        pontosDealer = 0;
        pontosJogador = 0;
    }
    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2 = (Graphics2D) graphics;

        try {
            imagemDeFundo = ImageIO.read(new File("assets/background.png"));
            trofeuImagem = ImageIO.read(new File("assets/trofeu.png"));


        }
        catch (IOException e) {}

        Image imageDfundo = imagemDeFundo.getScaledInstance(1130,665,10);
        Image trofeuNovoImagem  = trofeuImagem.getScaledInstance(50,50,1);

        graphics2.drawImage(imageDfundo,0,0, null);
        graphics2.setColor(Color.WHITE);
        graphics2.setFont(new Font("Arial", Font.BOLD, 30));
        graphics2.drawString("Dealer", 515, 50);
        graphics2.drawString("Jogador", 515, 380);
        graphics2.setFont(new Font("Arial", Font.PLAIN,30 ));
        graphics2.drawImage(trofeuNovoImagem, 120, 20, null);
        graphics2.drawString("Pontos", 20,50);
        graphics2.drawString("Dealer: ", 20, 100);
        graphics2.drawString("Jogador: ",20, 150);
        graphics2.drawString(Integer.toString(pontosDealer),200, 100);
        graphics2.drawString(Integer.toString(pontosJogador),200, 150);
        graphics2.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        graphics2.drawString("Saldo Atual: " + saldoAtual, 50, 570);


        try {
            for (int i = 0; i < maoDealer.size(); i++) {
                if (i == 0) {
                    if (cartaDealerViradaParaBaixo) {
                        maoDealer.get(i).apresentarCarta(graphics2, true, true, i);

                    } else {
                        maoDealer.get(i).apresentarCarta(graphics2, true, false, i);
                    }
                }
                else    {
                    maoDealer.get(i).apresentarCarta(graphics2, true ,false, i);
                    }
            }
        } catch (IOException e) {

        }
        try {
            for (int i = 0; i < maoJogador.size(); i++) {
                maoJogador.get(i).apresentarCarta(graphics2, false,false,i);
            }
        }
        catch (IOException e) {}



    }

    public void refresh(int pj, int pd, boolean cvpb){
        pontosJogador = pj;
        pontosDealer = pd;
        cartaDealerViradaParaBaixo = cvpb;
        this.repaint();
    }
    public void refreshSaldo(int sa) {
        saldoAtual = sa;
        this.repaint();
    }



}
