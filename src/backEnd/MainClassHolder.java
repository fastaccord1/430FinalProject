package backEnd;

import frontEnd.InitialSetup;
import setup.NewInstall;
//import setup.PostInstall;

/**
 * Created by kreuter on 10/20/15.
 */
public class MainClassHolder {
    private static Database database;
    //private static Cryptography cryptography;
    private static NewInstall newInstall;
    //private static PostInstall postInstall;
    private static final String INSTALL_FILE = "430.conf";
    private static final String INSTALLPATHWIN = System.getProperty("user.home") + "\\" + INSTALL_FILE;
    private static final String INSTALLPATHLIN = System.getProperty("user.home") + "/" + INSTALL_FILE;


    public MainClassHolder(){
        database = null;
        InitialSetup.main(null);
        //cryptography = new Cryptography();
        newInstall = new NewInstall();
        //postInstall = new PostInstall();

        if(!NewInstall.checkInstall()){
            newInstall.createInstall();
        }
        else{
            //postInstall.createDatabase();
        }


    }


    /*public static Cryptography getCryptography() {
        return cryptography;
    }*/

    /*public static void setCryptography(Cryptography cryptography) {
        MainClassHolder.cryptography = cryptography;
    }*/

    public static Database getDatabase() {
        return database;
    }

    public static void setDatabase(String username, String password) {
        MainClassHolder.database = new Database(username, password);
    }

    public static String getPath(){
        String path = null;
        if(System.getProperty("os.name").equals("Windows")){
            path = INSTALLPATHWIN;
        }else if(System.getProperty("os.name").equals("Linux")){
            path = INSTALLPATHLIN;
        }else{
            System.out.println("Something went wrong");
        }
        System.out.println(path);
        return path;
    }
}
