package cs430.finalProject.backEnd;

import oracle.jdbc.driver.OracleDriver;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by kreuter on 11/13/15.
 */
public class FacultyDatabase extends Database {
    public FacultyDatabase(String username, String password) {
        super();
        try {
            DriverManager.registerDriver(new OracleDriver());
            super.conn = DriverManager.getConnection(super.URL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
     * @throws SQLException
     */
    public Object[][] facultySearch(int fid, String fname, int deptId) throws SQLException {
        Object[][] output = null;
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
        int count = getCount(query);
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        output = new Object[count][3];
        for (int i = 0; rs.next(); i++) {
            output[i][0] = rs.getInt("fid");
            output[i][1] = rs.getString("fname");
            output[i][2] = rs.getInt("deptid");
        }

        return output;
    }

    /**
     * Gathers all items from Faculty table
     *
     * @return Two-dimensional array of results from faculty table
     * @throws SQLException
     */
    public Object[][] facultySearch() throws SQLException {
        Object[][] output = null;
        String query = "SELECT * FROM Faculty";
        int count = getCount(query);
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        output = new Object[count][3];

        for (int i = 0; rs.next(); i++) {
            output[i][0] = rs.getInt("fid");
            output[i][1] = rs.getString("fname");
            output[i][2] = rs.getInt("deptid");
        }

        return output;
    }
}
