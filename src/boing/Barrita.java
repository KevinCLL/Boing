package boing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Barrita {

    private static final int Y = 535;
    static int with = 100;
    private static final int HEIGHT = 15;
    static int x = 0;
    int xa = 0;
    static Color color;
    private Boing game;

    public Barrita(Boing game, int x, Color color) {
        this.game = game;
        this.x = x;
        this.color = color;
    }

    void move() {
        if (x + xa > 0 && x + xa < game.getWidth() - with) {
            x = x + xa;
        }
        if (game.getMousePosition() != null
                && game.getMousePosition().x + (with / 2) < game.getWidth()
                && game.getMousePosition().x >= with / 2) {
            x = game.getMousePosition().x - (with / 2);
        }
    }

    public void keyReleased(KeyEvent e) {
        xa = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            xa = -Pelota.speed - 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            xa = Pelota.speed + 1;
        }
        if (e.getExtendedKeyCode() == KeyEvent.VK_M) {
            Musica.BACKGROUND.stop();
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, Y, with, HEIGHT);
    }

    public int getTopY() {
        return Y - HEIGHT;
    }

    public void paint(Graphics2D g) {
        g.setColor(color);
        g.fillRect(x, Y, with, HEIGHT);
    }
}
