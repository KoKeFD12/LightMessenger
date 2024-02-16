package client;

import client.GUI.ChatGUI;
import client.GUI.LoginGUI;

public class Client {
    public static void main(String[] args) {

        if (LoginGUI.show() != 1) {
            System.exit(0);
        } else {
            ChatGUI chatGUI = new ChatGUI(LoginGUI.username);
            chatGUI.setVisible(true);
        }


    }
}
