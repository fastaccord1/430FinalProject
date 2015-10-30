package cs430.finalProject.setup;

/**
 * 430FinalProject
 * Created by kreuter on 10/18/15.
 * @author Kevin Reuter
 */
import cs430.finalProject.backEnd.Database;

import java.security.SecureRandom;
import java.sql.ResultSet;
import java.util.Random;


public class NewInstall {

    private Database database;
    private final int NUM_GENERATE = 10;
    private final String[] LEVELS = {"Freshman", "Sophomore", "Junior", "Senior", "Masters", "PHD"};
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
        return rs != null;
    }

    /**
     * This method creates the install by calling the installation methods. It first
     * creates the tables and then inserts the data.
     */
    public void createInstall(){
        createTables();
        insertDepartmentData();
        insertStudentData(null);
        insertFacultyData();
        insertStaffData();
        insertCoursesData();
        insertEnrolledData();
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
                "level VARCHAR(50), age INT, PRIMARY KEY(sid))";
        database.executeInsertUpdate(createStudent);

        String createDepartment = "CREATE TABLE Department(did INT, dname VARCHAR(50), PRIMARY KEY(did))";
        database.executeInsertUpdate(createDepartment);

        String createStaff = "CREATE TABLE Staff(sid INT, sname VARCHAR(50), deptid INT, PRIMARY KEY(sid), " +
                "FOREIGN KEY(deptid) REFERENCES Department(did))";
        database.executeInsertUpdate(createStaff);

        String createFaculty = "CREATE TABLE Faculty(fid INT, fname VARCHAR(50), deptid INT, PRIMARY KEY(fid), " +
                "FOREIGN KEY(deptid) REFERENCES Department(did))";
        database.executeInsertUpdate(createFaculty);

        String createCourses = "CREATE TABLE Courses(cid INT, cname VARCHAR(50), meets_at VARCHAR(50), " +
                "room VARCHAR(50), fid INT REFERENCES Facutly(fid), limit INT, PRIMARY KEY(cid))";
        database.executeInsertUpdate(createCourses);

        String createEnrolled = "CREATE TABLE Enrolled(sid INT, cid INT, exam1 INT, exam2 INT, final INT, " +
                "FOREIGN KEY(sid) REFERENCES Student(sid), FOREIGN KEY(cid) REFERENCES Course(cid))";
        database.executeInsertUpdate(createEnrolled);
    }

    /**
     * Method to insert data into Student table
     * @param sID Array of ints for randomly generated student IDs
     */
    public void insertStudentData(int[] sID){
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
        String query = "INSERT ALL" +
                "INTO Faculty VALUES(201, '', 21)" +
                "INTO Faculty VALUES(202, '', 30)" +
                "INTO Faculty VALUES(203, '', 11)" +
                "INTO Faculty VALUES(204, '', 10)" +
                "INTO Faculty VALUES(205, '', 15)" +
                "INTO Faculty VALUES(206, '', 25)" +
                "INTO Faculty VALUES(207, '', 26)" +
                "INTO Faculty VALUES(208, '', 27)" +
                "INTO Faculty VALUES(209, '', 28)" +
                "INTO Faculty VALUES(210, '', 31)" +
                "SELECT * FROM dual";
        database.executeInsertUpdate(query);
    }

    /**
     * Inserts Staff data into the database
     */
    public void insertStaffData(){
        String query = "INSERT ALL" +
                "INTO Staff VALUES(301, '', 21)" +
                "INTO Staff VALUES(302, '', 30)" +
                "INTO Staff VALUES(303, '', 11)" +
                "INTO Staff VALUES(304, '', 10)" +
                "INTO Staff VALUES(305, '', 15)" +
                "INTO Staff VALUES(306, '', 25)" +
                "INTO Staff VALUES(307, '', 26)" +
                "INTO Staff VALUES(308, '', 27)" +
                "INTO Staff VALUES(309, '', 28)" +
                "INTO Staff Values(310, '', 31)" +
                "SELECT * FROM dual";
        database.executeInsertUpdate(query);
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
        String query = "INSERT ALL" +
                "INTO Department VALUES(21, 'Computer Science')" +
                "INTO Department VALUES(11, 'English')" +
                "INTO Department VALUES(30, 'Electrical Engineering)" +
                "INTO Department VALUES(10, 'Philosophy')" +
                "INTO Department VALUES(15, 'Political Science')" +
                "INTO Department VALUES(25, 'Math')" +
                "INTO Department VALUES(26, 'Physics')" +
                "INTO Department VALUES(27, 'Biology')" +
                "INTO Department VALUES(28, 'Chemistry)" +
                "INTO Department VALUES(31, 'Structural Engineering')" +
                "SELECT * FROM dual";
        database.executeInsertUpdate(query);

    }

    /**
     * Inserts enrolled data into the database
     */
    public void insertEnrolledData(){

    }

    public int[] generateDepartmentNums(int length){
        int[] output = new int[length];
        for(int i = 0; i < length; i++){
            output[i] = generateRandomInt();
        }
        return output;
    }

    public int[] generateStudentID(int length){
        int high = 1999;
        int low = 1000;
        int[] output = new int[length];
        for(int i = 0; i< length; i++){
            output[i] = generateRandomInt(high, low);
        }
        return output;
    }
    public int[] generateFacutlyID(int length){
        int high = 2999;
        int low = 2000;
        int[] output = new int[length];
        for(int i = 0; i < length; i++){
            output[i] = generateRandomInt(high, low);
        }
        return output;
    }

    public int[] generateStaffID(int length){
        int high = 3999;
        int low = 3000;
        int[] output = new int[length];
        for(int i = 0; i < length; i++){
            output[i] = generateRandomInt(high, low);
        }
        return output;
    }

    public int generateRandomInt(int high, int low){
        Random random = new SecureRandom();
        return random.nextInt() % (high - low) + low;
    }

    public int generateRandomInt(){
        Random random = new SecureRandom();
        return random.nextInt();
    }
}
