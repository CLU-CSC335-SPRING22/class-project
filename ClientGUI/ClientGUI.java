package ClientGUI;

import javax.swing.*;

public class ClientGUI extends JFrame {
    private JTextField textField1;
    private JPanel panel1;
    private JPasswordField passwordField1;
    private JButton button1;
    private JFrame frame;


    public ClientGUI(){
        frame = new JFrame("Client");

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

