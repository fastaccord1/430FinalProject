package cs430.finalProject.backEnd;

import java.sql.Connection;
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
     * @param connection The Connection object for the database to be used
     */
    public StaffDatabase(Connection connection) {
        super();
        super.conn = connection;
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
        String query = "SELECT * FROM staffView";
        try {
            return getStaffFacultyResults(query, new String[]{"sid", "sname", "dname"});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Searches Staff table for a specific staff member
     *
     * @param sid   ID for the staff member to be found. -1 if not searched
     * @param sName Name for the staff member to be found. null if not searched
     * @param dName Department ID for the staff member to be found. -1 if not searched
     * @return Two-dimensional array of the results.
     */
    public Object[][] staffSearch(int sid, String sName, String dName) {
        String query = "SELECT * FROM staffView WHERE";
        ArrayList<String> conditions = new ArrayList<>();
        if (sid != -1) {
            conditions.add(" sid = " + sid);
        }
        if (sName != null) {
            conditions.add(" sname LIKE '%" + sName + "%'");
        }
        if (dName != null) {
            conditions.add(" dname LIKE '" + dName + "'");
        }

        query = finishQuery(query, conditions);
        try {
            return getStaffFacultyResults(query, new String[]{"sid", "sname", "dname"});
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
        String statement = "INSERT INTO Staff VALUES(" + sid + ", '" + sName + "', " + deptId + ")";
        try {
            executeInsertUpdate(statement);
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
        String statement = "UPDATE Staff SET ";
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

    /**
     * Deletes a staff member from the database
     *
     * @param id The ID for the staff member to be deleted
     */
    public void deleteStaff(int id) {
        String statement = "DELETE FROM Staff WHERE sid = " + id;
        try {
            executeInsertUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
