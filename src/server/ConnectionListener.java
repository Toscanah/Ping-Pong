package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionListener {
    private final int port;

    public ConnectionListener(int port) {
        this.port = port;
    }

    public void listen() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            ConnectionHandler connectionHandler = new ConnectionHandler(clientSocket);
            Thread connectionThread = new Thread(connectionHandler);
            connectionThread.start();
        }
    }
}