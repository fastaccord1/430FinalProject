package setup;

/**
 * Created by kreuter on 10/18/15.
 */
import backEnd.Database;
import backEnd.MainClassHolder;
import frontEnd.InitialSetup;
import java.io.File;


public class NewInstall {

    //private Boolean installed;
    private String username;
    private String password;


    public NewInstall(){
        this.password = null;
        this.username = null;


    }

    /**
     *
     * @return Boolean value for whether install was run previously
     */
    public static Boolean checkInstall(){
        File f = new File(MainClassHolder.getPath());

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
