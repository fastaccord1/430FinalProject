/**
 * Created by kreuter on 10/18/15.
 */

import setup.*;

public class MainClass {
    public static void main(String[] args){
        if(!NewInstall.checkInstall()){
            /*
            Console console = System.console();
            console.printf("Please input username: ");
            String username = console.readLine();

            console.printf("\n");

            console.printf("Please input password: ");
            char[] passwordChars = console.readPassword();
            String password = new String(passwordChars);
            */
            NewInstall install = new NewInstall();

            install.createInstall();
        }


    }
}
