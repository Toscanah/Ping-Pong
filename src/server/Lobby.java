package server;

import org.json.JSONObject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.Dimensions.*;

public class Lobby {
    private final int port;
    private final Ball ball;

    public Lobby(int port) {
        this.port = port;
        ball = new Ball(800 / 2, 100);
    }

    public void listen() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);

        while (Players.getInstance().getPlayers().size() != 1) {
            Socket clientSocket = serverSocket.accept();
            PlayerHandler playerHandler = new PlayerHandler(clientSocket);
            Thread connectionThread = new Thread(playerHandler);
            connectionThread.start();
        }
        startBalling();
    }

    private void startBalling() {
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // questo è il controllo per la pallina con la sua direzione di default
            // ma cambia tutto con l'altro giocatore

            int xStep = BALL_STEP;
            int yStep = BALL_STEP;

            if (ball.getX() == 0 || ball.getX() == CANVAS_WIDTH - 50) {
                xStep = -xStep;
            }

            if (ball.getY() == 0 || ball.getY() == CANVAS_HEIGHT - 50) {
                yStep = -yStep;
            }

            ball.setX(ball.getX() + xStep);
            ball.setY(ball.getY() + yStep);

            // TODO: problema, come faccio a detectare la collusione con le coordinate specchiate?
            // con un player è easy, ma con l'altro?

            PlayerHandler player1 = Players.getInstance().getPlayers().get(0);
            //PlayerHandler player2 = Players.getInstance().getPlayers().get(1);

            JSONObject data = new JSONObject();
            data.put("ballX", ball.getX());
            data.put("ballY", ball.getY());

            player1.sendBallCoordinates(data);

            // guardare soluzioni chatGPT
        }
    }
}