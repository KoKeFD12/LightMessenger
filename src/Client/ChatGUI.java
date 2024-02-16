package Client;

import javax.swing.*;
import java.awt.*;

public class ChatGUI extends JFrame{
    public ChatGUI() {
        setSize(400,600);
        setTitle("LightMessenger");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.GRAY);

        JPanel chatPanel = new JPanel();
        chatPanel.setBackground(Color.GREEN);

        JPanel usersPanel = new JPanel();
        usersPanel.setBackground(Color.BLUE);

        JList<String>

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
