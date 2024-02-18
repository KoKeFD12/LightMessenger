package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import util.Values;

public class ServerThread extends Thread{
    private final Socket CLIENT;
    private final ServerData serverData;
    private String username = "";
    public ServerThread(ServerData serverData, Socket client) {
        this.serverData = serverData;
        this.CLIENT = client;
    }

    @Override
    public void run() {
        try (DataInputStream clientInput = new DataInputStream(CLIENT.getInputStream());
             DataOutputStream clientOutput = new DataOutputStream(CLIENT.getOutputStream())) {
            while (true) {
                clientOutput.writeInt(Values.USERS_LIST_UPDATE_CODE);
                clientOutput.writeUTF(serverData.getUsersList().toString());
                clientOutput.writeInt(Values.MESSAGES_UPDATE_CODE);
                clientOutput.writeUTF(serverData.getMessages().toString());
                switch (clientInput.readInt()){
                    case Values.NEW_USER_CODE -> {
                        username = clientInput.readUTF();
                        serverData.addToUsersList(username);
                    }
                    case Values.NEW_MESSAGE_CODE -> {
                        serverData.addToMessages(clientInput.readUTF());
                    }
                }
            }
        } catch (IOException e) {
            serverData.removeFromUsersList(username);
        }
    }
}
