package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static util.Dimensions.*;

public class Camp extends Canvas {
    private int playerX;
    private int playerY;
    private int opponentX;
    private int opponentY;
    private int ballX;
    private int ballY;

    private int playerScore;
    private int opponentScore;

    private boolean isSecondPlayer;

    private JFrame frame;

    public Camp() {
        frame = new JFrame("Pong");
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        setBackground(Color.BLACK);

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP, KeyEvent.VK_W -> {
                        if (playerY - PLAYER_STEP > 0) {
                            playerY -= PLAYER_STEP;
                            repaint();
                        }
                    }
                    case KeyEvent.VK_DOWN, KeyEvent.VK_S -> {
                        if (playerY + PLAYER_STEP < CANVAS_HEIGHT - PLAYER_HEIGHT) {
                            playerY += PLAYER_STEP;
                            repaint();
                        }
                    }
                }

            }
        });

        setFocusable(true);
        requestFocus();
        frame.add(this);
        frame.pack();
        frame.setVisible(true);

        playerX = 0;
        opponentX = CANVAS_WIDTH - PLAYER_WIDTH;

        playerY = CANVAS_HEIGHT / 2;
        opponentY = CANVAS_HEIGHT / 2;

        ballX = -1000;
        ballY = -1000;

        isSecondPlayer = false;

        repaint();
    }

    private Image buffer;
    private Graphics bufferGraphics;

    public void update(Graphics g) {
        if (buffer == null || buffer.getWidth(null) != getWidth() || buffer.getHeight(null) != getHeight()) {
            buffer = createImage(getWidth(), getHeight());
            bufferGraphics = buffer.getGraphics();
        }

        bufferGraphics.setColor(getBackground());
        bufferGraphics.fillRect(0, 0, getWidth(), getHeight());
        paint(bufferGraphics);

        g.drawImage(buffer, 0, 0, null);
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.WHITE);
        drawPlayer(g);
        drawOpponent(g);
        drawBall(g);
        drawMiddleLine((Graphics2D) g);
        drawLabels(g);
    }

    private void drawPlayer(Graphics g) {
        g.fillRect(playerX, playerY, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    private void drawOpponent(Graphics g) {
        g.fillRect(opponentX, opponentY, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    private void drawBall(Graphics g) {
        g.fillRect(ballX, ballY, BALL_WIDTH, BALL_HEIGHT);
    }

    private void drawMiddleLine(Graphics2D g) {
        int centerX = getWidth() / 2;
        float[] dashPattern = {20f, 10f};
        BasicStroke dotted = new BasicStroke(10f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0f, dashPattern, 0f);
        g.setStroke(dotted);
        g.setColor(Color.WHITE);
        g.drawLine(centerX, 0, centerX, getHeight());
    }

    private void drawLabels(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("MS Sans Serif", Font.BOLD, 30));

        String scoreText = (isSecondPlayer ?
                opponentScore + "               " + playerScore
                : playerScore + "               " + opponentScore);

        int textWidth = g.getFontMetrics().stringWidth(scoreText);
        int textHeight = g.getFontMetrics().getHeight();
        int x = (CANVAS_WIDTH - textWidth) / 2;
        int y = textHeight + 20;
        g.drawString(scoreText, x, y);
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

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
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

    public void addScore(String player) {
        if (player.equals("player")) {
            playerScore++;
        } else {
            opponentScore++;
        }

        setOpponentY(CANVAS_HEIGHT / 2);
        setPlayerY(CANVAS_HEIGHT / 2);
        repaint();
    }
}