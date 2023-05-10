package server;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PlayerHandler implements Runnable {
    private final BufferedReader in;
    private final PrintWriter out;

    public PlayerHandler(Socket clientSocket) throws IOException {
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);

        Players.getInstance().addPlayer(this);
    }

    @Override
    public void run() {
        while (true) {
            try {
                JSONObject data = new JSONObject(in.readLine());

                if (Players.getInstance().getPlayers().size() == 2) {
                    //Thread.sleep(500);

                    System.out.println("player " + Players.getInstance().getPlayers().indexOf(this) + ": " + data);
                    Players.getInstance().getOtherPlayer(this).sendOpponentCoordinates(data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendOpponentCoordinates(JSONObject data) {
        out.println(data);
    }

    public void sendBallCoordinates(JSONObject data) {
        out.println(data);
    }
}