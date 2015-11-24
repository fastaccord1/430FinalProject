package cs430.finalProject.backEnd;
/**
 * 430FinalProject
 * Created by kreuter on 10/18/15.
 *
 * @author Kevin Reuter
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Database {
    // Stores the url to the database
    protected final String URL = "jdbc:oracle:thin:@dbserv.cs.siu.edu:1521:cs";
    // Connection object for the database
    protected Connection conn;

    /**
     * Default constructor that initializes variable
     */
    public Database() {
        conn = null;
    }

    /**
     * This method closes the connection to the database
     */
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Finishes the query from the conditions in the ArrayList
     *
     * @param query      The query to be completed
     * @param conditions An ArrayList of the conditions to be added.
     * @return The completed query to be run.
     */
    protected String finishQuery(String query, ArrayList<String> conditions) {
        if (conditions.size() == 1) {
            query += conditions.get(0);
        } else {
            for (int i = 0; i < conditions.size() - 1; i++) {
                query += conditions.get(i);
                query += " AND";
            }
            query += conditions.get(conditions.size() - 1);
        }

        return query;
    }

    /**
     * Method to get the count for a specific query
     *
     * @param startingQuery PreparedStatement to be used for the count
     * @return int count for results
     * @throws SQLException
     */
    protected int getCount(String startingQuery) throws SQLException {
        int out;
        String countQuery = "SELECT COUNT(*) FROM (";
        countQuery += startingQuery;
        countQuery += ")";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(countQuery);
        if (rs.next()) {
            out = rs.getInt(1);
        } else {
            return -1;
        }
        rs.close();
        statement.close();
        return out;
    }

    /**
     * Checks to see if query returns results
     *
     * @param query The query to be checked
     * @return True if query returned results. False if it didn't.
     * @throws SQLException
     */
    protected boolean isFound(String query) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            rs.close();
            statement.close();
            return true;
        }
        return false;
    }

    /**
     * Executes a query and gives the ResultSet
     *
     * @param query Query to be run against the database
     * @return The ResultSet from the query being run.
     * @throws SQLException
     */
    protected ResultSet executeQuery(String query) throws SQLException {
        Statement statement = conn.createStatement();
        return statement.executeQuery(query);
    }

    /**
     * Executes Insert or Update against the database.
     *
     * @param query The SQL statement to be run against the database.
     * @throws SQLException
     */
    protected void executeInsertUpdate(String query) throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute(query);
    }

    /**
     * Separates results from query into array for table
     *
     * @param query       The query to be run
     * @param columnNames The column names to be grabbed
     * @return A two-dimensional array for the table
     * @throws SQLException
     */
    protected Object[][] getStaffFacultyResults(String query, String[] columnNames) throws SQLException {
        Object[][] output;
        int count = getCount(query);
        output = new Object[count][3];
        ResultSet rs = executeQuery(query);
        for (int i = 0; rs.next(); i++) {
            output[i][0] = rs.getInt(columnNames[0]);
            output[i][1] = rs.getString(columnNames[1]);
            output[i][2] = rs.getString(columnNames[2]);
        }
        return output;
    }

    /**
     * Separates student results from query into array for table
     *
     * @param query The query to be run
     * @return A two-dimensional array for the table
     * @throws SQLException
     */
    protected Object[][] getStudentResults(String query) throws SQLException {
        Object[][] output = new Object[getCount(query)][5];
        ResultSet rs = executeQuery(query);
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
     * Separates enrolled student results from query into array for table
     *
     * @param query The query to be run
     * @return A two-dimensional array for the table
     * @throws SQLException
     */
    protected Object[][] getStudentEnrolledResults(String query) throws SQLException {
        Object[][] output = new Object[getCount(query)][5];
        ResultSet rs = executeQuery(query);
        for (int i = 0; rs.next(); i++) {
            output[i][0] = rs.getString("cid");
            output[i][1] = rs.getString("sname");
            output[i][2] = rs.getInt("exam1");
            output[i][3] = rs.getInt("exam2");
            output[i][4] = rs.getInt("final");
        }
        return output;
    }

    /**
     * Separates enrolled results for faculty into array for a table
     *
     * @param query The query to be run
     * @return A two-dimensional array for the table
     * @throws SQLException
     */
    protected Object[][] getFacultyEnrolledResults(String query) throws SQLException {
        Object[][] output = new Object[getCount(query)][5];
        ResultSet rs = executeQuery(query);
        for (int i = 0; rs.next(); i++) {
            output[i][0] = rs.getString("cid");
            output[i][1] = rs.getString("fname");
            output[i][2] = rs.getInt("exam1");
            output[i][3] = rs.getInt("exam2");
            output[i][4] = rs.getInt("final");
        }
        return output;
    }
}
