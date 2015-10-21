/**
 * Created by kreuter on 10/18/15.
 */

import backEnd.Database;
import setup.*;

public class MainClass {
    private static Database database;
    private static Cryptography cryptography;

    public MainClass(){
        database = null;
        cryptography = new Cryptography();
    }

    public static void main(String[] args){
        MainClass mainClass = new MainClass();

        if(!NewInstall.checkInstall()){

            NewInstall install = new NewInstall(cryptography);

            install.createInstall();
        }






    }

    private void setDatabase(String username, String password){
        database = new Database(username, password);
    }

    public static Database getDatabase(){
        return database;
    }

    public static Cryptography getCryptography(){
        return cryptography;
    }
}
