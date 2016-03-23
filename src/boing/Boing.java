package boing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Boing extends JPanel {

    static int counter = 0;
    static Color gColor = getColor();
    static final String[] MANCO = new String[]{"¡Mancazo!", "n00b!!!1!",
        "Penoso", "Dedicate a otra cosa"};

    Pelota pelota1 = new Pelota(this, ((int) (Math.random() * 799) + 1),
            ((int) (Math.random() * 100) + 1), 4, 4, gColor, 4, 30);
    Barrita barrita = new Barrita(this, 400, gColor);

    public Boing() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                barrita.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                barrita.keyPressed(e);
            }
        });
        setFocusable(true);
        Musica.BACKGROUND.loop();
    }

    private void move() throws InterruptedException {
        pelota1.move();
        barrita.move();
    }

    static public Color getColor() {
        int[] random = new int[]{255, 0, 255, 0, 127, 255, 0, 255, 0};
        int r = random[(int) (Math.random() * 8) + 0];
        int g = random[(int) (Math.random() * 8) + 0];
        int b = random[(int) (Math.random() * 8) + 0];
        Color color;
        if (r == 0 && g == 0 && b == 0) {
            color = new Color(r, g, 255);
        } else {
            color = new Color(r, g, b);
        }
        return color;
    }

    public void gameOver() throws InterruptedException {
        Musica.BACKGROUND.stop();
        Musica.GAMEOVER.play();
        JOptionPane.showMessageDialog(this, MANCO[(int) (Math.random() * 4) + 0], "Game over", JOptionPane.YES_NO_OPTION);
        Thread.sleep(1000);
        pelota1 = new Pelota(this, ((int) (Math.random() * 799) + 1),
                ((int) (Math.random() * 100) + 1), 4, 4, gColor, 4, 30);
        barrita = new Barrita(this, 400, gColor);
        Barrita.with = 100;
        counter = 0;
        Musica.BACKGROUND.loop();
    }

    private int getScore() {
        return counter;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        pelota1.paint(g2d);
        barrita.paint(g2d);
        g2d.setColor(gColor);
        g2d.setFont(new Font("Verdana", Font.BOLD, 30));
        g2d.drawString(String.valueOf(getScore()), 375, 30);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Boing");
        Boing game = new Boing();
        frame.add(game);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setCursor(frame.getToolkit().createCustomCursor(
                new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),
                "null"));
        game.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        while (true) {
            game.move();
            game.repaint();
            Thread.sleep(10);
        }
    }
}
