package cs430.finalProject.setup;

/**
 * 430FinalProject
 * Created by kreuter on 10/18/15.
 * @author Kevin Reuter
 */
import cs430.finalProject.backEnd.Database;

import java.io.*;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.util.Random;


public class NewInstall {

    private Database database;
    private final String PATH = "/cs430/finalProject/SQL_SCRIPTS/";
    private final String CREATENAME = "CreateDatabase.sql";
    private final String FILLNAME = "CreateEntries.sql";

    /**
     * Default constructor that creates a NewInstall object
     * @param database Database object to be used for connection
     */
    public NewInstall(Database database){
        //firstNames = {};
        this.database = database;
        try {
            createTables();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            createEntries();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void createTables() throws IOException {
        BufferedReader bufferedReader = getBufferedReader(PATH + CREATENAME);
        String line;
        while((line = bufferedReader.readLine()) != null){
            database.executeInsertUpdate(line);
        }
    }

    private void createEntries() throws IOException {
        BufferedReader bufferedReader = getBufferedReader(PATH + FILLNAME);
        String line;
        while((line = bufferedReader.readLine()) != null){
            database.executeInsertUpdate(line);
        }
    }

    private BufferedReader getBufferedReader(String path){
        InputStream inputStream = getClass().getResourceAsStream(path);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        return new BufferedReader(inputStreamReader);
    }

}
