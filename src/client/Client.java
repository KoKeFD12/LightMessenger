package client;

import client.GUI.ChatGUI;
import client.GUI.LoginGUI;
import util.Values;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {

        try (Socket serverConnection = new Socket("localhost", Values.SERVER_PORT)) {
            DataOutputStream serverOutputStream = new DataOutputStream(serverConnection.getOutputStream());
            DataInputStream serverInputStream = new DataInputStream(serverConnection.getInputStream());
            if (LoginGUI.show() != 1) {
                System.exit(0);
            } else {
                ChatGUI chatGUI = new ChatGUI(LoginGUI.username, serverInputStream, serverOutputStream);
            }
        } catch (UnknownHostException e) {
            System.err.println("Could not find host IP.");
        } catch (IOException e) {
            System.err.println("Could not connect to the server.");
        }


    }
}
