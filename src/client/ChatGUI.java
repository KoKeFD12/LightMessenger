package client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChatGUI extends JFrame{
    public ChatGUI(String username) {
        setSize(400,600);
        setTitle("LightMessenger: "+username);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.GRAY);

        JPanel chatPanel = new JPanel();
        chatPanel.setBackground(Color.GREEN);

        JPanel usersPanel = new JPanel();
        usersPanel.setBackground(Color.BLUE);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement(username);
        JList<String> userList = new JList<>(listModel);
        usersPanel.add(userList);

        JPanel controlsPanel = new JPanel(new BorderLayout());
        controlsPanel.setBackground(Color.YELLOW);

        JTextField textField = new JTextField();
        controlsPanel.add(textField, BorderLayout.CENTER);

        JButton sendButton = new JButton("Send");
        controlsPanel.add(sendButton, BorderLayout.EAST);

        mainPanel.add(chatPanel, BorderLayout.CENTER);
        mainPanel.add(usersPanel, BorderLayout.EAST);
        mainPanel.add(controlsPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
}
