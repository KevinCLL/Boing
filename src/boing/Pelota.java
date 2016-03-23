package boing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Pelota {

    static int x;
    static int y;
    static int xa;
    static int ya;
    Color color;
    private Boing game;
    static int speed;
    static int size;

    public Pelota(Boing game, int x, int y, int xa, int ya, Color color, int speed, int size) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.xa = xa;
        this.ya = ya;
        this.color = color;
        this.speed = speed;
        this.size = size;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    private boolean collision() {
        return game.barrita.getBounds().intersects(getBounds());
    }

    void move() throws InterruptedException {
        if (x + xa < 0) {
            xa = speed;
        }
        if (x + xa > game.getWidth() - size) {
            xa = -speed;
        }
        if (y + ya < 0) {
            ya = speed;
        }
        if (y + ya > game.getHeight() - size) {
            game.gameOver();
        }
        if (collision()) {
            ya = -speed;
            y = game.barrita.getTopY() - size;
            Boing.counter += 1;
            if (Boing.counter % 3 == 0) {
                speed++;
            }
            if (Boing.counter % 5 == 0 && Barrita.with > 50) {
                Barrita.with -= 5;
            }
            if (Boing.counter % 4 == 0 && size > 10) {
                size--;
            }
            Color tColor = Boing.getColor();
            Boing.gColor = tColor;
            Barrita.color = tColor;
            color = tColor;

        }

        x = x + xa;
        y = y + ya;
    }

    public void paint(Graphics2D g) {

        g.setColor(color);
        g.fillOval(x, y, size, size);

    }
}
