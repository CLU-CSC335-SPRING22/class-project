package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {
    private JPanel pnlClient;
    private JPanel pnlConnect;
    private JPanel pnlLogin;
    private JTextField tfUsernameLogin;
    private JPasswordField pfPasswordLogin;
    private JButton btnLogin;
    private JButton btnRecover;
    private JButton btnRegister;
    private JLabel lblLogo;
    private JLabel lblWebsite;
    private JTextField tfDomain;
    private JLabel lblDomain;
    private JButton btnConnect;
    private JProgressBar pbConnection;
    private JPanel pnlDashboard;
    private JButton btnDisconnect;
    private JButton btnLogout;
    private JPanel pnlRegister;
    private JPanel pnlChange;
    private JPanel pnlRecover;
    private JTextField tfEmailRecover;
    private JTextField tfEmailRecoverConfirm;
    private JButton btnRecoveryEmail;
    private JLabel lblLogoConnect;
    private JLabel lblWebsiteConnect;
    private JLabel lblUsernameLogin;
    private JLabel lblPasswordLogin;
    private JButton btnChangePassword;
    private JLabel lblLogoDashboard;
    private JLabel lblWebsiteDashboard;
    private JLabel lblWebsiteRecovery;
    private JLabel lblLogoRecovery;
    private JLabel lblRecoveryConfirmEmail;
    private JLabel lblRecoveryEmail;
    private JTextField tfRegisterUsername;
    private JPasswordField pfRegisterPassword;
    private JPasswordField pfRegisterPasswordConfirm;
    private JTextField tfRegisterEmail;
    private JTextField tfRegisterEmailConfirm;
    private JLabel lblLogoRegister;
    private JLabel lblWebsiteRegister;
    private JLabel lblRegisterEmailConfirm;
    private JLabel lblRegisterEmail;
    private JLabel lblRegisterPasswordConfirm;
    private JLabel lblRegisterPassword;
    private JLabel lblRegisterUsername;
    private JButton btnRegisterConfirm;
    private JButton btnRegisterCancel;
    private JPasswordField pfCurrentPassword;
    private JPasswordField pfNewPassword;
    private JPasswordField pfConfirmNewPassword;
    private JButton btnChangePasswordSubmit;
    private JLabel lblLogoChange;
    private JLabel lblWebsiteChange;
    private JLabel lblCurrentPassword;
    private JLabel lblNewPassword;
    private JLabel lblConfirmNewPassword;
    private JButton btnChangeCancel;
    private JFrame clientFrame;

    public ClientGUI() {
        clientFrame = new JFrame("Client");

        clientFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        clientFrame.add(pnlClient);
        clientFrame.pack();
        clientFrame.setLocationRelativeTo(null);
        clientFrame.setVisible(true);

        btnConnect.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlConnect.setVisible(false);
                pnlLogin.setVisible(true);
            }
        });
        btnLogin.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlLogin.setVisible(false);
                pnlDashboard.setVisible(true);
            }
        });
        btnDisconnect.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlLogin.setVisible(false);
                pnlConnect.setVisible(true);
            }
        });
        btnLogout.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlDashboard.setVisible(false);
                pnlLogin.setVisible(true);

            }
        });
        btnRecover.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlLogin.setVisible(false);
                pnlRecover.setVisible(true);
            }
        });
        btnRecoveryEmail.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlRecover.setVisible(false);
                pnlLogin.setVisible(true);
            }
        });
        btnRegisterConfirm.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            public void actionPerformed(ActionEvent e) {
                // Gathers User's Input from Input Fields
                String username = tfRegisterUsername.getText();
                String email = tfRegisterEmail.getText();
                String confirmEmail = tfRegisterEmailConfirm.getText();
                char[] password = pfRegisterPassword.getPassword();
                char[] confirmPassword = pfRegisterPasswordConfirm.getPassword();

                //Add Validation to Register User and Change Password
                Validation verify = new Validation(username, password, confirmPassword, email, confirmEmail);

                if(verify.completeValidation()) {
                    JOptionPane.showMessageDialog(null, "Success!");
                    pnlRegister.setVisible(false);
                    pnlLogin.setVisible(true);

                    // Reset Text Fields
                    tfRegisterUsername.setText("");
                    tfRegisterEmail.setText("");
                    tfRegisterEmailConfirm.setText("");
                    pfRegisterPassword.setText("");
                    pfRegisterPasswordConfirm.setText("");
                }else{
                    JOptionPane.showMessageDialog(null, "Please make sure the format is correct and entries match.");
                    // Reset Text Fields
                    tfRegisterUsername.setText("");
                    tfRegisterEmail.setText("");
                    tfRegisterEmailConfirm.setText("");
                    pfRegisterPassword.setText("");
                    pfRegisterPasswordConfirm.setText("");
                }
            }
        });
        btnRegisterCancel.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlRegister.setVisible(false);
                pnlLogin.setVisible(true);
            }
        });
        btnRegister.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlLogin.setVisible(false);
                pnlRegister.setVisible(true);
            }
        });
        btnChangePasswordSubmit.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Username, email, confirmEmail not needed here
                char[] newPassword = pfNewPassword.getPassword();
                char[] confirmNewPassword = pfConfirmNewPassword.getPassword();

                Validation verifyNewPassword = new Validation(newPassword, confirmNewPassword);

                if(verifyNewPassword.passwordFormatCheck() && verifyNewPassword.passwordMatchCheck()){
                    JOptionPane.showMessageDialog(null, "Success");
                    pnlChange.setVisible(false);
                    pnlDashboard.setVisible(true);

                    // Reset Text Fields
                    pfCurrentPassword.setText("");
                    pfNewPassword.setText("");
                    pfConfirmNewPassword.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Please check format and make sure your entries match.");

                    // Reset Text Fields
                    pfCurrentPassword.setText("");
                    pfNewPassword.setText("");
                    pfConfirmNewPassword.setText("");
                }
            }
        });
        btnChangeCancel.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlChange.setVisible(false);
                pnlDashboard.setVisible(true);
            }
        });
        btnChangePassword.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlDashboard.setVisible(false);
                pnlChange.setVisible(true);
            }
        });
    }


}
