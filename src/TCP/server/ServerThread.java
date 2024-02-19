package TCP.server;

import TCP.util.Values;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {
    private final ServerData serverData;
    private String username = "";
    private DataInputStream clientInput = null;
    private DataOutputStream clientOutput = null;

    public ServerThread(ServerData serverData, Socket client) {
        this.serverData = serverData;
        try {
            clientInput = new DataInputStream(client.getInputStream());
            clientOutput = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                switch (clientInput.readInt()) {
                    case Values.NEW_USER_CODE -> {
                        username = clientInput.readUTF();
                        serverData.addToUsersList(username);
                        Server.updateUsersList();
                    }
                    case Values.NEW_MESSAGE_CODE -> {
                        serverData.addToMessages(clientInput.readUTF());
                        Server.updateMessages();
                    }
                    case Values.CHECK_USERNAME_CODE -> {
                        clientOutput.writeBoolean(serverData.getUsersList().contains(clientInput.readUTF()));
                    }
                }
            }
        } catch (IOException e) {
            serverData.removeFromUsersList(username);
            Server.updateUsersList();
        }
    }

    public void sendUpdateMessages() {
        try {
            clientOutput.writeInt(Values.MESSAGES_UPDATE_CODE);
            clientOutput.writeUTF(serverData.getMessages().toString());
        } catch (IOException e) {
            System.err.println("Could not send the update messages: " + e.getMessage());
        }
    }

    public void sendUpdateUsers() {
        try {
            clientOutput.writeInt(Values.USERS_LIST_UPDATE_CODE);
            clientOutput.writeUTF(serverData.getUsersList().toString());
        } catch (IOException e) {
            System.err.println("Could not send the update users list: " + e.getMessage());
        }
    }
}