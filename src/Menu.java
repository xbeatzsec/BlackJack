import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Menu extends JComponent implements ActionListener {

    private JButton botaoJogar = new JButton("Jogar");
    private JButton botaoSair = new JButton("Sair");
    private static BufferedImage imagemDeFundo;
    public static BufferedImage piagetLogo;

    public Menu() {
        botaoJogar.addActionListener(this);
        botaoSair.addActionListener(this);
    }

    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        try {
            imagemDeFundo = ImageIO.read(new File("assets/background.png"));
            piagetLogo = ImageIO.read(new File("assets/piagetLogo.png"));
        } catch (IOException e) {
        }
        Image imageDfundo = imagemDeFundo.getScaledInstance(1130, 665, 10);
        Image piagetNovoLogo = piagetLogo.getScaledInstance(100, 100, 1);

        g2.drawImage(imageDfundo, 0, 0, null);

        g2.setFont(new Font("Serif Bold", Font.BOLD, 50));
        g2.setColor(Color.WHITE);
        g2.drawString("Piaget | BlackJack", 380, 86);

        g2.setFont(new Font("Arial", Font.BOLD, 10));
        g2.drawString("Elaborado por Hernani & Alex", 930, 580);

        botaoJogar.setBounds(500, 300, 150, 80);
        botaoSair.setBounds(500, 400, 150, 80);

        botaoJogar.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        botaoSair.setFont(new Font("Comic Sans MS", Font.BOLD, 40));

        Color lightGreen = new Color(144, 238, 144);
        Color lightRed = new Color(255, 99, 71);

        botaoJogar.setBackground(lightGreen);
        botaoSair.setBackground(lightRed);

        g2.drawImage(piagetNovoLogo, 50, 20, null);

        super.add(botaoJogar);
        super.add(botaoSair);
    }

    public void actionPerformed(ActionEvent e) {
        JButton selectedButton = (JButton) e.getSource();

        if (selectedButton == botaoSair) {
            System.exit(0);
        } else if (selectedButton == botaoJogar) {
            Main.currentState = Main.ESTADO.JOGO;
            Main.menu.dispose();
            Main.refreshJogoThread.start();
            Main.checkThreadJogo.start();
        }
    }

}