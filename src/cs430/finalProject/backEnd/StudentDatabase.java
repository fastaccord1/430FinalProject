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
public class StudentDatabase extends Database {

    public StudentDatabase(String username, String password) {
        super();
        try {
            DriverManager.registerDriver(new OracleDriver());
            super.conn = DriverManager.getConnection(super.URL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Searches the database for a specific student
     *
     * @param id    int for the wanted student's id
     * @param name  String for the wanted student's name
     * @param major String for the wanted student's major
     * @param level String for the wanted student's level
     * @param age   String for the wanted student's age
     * @return Object[][] Two-dimensional array of results.
     */
    public Object[][] searchStudent(int id, String name, String major, String level, int age) throws SQLException {
        String query = "SELECT * FROM Student WHERE";

        ArrayList<String> conditions = new ArrayList<>();

        if (id != -1) {
            conditions.add(" sid = " + id);
        }
        if (name != null) {
            conditions.add(" sname = " + name);
        }
        if (major != null) {
            conditions.add(" major = " + major);
        }
        if (level != null) {
            conditions.add(" s_level = " + level);
        }
        if (age != -1) {
            conditions.add(" age = " + age);
        }

        query = finishQuery(query, conditions);

        int length = getCount(query);
        Object[][] output = new Object[length][5];
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        for (int i = 0; rs.next(); i++) {
            output[i][0] = rs.getInt("sid");
            output[i][1] = rs.getString("sname");
            output[i][2] = rs.getString("major");
            output[i][3] = rs.getString("s_level");
            output[i][4] = rs.getInt("age");
        }
        return output;
    }

    /**
     * This method returns entire Student table
     *
     * @return Two dimensional array of results
     * @throws SQLException
     */
    public Object[][] searchStudent() throws SQLException {
        String query = "SELECT * FROM Student";
        int length = getCount(query);
        Object[][] output = new Object[length][5];
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        int i = 0;
        while (rs.next()) {
            output[i][0] = rs.getInt("sid");
            output[i][1] = rs.getString("sname");
            output[i][2] = rs.getString("major");
            output[i][3] = rs.getString("s_level");
            output[i][4] = rs.getInt("age");
            i++;
        }
        rs.close();
        statement.close();
        return output;
    }

    public boolean isStudent(int id) {
        String query = "SELECT * FROM Student WHERE sid = " + id;
        try {
            return isFound(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
