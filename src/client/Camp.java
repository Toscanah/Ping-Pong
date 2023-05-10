package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static util.Dimensions.*;

public class Camp extends Canvas {
    private int playerY;
    private int opponentY;

    private int ballX;
    private int ballY;

    private JFrame frame;

    public Camp() {
        frame = new JFrame("Pong");
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        setBackground(Color.WHITE);
        setFocusable(true);
        KeyListener inputListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP, KeyEvent.VK_W -> {
                        if (playerY - PLAYER_STEP > 0) {
                            playerY -= PLAYER_STEP;
                        }
                    }
                    case KeyEvent.VK_DOWN, KeyEvent.VK_S -> {
                        if (playerY + PLAYER_STEP < CANVAS_HEIGHT - PLAYER_HEIGHT) {
                            playerY += PLAYER_STEP;
                        }
                    }
                }
                repaint();
            }
        };
        addKeyListener(inputListener);

        frame.add(this);
        frame.pack();
        frame.setVisible(true);

        playerY = 0;
        opponentY = 0;
    }

    private void drawPlayer(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, playerY, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    private void drawOpponent(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(CANVAS_WIDTH - PLAYER_WIDTH, opponentY, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    private void drawBall(Graphics g) {
        int diameter = Math.min(BALL_WIDTH, BALL_HEIGHT);
        g.setColor(Color.BLACK);
        g.fillOval(ballX, ballY, diameter, diameter);
    }

    @Override
    public void paint(Graphics g) {
        drawPlayer(g);
        drawOpponent(g);
        drawBall(g);
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setOpponentY(int opponentY) {
        this.opponentY = opponentY;
        repaint();
    }

    public int getBallX() {
        return ballX;
    }

    public void setBallX(int ballX) {
        this.ballX = ballX;
        repaint();
    }

    public int getBallY() {
        return ballY;
    }

    public void setBallY(int ballY) {
        this.ballY = ballY;
        repaint();
    }
}