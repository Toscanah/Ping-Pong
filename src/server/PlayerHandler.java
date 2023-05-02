package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PlayerHandler implements Runnable {
    private final BufferedReader in;
    private final PrintWriter out;

    private final Socket clientSocket;

    public PlayerHandler(Socket clientSocket) throws IOException {
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);

        this.clientSocket = clientSocket;

        Players.getInstance().addPlayer(this);
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}