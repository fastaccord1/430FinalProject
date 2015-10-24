package cs430.finalProject.backEnd;

import cs430.finalProject.frontEnd.InitialSetup;
import cs430.finalProject.setup.NewInstall;


/**
 * 430FinalProject
 * Created by kreuter on 10/20/15.
 * @author Kevin Reuter
 */
public class MainClassHolder {
    // Database object to be used for connection
    private static Database database;
    // NewInstall object to be used for initializing database
    private static NewInstall newInstall;

    /**
     * Default constructor to initialize variables
     */
    public MainClassHolder(){
        database = null;
        InitialSetup.main(null);
        newInstall = null;
    }

    /**
     * Method that initiates a check for the database tables
     */
    public static void checkInstall(){
        newInstall = new NewInstall(database);
        if(!newInstall.checkInstall()){

        }
    }

    /**
     * Getter for the Database object used for the main connection
     * @return Database object for the database connection
     */
    public static Database getDatabase() {
        return database;
    }

    /**
     * Setter method that creates a new Database object
     * @param username String username to be used for database
     * @param password String password to be used for the database
     */
    public static void setDatabase(String username, String password) {
        MainClassHolder.database = new Database(username, password);
    }

    /**
     * Closes the connection on the database
     */
    public void close(){
        database.closeConnection();
    }

}
