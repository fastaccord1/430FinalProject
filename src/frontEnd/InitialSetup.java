package frontEnd;

import setup.NewInstall;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by kreuter on 10/18/15.
 */
public class InitialSetup extends JFrame{
    private JPanel InitialSetupPane;
    private JPanel SetupPaneNorth;
    private JPanel SetupPaneWest;
    private JPanel SetupPaneEast;
    private JLabel InitialSetupLabel;
    private JLabel UsernameLabel;
    private JLabel PasswordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton submit;
    private JButton clear;
    private static JFrame frame;

    public InitialSetup(){
        super("Initial Setup");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter writerOut = new FileWriter(NewInstall.getPath());
                    BufferedWriter outputWriter = new BufferedWriter(writerOut);
                    String output;
                    output = "Username:" + usernameField.getText() + "\n";
                    output += "Password:" + new String(passwordField.getPassword());
                    outputWriter.write(output);
                    outputWriter.flush();
                    outputWriter.close();
                    frame.dispose();

                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameField.setText("");
                passwordField.setText("");
            }
        });
    }


    public static void main(String[] args) {
        frame = new JFrame("InitialSetup");
        frame.setContentPane(new InitialSetup().InitialSetupPane);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
