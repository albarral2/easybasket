package com.tomas.tennis;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {

  final static int DIAMETER = 30;
  final static int SPEED = 2;
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

    if (x + vx < 0) {
      vx = SPEED;
    } else if (x + vx > xlimit) {
      vx = -SPEED;
    }

    if (y + vy < 0) {
      vy = SPEED;
    } else if (y + vy > ylimit) {
      //vy = -SPEED;
      game.gameOver();
    }

    // if ball colides racket it goes up again
    if (collision()) {
      vy = -SPEED;
    }
    x = x + vx;
    y = y + vy;
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
