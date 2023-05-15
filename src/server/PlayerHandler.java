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

    private int x;
    private int y;

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

                x = data.getInt("opponentX");
                y = data.getInt("opponentY");

                if (Players.getInstance().getPlayers().size() == 2) {
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

    public void setSecondPlayer() {
        out.println(new JSONObject().put("secondPlayer", true));
    }

    public void sendScore(JSONObject data) {
        out.println(data);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}