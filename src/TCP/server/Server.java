package TCP.server;

import TCP.util.Values;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server {
    private static final ArrayList<ServerThread> serverThreadArrayList = new ArrayList<>();
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(Values.SERVER_PORT);) {
            ServerData serverData = new ServerData();
            while (true) {
                try {
                    ServerThread serverThread = new ServerThread(serverData, serverSocket.accept());
                    serverThread.start();
                    serverThreadArrayList.add(serverThread);
                } catch (IOException e) {
                    System.err.println("Client disconnected: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("A problem with the connection occurred: " + e.getMessage());
        }
    }


    /**
     * Sends a call to all the clients to get and update the most recent users list.
     */
    public static void updateMessages() {
        serverThreadArrayList.forEach(ServerThread::sendUpdateMessages);
    }


    /**
     * Sends a call to all the clients to get and update the most recent messages.
     */
    public static void updateUsersList() {
        serverThreadArrayList.forEach(ServerThread::sendUpdateUsers);
    }
}
