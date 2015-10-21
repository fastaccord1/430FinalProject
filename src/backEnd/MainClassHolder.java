package backEnd;

import setup.Cryptography;
import setup.NewInstall;
import setup.PostInstall;

/**
 * Created by kreuter on 10/20/15.
 */
public class MainClassHolder {
    private static Database database;
    private static Cryptography cryptography;
    private static NewInstall newInstall;
    private static PostInstall postInstall;


    public MainClassHolder(){
        database = null;
        cryptography = new Cryptography();
        newInstall = new NewInstall();
        postInstall = new PostInstall(cryptography);

        if(!NewInstall.checkInstall()){
            newInstall.createInstall();
        }
        else{
            postInstall.createDatabase();
        }


    }


    public static Cryptography getCryptography() {
        return cryptography;
    }

    public static void setCryptography(Cryptography cryptography) {
        MainClassHolder.cryptography = cryptography;
    }

    public static Database getDatabase() {
        return database;
    }

    public static void setDatabase(Database database) {
        MainClassHolder.database = database;
    }
}
