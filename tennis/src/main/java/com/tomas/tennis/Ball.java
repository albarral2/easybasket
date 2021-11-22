package com.tomas.tennis;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {

    final static int DIAMETER = 30;
    final static int SPEED = 3;
    int x, y;
    int vx, vy;
    Game game;

    public Ball(Game game) {
        x = 0;
        y = 0;
        vx = SPEED;
        vy = SPEED;
        this.game = game;
    }

    public void move() {
        int xlimit = game.getWidth() - DIAMETER;
        int ylimit = game.getHeight() - DIAMETER;

        x = x + vx;
        y = y + vy;

        if ((x < 0) || (x > xlimit)) {
            Sound.BALL.play();
            vx = -vx;
        }

        if ((y < 0) || (y > ylimit)) {
            game.gameOver();
        }

        // if ball colides racket it goes up again
        if (collision()) {
            Sound.BALL.play();
            vy = -vy;
        }
    }

    public void paint(Graphics2D g) {
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }

    private boolean collision() {
        return game.racket.getBounds().intersects(getBounds());
    }
}
