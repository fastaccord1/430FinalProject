package backEnd;

import frontEnd.InitialSetup;
import setup.NewInstall;
//import setup.PostInstall;

/**
 * Created by kreuter on 10/20/15.
 */
public class MainClassHolder {
    private static Database database;
    private static NewInstall newInstall;


    public MainClassHolder(){
        database = null;
        InitialSetup.main(null);
        newInstall = null;


    }


    public static void checkInstall(){
        newInstall = new NewInstall(database);
        newInstall.checkInstall();
    }

    public static Database getDatabase() {
        return database;
    }

    public static void setDatabase(String username, String password) {
        MainClassHolder.database = new Database(username, password);
    }

}
