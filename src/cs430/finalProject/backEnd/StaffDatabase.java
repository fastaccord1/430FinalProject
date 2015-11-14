package cs430.finalProject.backEnd;

import oracle.jdbc.driver.OracleDriver;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Child class of Database for staff-related actions
 * Created by Kevin Reuter on 11/13/15.
 *
 * @author Kevin Reuter
 */
public class StaffDatabase extends Database {

    /**
     * Constructor for the StaffDatabase Class
     *
     * @param username Username for the database
     * @param password Password for the database
     */
    public StaffDatabase(String username, String password) {
        super();
        try {
            DriverManager.registerDriver(new OracleDriver());
            super.conn = DriverManager.getConnection(super.URL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks to see if ID belongs to a staff member
     *
     * @param id The ID to be checked
     * @return True if the ID belongs to a staff member. False if it doesn't.
     */
    public boolean isStaff(int id) {
        String query = "SELECT * FROM Staff WHERE sid = " + id;
        try {
            return isFound(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * Gets all data from Staff table
     *
     * @return Two-dimensional array of all staff entries
     */
    public Object[][] staffSearch() {
        String query = "SELECT * FROM Staff";
        try {
            return getStaffFacultyResults(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Searches Staff table for a specific staff member
     *
     * @param sid    ID for the staff member to be found. -1 if not searched
     * @param sName  Name for the staff member to be found. null if not searched
     * @param deptId Department ID for the staff member to be found. -1 if not searched
     * @return Two-dimensional array of the results.
     */
    public Object[][] staffSearch(int sid, String sName, int deptId) {
        String query = "SELECT * FROM Staff WHERE";
        ArrayList<String> conditions = new ArrayList<>();
        if (sid != -1) {
            conditions.add(" sid = " + sid);
        }
        if (sName != null) {
            conditions.add(" sname = " + sName);
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
     * Inserts new staff member into the database
     *
     * @param sid    SID for the new staff member
     * @param sName  Name for the new staff member
     * @param deptId Department ID for the new staff member
     */
    public void insertStaff(int sid, String sName, int deptId) {
        try {
            executeFacultyStaffInsert("Staff", sid, sName, deptId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates a staff member's entry to new data
     *
     * @param oldSid Old SID for the staff member to be updated
     * @param newSid New SID for the staff member
     * @param sName  New name for the staff member
     * @param deptId New Department ID for the staff member
     */
    public void updateStaff(int oldSid, int newSid, String sName, int deptId) {
        String statement = "UPDATE Student SET ";
        statement += "sid = " + newSid + ", ";
        statement += "sname = '" + sName + "', ";
        statement += "deptid = " + deptId;
        statement += " WHERE sid = " + oldSid;

        try {
            executeInsertUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
