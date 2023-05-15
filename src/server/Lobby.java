package server;

import org.json.JSONObject;

import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.Dimensions.*;

public class Lobby {
    private final int port;
    private final Ball ball;

    private int xStep = -1;
    private int yStep = 1;

    public Lobby(int port) {
        this.port = port;
        ball = new Ball(CANVAS_WIDTH / 2, CANVAS_WIDTH / 2);
    }

    public void listen() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (Players.getInstance().getPlayers().size() != 2) {
                Socket clientSocket = serverSocket.accept();
                PlayerHandler playerHandler = new PlayerHandler(clientSocket);
                Thread connectionThread = new Thread(playerHandler);
                connectionThread.start();

                if (Players.getInstance().getPlayers().size() == 2) {
                    playerHandler.setSecondPlayer();
                }
            }
            startBalling();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startBalling() {
        while (true) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Rectangle ballRect = new Rectangle(
                    ball.getX(), ball.getY(), BALL_WIDTH, BALL_HEIGHT
            );

            for (PlayerHandler player : Players.getInstance().getPlayers()) {
                Rectangle playerRect = new Rectangle(
                        player.getX(), player.getY(), PLAYER_WIDTH, PLAYER_HEIGHT
                );

                if (ballRect.getBounds().intersects(playerRect.getBounds())) {
                    xStep = -xStep;
                }
            }

            int newX = ball.getX() + (BALL_STEP * xStep);
            int newY = ball.getY() + (BALL_STEP * yStep);

            if (newY < 0) {
                yStep = -yStep;
            }

            if (newY + BALL_HEIGHT > CANVAS_HEIGHT) {
                yStep = -yStep;
            }

            if (newX + BALL_WIDTH == 0) {
                JSONObject data = new JSONObject().put("rightWon", true);

                for (PlayerHandler player : Players.getInstance().getPlayers()) {
                    player.sendScore(data);
                }

                restartGame();
            }

            if (newX + BALL_WIDTH == CANVAS_WIDTH) {
                JSONObject data = new JSONObject().put("leftWon", true);

                for (PlayerHandler player : Players.getInstance().getPlayers()) {
                    player.sendScore(data);
                }

                restartGame();
            }

            ball.setX(ball.getX() + (BALL_STEP * xStep));
            ball.setY(ball.getY() + (BALL_STEP * yStep));

            JSONObject ballData = new JSONObject();
            ballData.put("ballX", ball.getX());
            ballData.put("ballY", ball.getY());

            for (PlayerHandler player : Players.getInstance().getPlayers()) {
                player.sendBallCoordinates(ballData);
            }
        }
    }

    private void restartGame() {
        ball.setX(CANVAS_WIDTH / 2);
        ball.setY(CANVAS_HEIGHT / 2);
    }
}