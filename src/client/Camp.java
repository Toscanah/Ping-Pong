package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import static util.Dimensions.*;

public class Camp extends Canvas {
    private int playerX;
    private int playerY;
    private int opponentX;
    private int opponentY;
    private int ballX;
    private int ballY;

    private boolean isSecondPlayer;

    private JFrame frame;
    private Canvas canvas;


    public Camp() {
        frame = new JFrame("Pong");
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        setBackground(Color.WHITE);

        addKeyListener(new KeyAdapter() {
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
        });

        setFocusable(true);
        requestFocus();
        frame.add(this);
        frame.pack();
        frame.setVisible(true);

        playerX = 0;
        opponentX = CANVAS_WIDTH - PLAYER_WIDTH;

        playerY = 0;
        opponentY = 0;

        ballX = CANVAS_WIDTH / 2;
        ballY = CANVAS_HEIGHT / 2;

        isSecondPlayer = false;

        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        drawPlayer(g);
        drawOpponent(g);
        drawBall(g);
    }

    private void drawPlayer(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(playerX, playerY, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    private void drawOpponent(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(opponentX, opponentY, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    private void drawBall(Graphics g) {
        int diameter = Math.min(BALL_WIDTH, BALL_HEIGHT);
        g.setColor(Color.BLACK);
        g.fillOval(ballX, ballY, diameter, diameter);
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setOpponentY(int opponentY) {
        this.opponentY = opponentY;
        repaint();
    }

    public void setBallX(int ballX) {
        this.ballX = ballX;
        repaint();
    }

    public void setBallY(int ballY) {
        this.ballY = ballY;
        repaint();
    }

    public void setSecondPlayer(boolean secondPlayer) {
        isSecondPlayer = secondPlayer;
        playerX = CANVAS_WIDTH - PLAYER_WIDTH;
        opponentX = 0;
        repaint();
    }
}