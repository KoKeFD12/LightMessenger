package server;
import client.Client;
import util.Values;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(Values.SERVER_PORT);
            Socket clientConnection = null;
            DataInputStream clientInput = null;
            DataOutputStream clientOutput = null;
            clientConnection = serverSocket.accept();
            clientInput = new DataInputStream(clientConnection.getInputStream());
            clientOutput = new DataOutputStream(clientConnection.getOutputStream());
            while (true) {
                clientInput.readUTF();
                System.out.println(clientInput.readUTF());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
