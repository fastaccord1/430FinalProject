package cs430.finalProject.backEnd;

import oracle.jdbc.driver.OracleDriver;

import java.sql.DriverManager;
import java.sql.SQLException;

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
}
