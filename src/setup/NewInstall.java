package setup;

/**
 * Created by kreuter on 10/18/15.
 */
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
public class NewInstall {
    private final String INSTALL_FILE = "430.conf";
    private String osName;
    //private Boolean installed;
    private String username;
    private String password;
    private String installPath;

    public NewInstall(String username, String password){
        this.password = password;
        this.username = username;
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

    }
    public Boolean checkInstall(){

        File f = new File(installPath);
        if(f.exists() && !f.isDirectory()) {
            return true;

        } else{
            return false;
        }
    }

    public void createInstall(){
        String output;
        Cryptography crypto = new Cryptography();
        String encryptedPass = crypto.encrypt(password);
        System.out.println(encryptedPass);
    }
}
