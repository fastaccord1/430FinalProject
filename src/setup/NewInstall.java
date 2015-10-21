package setup;

/**
 * Created by kreuter on 10/18/15.
 */
import backEnd.Database;
import backEnd.MainClassHolder;
import frontEnd.InitialSetup;
import java.io.File;
import java.util.ArrayList;


public class NewInstall {

    //private Boolean installed;
    private Database database;

    public NewInstall(){
        database = MainClassHolder.getDatabase();

    }

    /**
     *
     * @return Boolean value for whether install was run previously
     */
    public Boolean checkInstall(){
        ArrayList<String> tables = database.getTables();

        return false;
    }

    /**
     *
     * @return String path of configuration file
     */


    public void initialSetup(){

        InitialSetup.main(null);

    }

    public void createTables(){


        String createStudent = "CREATE TABLE Student(sid INT, sname VARCHAR(50), major VARCHAR(50), " +
                "level VARCHAR(50), age INT, PRIMARY KEY(sid)";
        database.executeInsertUpdate(createStudent);

        String createFaculty = "CREATE TABLE Faculty(fid INT, fname VARCHAR(50), deptid INT, PRIMARY KEY(fid))";
        database.executeInsertUpdate(createFaculty);

        String createStaff = "CREATE TABLE Staff(sid INT, sname VARCHAR(50), deptid INT, PRIMARY KEY(sid)";
        database.executeInsertUpdate(createStaff);

        String createDepartment = "CREATE TABLE Department(did INT, dname VARCHAR(50), PRIMARY KEY(did)";
        database.executeInsertUpdate(createDepartment);

        String createCourses = "CREATE TABLE Courses(cid INT, cname VARCHAR(50), meets_at VARCHAR(50), " +
                "room VARCHAR(50), fid INT REFERENCES Facutly(fid), limit INT, PRIMARY KEY(cid)";
        database.executeInsertUpdate(createCourses);

        String createEnrolled = "CREATE TABLE Enrolled(sid INT, cid INT, exam1 INT, exam2 INT, final INT, " +
                "FOREIGN KEY(sid) REFERENCES Student(sid), FOREIGN KEY(cid) REFERENCES Course(cid))";
        database.executeInsertUpdate(createEnrolled);


    }
}
