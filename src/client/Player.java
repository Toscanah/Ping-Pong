package client;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private int x;
    private int y;

    public Player(String address, int port) throws IOException {
        socket = new Socket(address, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public JSONObject getOtherPlayerCoords() throws IOException {
        return new JSONObject(in.readLine());
    }

    public boolean isActive() {
        return !socket.isClosed();
    }

    public void sendCoordinates(JSONObject coords) {
        out.println(coords);
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
}