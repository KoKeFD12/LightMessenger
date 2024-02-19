package TCP.client.GUI;

import javax.swing.*;
import java.awt.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import TCP.util.Values;

public class ChatGUI extends JFrame{
    public boolean isClosed = false;
    private DataInputStream serverInputStream;
    DataOutputStream serverOutputStream;
    public ChatGUI(String username, DataInputStream serverInputStream, DataOutputStream serverOutputStream) {
        this.serverInputStream = serverInputStream;
        this.serverOutputStream = serverOutputStream;

        setSize(400,600);
        setTitle("LightMessenger: "+username);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.GRAY);

        JPanel chatPanel = new JPanel(new BorderLayout());
        //chatPanel.setBackground(Color.GREEN);

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        chatPanel.add(textPane);

        JPanel usersPanel = new JPanel();
        //usersPanel.setBackground(Color.BLUE);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement(username);
        try {
            serverOutputStream.writeInt(Values.NEW_USER_CODE);
            serverOutputStream.writeUTF(username);
        } catch (IOException ex) {
            System.err.println("Could add the user to the users list: "+ex.getMessage());
        }
        JList<String> userList = new JList<>(listModel);
        userList.setFixedCellWidth(80);
        usersPanel.add(userList);


        JPanel controlsPanel = new JPanel(new BorderLayout());
        //controlsPanel.setBackground(Color.YELLOW);

        JTextField textField = new JTextField();
        controlsPanel.add(textField, BorderLayout.CENTER);

        JButton sendButton = new JButton("Send");
        controlsPanel.add(sendButton, BorderLayout.EAST);
        sendButton.addActionListener(e -> {
            try {
                serverOutputStream.writeInt(Values.NEW_MESSAGE_CODE);
                serverOutputStream.writeUTF(username+": "+textField.getText());
                textField.setText("");
            } catch (IOException ex) {
                System.err.println("Could not send the message: "+ex.getMessage());
            }

        });

        mainPanel.add(chatPanel, BorderLayout.CENTER);
        mainPanel.add(usersPanel, BorderLayout.EAST);
        mainPanel.add(controlsPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setVisible(true);

        while (true) {
            if (isVisible()) {
                try {
                    switch (serverInputStream.readInt()) {
                        case Values.USERS_LIST_UPDATE_CODE -> {
                            String userListString = serverInputStream.readUTF();
                            listModel.clear();
                            listModel.addAll(new ArrayList<>(Arrays.asList(userListString.substring(1, userListString.length() - 1).split(", "))));
                            userList.setModel(listModel);
                        }
                        case Values.MESSAGES_UPDATE_CODE -> {
                            String messagesListString = serverInputStream.readUTF();
                            ArrayList<String> messageList = new ArrayList<>(Arrays.asList(messagesListString.substring(1, messagesListString.length() - 1).split(", ")));
                            String finalString = "";
                            for (String str : messageList) {
                                finalString+= str+"\n";
                            }
                            textPane.setText(finalString);
                        }
                    }
                } catch (IOException e) {
                    System.err.println("A problem occurred while checking new messages: " + e.getMessage());
                }
            }
        }
    }
}
