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

    public PlayerHandler getOtherPlayer(PlayerHandler sender) {
        for (PlayerHandler player : players) {
            if (!player.equals(sender)) {
                return player;
            }
        }
        return null;
    }
}