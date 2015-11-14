package cs430.finalProject.backEnd;

import oracle.jdbc.driver.OracleDriver;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by kreuter on 11/13/15.
 */
public class GeneralDatabase extends Database {
    public GeneralDatabase(String username, String password) {
        super();
        try {
            DriverManager.registerDriver(new OracleDriver());
            super.conn = DriverManager.getConnection(super.URL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
