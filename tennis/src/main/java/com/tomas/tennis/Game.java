package com.tomas.tennis;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//@SuppressWarnings("serial")
public class Game extends JPanel {

    final static int WIDTH = 400;
    final static int HEIGHT = 400;
    Ball ball;
    Racket racket;

    @SuppressWarnings({"static-access", "OverridableMethodCallInConstructor"})
    public Game() {
        ball = new Ball(this);
        racket = new Racket(this);
        Sound.BACK.loop();
        KeyListener listener = new Keyboard(this);
        addKeyListener(listener);
        setFocusable(true);
    }

    public static void main(String[] args) throws InterruptedException {

        try {
            JFrame frame = new JFrame("Mini Tennis");
            Game game = new Game();
            frame.add(game);
            frame.setSize(WIDTH, HEIGHT);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            while (true) {
                game.move();
                game.repaint();
                Thread.sleep(10);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    private void move() {
        ball.move();
        racket.move();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ball.paint(g2d);
        racket.paint(g2d);
    }

    public void gameOver() {
        Sound.BACK.stop();
        Sound.GAMEOVER.play();
        JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
        System.exit(0);
    }
}
