package cs430.finalProject.backEnd;

import oracle.jdbc.driver.OracleDriver;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Child class of Database to handle faculty-related actions.
 * Created by kreuter on 11/13/15.
 *
 * @author Kevin Reuter
 */
public class FacultyDatabase extends Database {
    /**
     * Constructor for the FacultyDatabase Class
     *
     * @param username Username for the database
     * @param password Password for the database
     */
    public FacultyDatabase(String username, String password) {
        super();
        try {
            DriverManager.registerDriver(new OracleDriver());
            super.conn = DriverManager.getConnection(super.URL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks database to see if ID belongs to Faculty
     *
     * @param id The ID to be checked
     * @return True if the ID belongs to a faculty. False if it doesn't.
     */
    public boolean isFaculty(int id) {
        String query = "SELECT * FROM Faculty WHERE fid = " + id;
        try {
            return isFound(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Searches Faculty table for specific entries
     *
     * @param fid    Faculty ID to be found -1 if not searched
     * @param fname  Name of faculty member to be found null if not searched
     * @param deptId Department ID of faculty -1 if not searched
     * @return Two-dimensional array of items found from database
     */
    public Object[][] facultySearch(int fid, String fname, int deptId) {
        Object[][] output;
        String query = "SELECT * FROM FACULTY WHERE";
        ArrayList<String> conditions = new ArrayList<>();
        if (fid != -1) {
            conditions.add(" fid = " + fid);
        }
        if (fname != null) {
            conditions.add(" fname = " + fname);
        }
        if (deptId != -1) {
            conditions.add(" deptid = " + deptId);
        }

        query = finishQuery(query, conditions);
        try {
            return getStaffFacultyResults(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Gathers all items from Faculty table
     *
     * @return Two-dimensional array of results from faculty table
     * @throws SQLException
     */
    public Object[][] facultySearch() {
        Object[][] output;
        String query = "SELECT * FROM Faculty";
        try {
            return getStaffFacultyResults(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void insertFaculty(int fid, String fName, int deptId) {
        try {
            executeFacultyStaffInsert("Faculty", fid, fName, deptId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
