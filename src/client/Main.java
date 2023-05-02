package client;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Player player = new Player("127.0.0.1", 6666);

        // TODO: change name of thread
        Thread getOtherPlayerCoordsThread = new Thread(() -> {
            while (player.isActive()) {
                try {
                    JSONObject coords = player.getOtherPlayerCoords();
                    // TODO: draw other player with coordinates
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        getOtherPlayerCoordsThread.start();

        /* TODO: come gestisco le coordinate?
        il while qua sotto dovrebbe leggere le coordinate dal canvas
        e mandarle al server
        però le coordinate le ho già dentro a "Player"
        di conseguenza quand'è che aggiorno le coordinate?
        come gestisco l'input da utente (freccie)
         */

        while (true) {
            JSONObject coords = new JSONObject();
            // TODO:

            player.setCoordinates(x, y);
            player.sendCoordinates(coords);
        }
    }
}