package cs430.finalProject.setup;

/**
 * 430FinalProject
 * Created by kreuter on 10/18/15.
 * @author Kevin Reuter
 */
import cs430.finalProject.backEnd.Database;

import java.io.*;


public class NewInstall {

    private Database database;
    private final String PATH = "/SQL_Scripts/";
    private final String CREATENAME = "CreateDatabase.sql";
    private final String FILLNAME = "CreateEntries.sql";

    /**
     * Default constructor that creates a NewInstall object
     * @param database Database object to be used for connection
     */
    public NewInstall(Database database){
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
            if(!line.equals("") && !line.contains("/*")) {
                System.out.println(line);
                database.executeInsertUpdate(line);
            }
        }
    }

    private void createEntries() throws IOException {
        BufferedReader bufferedReader = getBufferedReader(PATH + FILLNAME);
        String line;
        while((line = bufferedReader.readLine()) != null){
            if(!line.equals("") && !line.contains("/*")) {
                line = line.replace(';', ' ');
                System.out.println(line);
                database.executeInsertUpdate(line);
            }
        }
    }

    private BufferedReader getBufferedReader(String path){
        InputStream inputStream = getClass().getResourceAsStream(path);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        return new BufferedReader(inputStreamReader);
    }

}
