package cs430.finalProject.backEnd;

import java.sql.Connection;
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
     * @param connection Connection object from MainClassHolder
     */
    public FacultyDatabase(Connection connection) {
        super();
        super.conn = connection;
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
     * @param dName Department ID of faculty -1 if not searched
     * @return Two-dimensional array of items found from database
     */
    public Object[][] facultySearch(int fid, String fname, String dName) {
        Object[][] output;
        String query = "SELECT * FROM facultyView WHERE";
        ArrayList<String> conditions = new ArrayList<>();
        if (fid != -1) {
            conditions.add(" fid = " + fid);
        }
        if (fname != null) {
            conditions.add(" fname LIKE '%" + fname + "%'");
        }
        if (dName != null) {
            conditions.add(" dname LIKE '" + dName + "'");
        }

        query = finishQuery(query, conditions);
        try {
            return getStaffFacultyResults(query, new String[]{"fid", "fname", "dname"});
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Gathers all items from Faculty table
     *
     * @return Two-dimensional array of results from faculty table
     */
    public Object[][] facultySearch() {
        Object[][] output;
        String query = "SELECT * FROM facultyView";
        try {
            return getStaffFacultyResults(query, new String[]{"fid", "fname", "dname"});
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void insertFaculty(int fid, String fName, int deptId) {
        String statement = "INSERT INTO Faculty VALUES (" + fid + ", '" + fName + "', " + deptId + ")";
        try {
            executeInsertUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFaculty(int oldFid, int fid, String name, int deptId) {
        String statement = "UPDATE Faculty SET";
        statement += " fid = " + fid + ", ";
        statement += "name = '" + name + "', ";
        statement += "deptid = " + deptId;
        statement += " WHERE fid = " + oldFid;
        try {
            executeInsertUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String[] getFacNames() {
        String query = "SELECT fname FROM Faculty";
        try {
            ResultSet rs = executeQuery(query);
            String[] output = new String[getCount(query) + 1];
            output[0] = "<Select>";
            for (int i = 1; rs.next(); i++) {
                output[i] = rs.getString("fname");
            }
            return output;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
