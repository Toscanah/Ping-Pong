package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection {
    private Socket socket;

    public Connection(String address, int port) throws IOException {
        socket = new Socket(address, port);
    }


    public String getMessage() throws IOException {
        return in.readLine();
    }

    public boolean isActive() {
        return !socket.isClosed();
    }

    public void shutdown() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}