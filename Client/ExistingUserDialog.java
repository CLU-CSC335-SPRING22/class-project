import javax.swing.*;
import java.awt.event.*;

public class ExistingUserDialog extends JDialog {
    private JPanel contentPane;
    private JButton btnExisitingYes;
    private JButton btnExistingNo;
    private JLabel lblExisiting;

    public ExistingUserDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnExisitingYes);

        btnExisitingYes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onYes();
            }
        });

        btnExistingNo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onYes() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        ExistingUserDialog dialog = new ExistingUserDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
