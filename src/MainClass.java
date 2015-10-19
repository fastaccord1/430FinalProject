/**
 * Created by kreuter on 10/18/15.
 */
import backEnd.*;

import setup.*;

import javax.swing.*;
import java.io.Console;

public class MainClass {
    public static void main(String[] args){
        //NewInstall test = new NewInstall("test", "test");
        if(!NewInstall.checkInstall()){
            //String username = JOptionPane.showInputDialog("Please input username:");
            //String password = JOptionPane.show
            Console console = System.console();
            console.printf("Please input username: ");
            String username = console.readLine();

            console.printf("\n");

            console.printf("Please input password: ");
            char[] passwordChars = console.readPassword();
            String password = new String(passwordChars);

            NewInstall install = new NewInstall(username, password);

            install.createInstall();
        }


    }
}
