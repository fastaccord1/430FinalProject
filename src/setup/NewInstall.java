package setup;

/**
 * Created by kreuter on 10/18/15.
 */
import backEnd.Database;
import frontEnd.InitialSetup;

import javax.swing.*;
import java.io.*;
public class NewInstall {
    private static final String INSTALL_FILE = "430.conf";
    //private Boolean installed;
    private String username;
    private String password;
    private static final String INSTALLPATHWIN = System.getProperty("user.home") + "\\" + INSTALL_FILE;
    private static final String INSTALLPATHLIN = System.getProperty("user.home") + "/" + INSTALL_FILE;
    private Cryptography cryptography;

    public NewInstall(Cryptography cryptography){
        this.password = null;
        this.username = null;
        this.cryptography = cryptography;


    }

    /**
     *
     * @return Boolean value for whether install was run previously
     */
    public static Boolean checkInstall(){
        File f = new File(getPath());

        if(f.exists() && !f.isDirectory()) {
            return true;

        } else{
            return false;
        }
    }

    /**
     *
     * @return String path of configuration file
     */
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

    public void createInstall(){

        InitialSetup.main(null);

    }

    public void createTables(){
        Database conn = new Database(username, password);

        String createStudent = "CREATE TABLE Student(sid INT, sname VARCHAR(50), major VARCHAR(50), " +
                "level VARCHAR(50), age INT, PRIMARY KEY(sid)";
        conn.executeInsertUpdate(createStudent);

        String createFaculty = "CREATE TABLE Faculty(fid INT, fname VARCHAR(50), deptid INT, PRIMARY KEY(fid))";
        conn.executeInsertUpdate(createFaculty);

        String createStaff = "CREATE TABLE Staff(sid INT, sname VARCHAR(50), deptid INT, PRIMARY KEY(sid)";
        conn.executeInsertUpdate(createStaff);

        String createDepartment = "CREATE TABLE Department(did INT, dname VARCHAR(50), PRIMARY KEY(did)";
        conn.executeInsertUpdate(createDepartment);

        String createCourses = "CREATE TABLE Courses(cid INT, cname VARCHAR(50), meets_at VARCHAR(50), " +
                "room VARCHAR(50), fid INT REFERENCES Facutly(fid), limit INT, PRIMARY KEY(cid)";
        conn.executeInsertUpdate(createCourses);

        String createEnrolled = "CREATE TABLE Enrolled(sid INT, cid INT, exam1 INT, exam2 INT, final INT, " +
                "FOREIGN KEY(sid) REFERENCES Student(sid), FOREIGN KEY(cid) REFERENCES Course(cid))";
        conn.executeInsertUpdate(createEnrolled);


    }
}
