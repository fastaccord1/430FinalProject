package cs430.finalProject.backEnd;

import java.sql.Connection;
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
     * @param connection
     */
    public GeneralDatabase(Connection connection) {
        super();
        super.conn = connection;
    }

    /**
     * Gets all entries from Department table
     *
     * @return Two-dimensional array with all results from Department table
     */
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

    /**
     * Searches Department table for specific department
     *
     * @param did   ID for the department to be found
     * @param dName Name for the department to be found
     * @return Two-dimensional array of departments found
     */
    public Object[][] searchDepartment(int did, String dName) {
        String query = "SELECT * FROM Department WHERE";
        ArrayList<String> conditions = new ArrayList<>();
        if (did != -1) {
            conditions.add(" did = " + did);
        }
        if (dName != null) {
            conditions.add(" dname LIKE '%" + dName + "%'");
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

    public String[] getDepartmentNames() {
        String query = "SELECT dname FROM Department";
        try {
            String[] output = new String[getCount(query) + 1];
            ResultSet rs = executeQuery(query);
            output[0] = "<Select>";
            for (int i = 1; rs.next(); i++) {
                output[i] = rs.getString("dname");
            }
            return output;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getDepartmentId(String deptName) {
        String query = "SELECT did FROM Department WHERE dname = '" + deptName + "'";
        ResultSet rs;
        int output = -1;
        try {
            rs = executeQuery(query);
            if (rs.next()) {
                output = rs.getInt("did");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }

    public void insertDepartment(int id, String name) {
        String statement = "INSERT INTO Department VALUE(";
        statement += id + ", ";
        statement += "'" + name + "')";

        try {
            executeInsertUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDepartment(int oldId, int id, String name) {
        String statement = "UPDATE Department SET ";
        statement += "did = " + id + ", ";
        statement += "dname = '" + name + "' ";
        statement += "WHERE did = " + oldId;

        try {
            executeInsertUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
