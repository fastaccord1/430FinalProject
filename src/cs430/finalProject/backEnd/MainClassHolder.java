package cs430.finalProject.backEnd;

import cs430.finalProject.frontEnd.SelectRole;
import cs430.finalProject.setup.NewInstall;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


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
    // String constant for the path to the config file
    private final String PATH = "../config/database.conf";

    /**
     * Default constructor to initialize variables
     */
    public MainClassHolder(){
        String[] userPass = null;
        try {

            userPass = getDatabaseInfo(PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(userPass != null){
            String username = userPass[0];
            String password = userPass[1];
            database = new Database(username, password);
            newInstall = new NewInstall(database);
            checkInstall();
            SelectRole.main(null);
        }
        else{
            System.err.println("Something went wrong!");
            System.exit(1);
        }
    }

    /**
     * Method that initiates a check for the database tables
     */
    public static void checkInstall(){
        /*if(!newInstall.checkInstall()){
            newInstall.createInstall();
        }*/
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
        MainClassHolder.database.searchStudent(-1, null, null, null, -1);
    }

    /**
     * Closes the connection on the database
     */
    public void close(){
        database.closeConnection();
    }

    /**
     * This method gets the username and password from the config file
     * @param path String for the path to the config file
     * @return Array of strings for the username and password
     * @throws IOException
     */
    public String[] getDatabaseInfo(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line, username = null, password = null;
        while((line = bufferedReader.readLine()) != null){
            if(line.contains("username:")){
                username = line.substring(12);
            }
            if(line.contains("password:")){
                password = line.substring(12);
            }
        }
        if(username == null || password == null){
            System.err.println((username == null) ? "Username" : "Password" + " not initialized");
            System.exit(1);
        }
        return new String[]{username, password};
    }

}
