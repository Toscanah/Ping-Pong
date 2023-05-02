package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Lobby {
    private final int port;

    public Lobby(int port) {
        this.port = port;
    }

    public void listen() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);

        while (Players.getInstance().getPlayers().size() != 2) {
            Socket clientSocket = serverSocket.accept();
            PlayerHandler playerHandler = new PlayerHandler(clientSocket);
            Thread connectionThread = new Thread(playerHandler);
            connectionThread.start();
        }

        // start the ball
    }
}