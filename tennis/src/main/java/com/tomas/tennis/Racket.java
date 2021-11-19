package com.tomas.tennis;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racket {

  final static int WIDTH = 60;
  final static int HEIGHT = 10;
  final static int SPEED = 5;
  int x, y;
  int vx;
  Game game;

  public Racket(Game game) {
    x = 0;
    y = Game.HEIGHT - 70; // y fixed position
    vx = 0;
    this.game = game;
  }

  public void move() {
    int xlimit = game.getWidth() - WIDTH;
    if (x + vx > 0 && x + vx < xlimit) {
      x = x + vx;
    }
  }

  public void paint(Graphics2D g) {
    g.fillRect(x, y, WIDTH, HEIGHT);
  }

  public Rectangle getBounds() {
    return new Rectangle(x, y, WIDTH, HEIGHT);
  }

  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      vx = -SPEED;
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      vx = SPEED;
    }
  }

  public void keyReleased(KeyEvent e) {
    vx = 0;
  }

}
