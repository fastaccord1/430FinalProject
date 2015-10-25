package cs430.finalProject.setup;

/**
 * 430FinalProject
 * Created by kreuter on 10/18/15.
 * @author Kevin Reuter
 */
import cs430.finalProject.backEnd.Database;

import java.util.ArrayList;


public class NewInstall {

    //private Boolean installed;
    private Database database;

    /**
     * Default constructor that creates a NewInstall object
     * @param database Database object to be used for connection
     */
    public NewInstall(Database database){
        this.database = null;
    }

    /**
     * This method checks to see if the program was run previously
     * @return Boolean value for whether install was run previously
     */
    public Boolean checkInstall(){
        ArrayList<String> tables = database.getTables();

        return false;
    }

    /**
     * This method sets the database
     * @param database a Database object for the database connection
     */
    public void setDatabase(Database database){
        this.database = database;
    }

    /**
     * This method creates the tables required for running the program
     */
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

    public void instertStudentData(){
        String query = "INSERT ALL" +
                "INTO Student VALUES(101, '', 'Computer Science', 'Junior', 21)" +
                "INTO Student VALUES(102, '', 'Electrical Engineering', 'Sophomore', 20" +
                "INTO Student VALUES(103, '', 'English', 'Freshman', 18" +
                "INTO Student VALUES(104, '', 'Philosophy', 'Masters', 24" +
                "INTO Student VALUES(105, '', 'Computer Science', 'Senior', 22" +
                "INTO Student VALUES(106, '', 'English', 'PHD', 26" +
                "INTO Student VALUES(107, '', 'Information Systems Technology', 'Freshman', 18" +
                "INTO Student VALUES(108, '', 'Business Administration', 'Masters', 24" +
                "INTO Student VALUES(109, '', 'Computer Science', 'PHD', 25" +
                "INTO Student VALUES(110, '', 'Electrical Engineering', 'Junior', 30" +
                "SELECT * FROM dual";
        database.executeInsertUpdate(query);
    }

    /**
     * This method creates the data within the database
     */
    public void createData(){

    }
}
