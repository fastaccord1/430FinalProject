package cs430.finalProject.setup;

/**
 * 430FinalProject
 * Created by kreuter on 10/18/15.
 * @author Kevin Reuter
 */
import cs430.finalProject.backEnd.Database;

import java.sql.ResultSet;
import java.util.ArrayList;


public class NewInstall {

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
        String query = "SELECT * FROM Student";
        ResultSet rs = database.executeQuery(query);
        if(rs != null){
            return true;
        }
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

    /**
     * Method to insert data into Student table
     */
    public void instertStudentData(){
        String query = "INSERT ALL" +
                "INTO Student VALUES(101, 'Kimberleigh Vernon', 'Computer Science', 'Junior', 21)" +
                "INTO Student VALUES(102, 'Fern Aukes', 'Electrical Engineering', 'Sophomore', 20" +
                "INTO Student VALUES(103, 'Gerlinde Ravenna', 'English', 'Freshman', 18" +
                "INTO Student VALUES(104, 'Lois P. Huff', 'Philosophy', 'Masters', 24" +
                "INTO Student VALUES(105, 'Lorraine J. Bailey', 'Computer Science', 'Senior', 22" +
                "INTO Student VALUES(106, 'Anthony B. Lucero', 'English', 'PHD', 26" +
                "INTO Student VALUES(107, 'Donna A. Wegman', 'Information Systems Technology', 'Freshman', 18" +
                "INTO Student VALUES(108, 'Margaret J. Bishop', 'Business Administration', 'Masters', 24" +
                "INTO Student VALUES(109, 'Vivian D. Shive', 'Computer Science', 'PHD', 25" +
                "INTO Student VALUES(110, 'Joann O. Cormack', 'Electrical Engineering', 'Junior', 30" +
                "SELECT * FROM dual";
        database.executeInsertUpdate(query);
    }

    /**
     * Inserts faculty data into the database
     */
    public void insertFacultyData(){

    }

    /**
     * Inserts Staff data into the database
     */
    public void insertStaffData(){

    }

    /**
     * Inserts course data into the database
     */
    public void insertCoursesData(){

    }

    /**
     * Inserts department data into the database
     */
    public void insertDepartmentData(){

    }

    /**
     * Inserts enrolled data into the database
     */
    public void insertEnrolledData(){

    }

    /**
     * This method creates the data within the database
     */
    public void createData(){

    }
}
