package cs430.finalProject.backEnd;

import oracle.jdbc.driver.OracleDriver;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class handles database operations not related to Faculty, Staff or Students
 * Created by kreuter on 11/13/15.
 *
 * @author Kevin Reuter
 */
public class GeneralDatabase extends Database {

    /**
     * Constructor for GeneralDatabase class
     *
     * @param username Username for the database
     * @param password Password for the database
     */
    public GeneralDatabase(String username, String password) {
        super();
        try {
            DriverManager.registerDriver(new OracleDriver());
            super.conn = DriverManager.getConnection(super.URL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Object[][] searchDepartment() {
        String query = "SELECT * FROM Department";
        Object[][] output;
        try {
            int count = getCount(query);
            output = new Object[count][2];
            ResultSet rs = executeQuery(query);
            for (int i = 0; rs.next(); i++) {
                output[i][0] = rs.getInt("did");
                output[i][1] = rs.getString("dname");
            }
            return output;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object[][] searchDepartment(int did, String dName) {
        String query = "SELECT * FROM Department WHERE";
        ArrayList<String> conditions = new ArrayList<>();
        if (did != -1) {
            conditions.add(" did = " + did);
        }
        if (dName != null) {
            conditions.add(" dname = " + dName);
        }

        try {
            query = finishQuery(query, conditions);
            int count = getCount(query);
            Object[][] output = new Object[count][2];
            ResultSet rs = executeQuery(query);
            for (int i = 0; rs.next(); i++) {
                output[i][0] = rs.getInt("did");
                output[i][1] = rs.getString("dname");
            }
            return output;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
