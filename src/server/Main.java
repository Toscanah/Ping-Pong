package server;

import java.io.IOException;

public class Main {
    private static final int PORT = 5845;

    public static void main(String[] args) throws IOException {
        System.out.println("Server started");
        Lobby lobby = new Lobby(PORT);
        lobby.listen();
    }
}
