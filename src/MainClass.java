/**
 * Created by kreuter on 10/18/15.
 */

import backEnd.Database;
import setup.*;

public class MainClass {
    private static Database database;

    public MainClass(){
        database = null;
    }

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

    private void setDatabase(String username, String password){
        database = new Database(username, password);
    }

    public static Database getDatabase(){
        return database;
    }
}
