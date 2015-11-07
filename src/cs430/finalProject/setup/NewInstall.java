package cs430.finalProject.setup;

/**
 * 430FinalProject
 * Created by kreuter on 10/18/15.
 * @author Kevin Reuter
 */
import cs430.finalProject.backEnd.Database;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.util.Random;


public class NewInstall {

    private Database database;
    private final String PATH = "../SQL_SCRIPTS/";
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
        FileReader fileReader = new FileReader(PATH + CREATENAME);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while((line = bufferedReader.readLine()) != null){
            database.executeInsertUpdate(line);
        }
    }

    private void createEntries() throws IOException {
        FileReader fileReader = new FileReader(PATH + FILLNAME);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while((line = bufferedReader.readLine()) != null){
            database.executeInsertUpdate(line);
        }
    }

}
