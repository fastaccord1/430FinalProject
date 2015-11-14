package cs430.finalProject.backEnd;

import oracle.jdbc.driver.OracleDriver;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by kreuter on 11/13/15.
 */
public class StaffDatabase extends Database {
    public StaffDatabase(String username, String password) {
        super();
        try {
            DriverManager.registerDriver(new OracleDriver());
            super.conn = DriverManager.getConnection(super.URL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isStaff(int id) {
        String query = "SELECT * FROM Staff WHERE sid = " + id;
        try {
            return isFound(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
