package setup;

/**
 * Created by kreuter on 10/18/15.
 */
import java.io.*;
public class newInstall {
    private static String INSTALL_FILE = "430.conf";
    private String osName;
    private Boolean installed;
    private String username;
    private String password;
    private String installPath;

    public newInstall(String username, String password){
        this.password = password;
        this.username = username;
        this.installed = false;
        this.osName = System.getProperty("os.name");

        this.installPath = null;
        String userHome = System.getProperty("user.home");
        if(osName.equals("Windows")) {
            this.installPath = userHome + "\\" + INSTALL_FILE;
        } else if(osName.equals("Linux")){
            this.installPath = userHome + "/" + INSTALL_FILE;
        } else{
            System.out.println("Something didn't go right");
        }

        checkInstall();
    }
    protected void checkInstall(){
        if(installed) return;

        File f = new File(installPath);
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
