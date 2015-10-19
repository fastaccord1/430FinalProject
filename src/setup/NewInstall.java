package setup;

/**
 * Created by kreuter on 10/18/15.
 */
import backEnd.Database;

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

    public void createTables(){
        Database conn = new Database(username, password);

        String createStudent = "CREATE TABLE Student(sid INT, sname VARCHAR(50), major VARCHAR(50), level VARCHAR(50), age INT, PRIMARY KEY(sid)";
        conn.executeInsertUpdate(createStudent);

        String createFaculty = "CREATE TABLE Faculty(fid INT, fname VARCHAR(50), deptid INT, PRIMARY KEY(fid))";
        conn.executeInsertUpdate(createFaculty);

        String createStaff = "CREATE TABLE Staff(sid INT, sname VARCHAR(50), deptid INT, PRIMARY KEY(sid)";
        conn.executeInsertUpdate(createStaff);

        String createDepartment = "CREATE TABLE Department(did INT, dname VARCHAR(50), PRIMARY KEY(did)";
        conn.executeInsertUpdate(createDepartment);

        String createCourses = "CREATE TABLE Courses(cid INT, cname VARCHAR(50), meets_at VARCHAR(50), room VARCHAR(50), fid INT REFERENCES Facutly(fid), limit INT, PRIMARY KEY(cid)";
        conn.executeInsertUpdate(createCourses);

        String createEnrolled = "CREATE TABLE Enrolled(sid INT, cid INT, exam1 INT, exam2 INT, final INT, FOREIGN KEY(sid) REFERENCES Student(sid), FOREIGN KEY(cid) REFERENCES Course(cid))";
        conn.executeInsertUpdate(createEnrolled);


    }
}
