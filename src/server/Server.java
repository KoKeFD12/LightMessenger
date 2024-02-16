package server;

import util.Values;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(Values.SERVER_PORT);
            Socket clientConnection = null;
            DataInputStream clientInput = null;
            DataOutputStream clientOutput = null;
            ArrayList<String> messageList = new ArrayList<>();
            while (true) {
                try {
                    clientConnection = serverSocket.accept();
                    clientInput = new DataInputStream(clientConnection.getInputStream());
                    clientOutput = new DataOutputStream(clientConnection.getOutputStream());
                    while (true) {
                        messageList.add(clientInput.readUTF());
                        clientOutput.writeUTF(messageList.getLast());
                    }
                } catch (IOException e) {
                    System.err.println("Client disconnected: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("A problem with the connection occurred: " + e.getMessage());
        }
    }
}
