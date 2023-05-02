package server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Connections {
    private static Connections instance;
    private final ArrayList<ConnectionHandler> connections = new ArrayList<>();

    private Connections() {
    }

    public static Connections getInstance() {
        if (instance == null) {
            instance = new Connections();
        }

        return instance;
    }

    public void addConnection(ConnectionHandler connectionHandler) {
        connections.add(connectionHandler);
    }

    public List<ConnectionHandler> getConnections() {
        return Collections.unmodifiableList(connections);
    }

    public void sendBroadcast(Socket sender, String message) {
        for (ConnectionHandler c : connections) {
            if (c.getClientSocket() != sender) {
                c.sendMessage(message);
            }
        }
    }
}