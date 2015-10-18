package setup;

/**
 * Created by kreuter on 10/18/15.
 */
import java.io.*;
public class newInstall {
    private static String INSTALL_FILE = "430.conf";
    private Boolean installed;
    private String username;
    private String password;

    public newInstall(String username, String password){
        this.password = password;
        this.username = username;
        this.installed = false;
        checkInstall();
    }
    protected void checkInstall(){
        File f = new File(System.getProperty("user.home") + "/" + INSTALL_FILE);
        if(f.exists() && !f.isDirectory()) {
            createInstall();
        } else{
            installed = true;
            return;
        }
    }

    protected void createInstall(){
        installed = true;
    }
}
