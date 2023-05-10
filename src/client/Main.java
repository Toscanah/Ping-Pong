package client;

import org.json.JSONObject;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Player player = new Player("localhost", 5845, new Camp());

        Thread recievingThread = new Thread(() -> {
            while (player.isActive()) {
                try {
                    player.getCoordinates();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        recievingThread.start();

        while (true) {
            player.sendPlayerY();
            //Thread.sleep(500);
            System.out.println("?");
        }
    }
}