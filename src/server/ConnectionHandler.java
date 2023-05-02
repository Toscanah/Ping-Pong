package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionHandler implements Runnable {
    private final BufferedReader in;
    private final PrintWriter out;
    private final Socket clientSocket;

    public ConnectionHandler(Socket clientSocket) throws IOException {
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        this.clientSocket = clientSocket;
        Connections.getInstance().addConnection(this);
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = in.readLine();
                Connections.getInstance().sendBroadcast(clientSocket, message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Socket getClientSocket() {
        return clientSocket;
    }
}