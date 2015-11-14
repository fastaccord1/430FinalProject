package cs430.finalProject.backEnd;

import oracle.jdbc.driver.OracleDriver;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Child class of Database for staff-related actions
 * Created by kreuter on 11/13/15.
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
     * @throws SQLException
     */
    public Object[][] staffSearch() throws SQLException {
        String query = "SELECT * FROM Staff";
        int count = getCount(query);
        ResultSet rs = executeQuery(query);
        Object[][] output = new Object[count][3];

        for (int i = 0; rs.next(); i++) {
            output[i][0] = rs.getInt("sid");
            output[i][1] = rs.getString("sname");
            output[i][2] = rs.getInt("deptid");
        }

        return output;
    }

    /**
     * Searches Staff table for a specific staff member
     *
     * @param sid    ID for the staff member to be found. -1 if not searched
     * @param sname  Name for the staff member to be found. null if not searched
     * @param deptid Department ID for the staff member to be found. -1 if not searched
     * @return Two-dimensional array of the results.
     * @throws SQLException
     */
    public Object[][] staffSearch(int sid, String sname, int deptid) throws SQLException {
        String query = "SELECT * FROM Staff WHERE";
        ArrayList<String> conditions = new ArrayList<>();
        if (sid != -1) {
            conditions.add(" sid = " + sid);
        }
        if (sname != null) {
            conditions.add(" sname = " + sname);
        }
        if (deptid != -1) {
            conditions.add(" deptid = " + deptid);
        }

        query = finishQuery(query, conditions);

        int count = getCount(query);
        Object[][] output = new Object[count][3];
        ResultSet rs = executeQuery(query);
        for (int i = 0; rs.next(); i++) {
            output[i][0] = rs.getInt("sid");
            output[i][1] = rs.getString("sname");
            output[i][2] = rs.getInt("deptid");
        }

        return output;


    }
}
