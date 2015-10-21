package setup;

import backEnd.Database;
import backEnd.MainClassHolder;

import javax.xml.crypto.Data;
import java.io.*;

/**
 * Created by kreuter on 10/20/15.
 */
public class PostInstall {
    private Cryptography cryptography;
    private static Database database;

    public PostInstall(Cryptography cryptography){
        database = null;
        this.cryptography = cryptography;

    }

    public Database createDatabase(){
        String line, username, password, encPassword;
        try {
            FileReader fileReader = new FileReader(MainClassHolder.getPath());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null){
                if(line.contains("Username:")){
                    username = line.substring(9);
                    System.out.println(username);
                }else if(line.contains("Password:")){
                    encPassword = line.substring(9);
                    password = cryptography.decrypt(encPassword);
                    System.out.println(password);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return database;
    }
}
