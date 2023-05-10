package client;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player {
    private Camp camp;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Player(String address, int port, Camp camp) throws IOException {
        socket = new Socket(address, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        this.camp = camp;
    }

    public void sendPlayerY() {
        JSONObject data = new JSONObject();
        data.put("opponentY", camp.getPlayerY());
        out.println(data);

        System.out.println(data);
    }

    public void getCoordinates() throws IOException {
        JSONObject data = new JSONObject(in.readLine());

        // modo terribile per gestire i JSON
        if (data.has("opponentY")) {
            camp.setOpponentY(data.getInt("opponentY"));
        }

        if (data.has("ballX") && data.has("ballY")) {
            camp.setBallX(data.getInt("ballX"));
            camp.setBallY(data.getInt("ballY"));
        }
    }

    public boolean isActive() {
        return !socket.isClosed();
    }
}