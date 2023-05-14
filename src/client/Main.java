package client;

import javafx.application.Application;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Camp camp = new Camp();
        Player player = new Player("localhost", 5845, camp);

        Thread receivingThread = new Thread(() -> {
            while (true) {
                try {
                    player.getData();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        receivingThread.start();

        while (true) {
            player.sendData();
        }
    }
}