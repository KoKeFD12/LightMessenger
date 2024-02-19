package TCP.client.GUI;

import javax.swing.*;
import java.awt.*;

public class LoginGUI {
    public static String username;
    public static int show() {

        JPanel mainPanel = new JPanel(new GridLayout(2,1));
        JLabel label = new JLabel("Type your username:");
        mainPanel.add(label);
        JTextField textField = new JTextField();
        mainPanel.add(textField);

        if (JOptionPane.showOptionDialog(
                null,
                mainPanel,
                "Login",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new String[]{"CANCEL", "ENTER"},
                "ENTER"
        ) == 1) {
            username = textField.getText();
            return 1;
        } else {
            return 0;
        }
    }

    public static void alreadyExistsMessage(){
        JFrame frame = new JFrame("Login");
        frame.setSize(200,100);
        JPanel panel = new JPanel();
        JLabel label = new JLabel("User already exists.");
        panel.add(label);
        frame.add(panel);
        frame.setVisible(true);
    }
}
