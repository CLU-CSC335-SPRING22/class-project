package Server;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ServerGUI extends JFrame {
    private JPanel pnlServerGUI;
    private JPanel pnlTop;
    private JLabel lblLogoServer;
    private JLabel lblServerTitle;
    private JList lstQueries;
    private JTextArea tpQueryLog;
    private JButton btnQuery;
    private JButton btnClearLog;
    private JFrame serverFrame;



    public ServerGUI() {
        serverFrame = new JFrame("Server");
        //DBaseConnection dbc = new DBaseConnection();

        serverFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        serverFrame.add(pnlServerGUI);
        lblServerTitle.setForeground(Color.YELLOW);
        serverFrame.pack();
        serverFrame.setLocationRelativeTo(null);
        serverFrame.setVisible(true);



        btnQuery.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
               // dbc.
                tpQueryLog.setText("Current Registered Users: 0");
            }
        });
        btnClearLog.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                tpQueryLog.setText("");
            }
        });

    }
}
