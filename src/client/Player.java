package client;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class Player {
    private final Camp camp;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Player(String address, int port, Camp camp) throws IOException {
        socket = new Socket(address, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        this.camp = camp;
        camp.repaint();
    }

    public void sendData() {
        JSONObject data = new JSONObject();
        data.put("opponentY", camp.getPlayerY());
        data.put("opponentX", camp.getPlayerX());

        out.println(data);
    }

    public void getData() throws IOException {
        JSONObject data = new JSONObject(in.readLine());
        System.out.println("Incoming data: " + data);

        if (data.has("secondPlayer")) {
            camp.setSecondPlayer(data.getBoolean("secondPlayer"));
        }

        if (data.has("opponentY")) {
            camp.setOpponentY(data.getInt("opponentY"));
        }

        if (data.has("ballX") && data.has("ballY")) {
            camp.setBallX(data.getInt("ballX"));
            camp.setBallY(data.getInt("ballY"));
        }
    }
}