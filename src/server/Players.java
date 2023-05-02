package server;

import java.util.ArrayList;

public class Players {
    private static Players instance;
    private final ArrayList<PlayerHandler> players = new ArrayList<>(2);

    private Players() {}

    public static Players getInstance() {
        if (instance == null) {
            instance = new Players();
        }
        return instance;
    }

    public ArrayList<PlayerHandler> getPlayers() {
        return players;
    }

    public void addPlayer(PlayerHandler handler) {
        players.add(handler);
    }
}
