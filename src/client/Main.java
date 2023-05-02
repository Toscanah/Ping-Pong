package client;

import client.Connection;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        Connection conn = new Connection("127.0.0.1", 6666);

        Thread getMessagesThread = new Thread(() -> {
            while (conn.isActive()) {
                try {
                    String message = conn.getMessage();
                    System.out.println(message);
                } catch (IOException e) {
                    conn.shutdown();
                    break;
                }
            }
        });

        getMessagesThread.start();

        while (true) {
            String message = scanner.nextLine();
            if (message.equalsIgnoreCase("/leave")) {
                conn.shutdown();
                break;
            }
            conn.sendMessage(message);
        }
    }
}