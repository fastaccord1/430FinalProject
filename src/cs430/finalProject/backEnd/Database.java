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
     * Finds the type of a certain id
     *
     * @param id The id to be checked against the database
     * @return An int representing the type of employee. -1 if not found
     * @throws SQLException
     */
    public int findType(int id) throws SQLException {
        int output = -1;
        String queryStudent = "SELECT * FROM Student WHERE sid = " + id;
        String queryStaff = "SELECT * FROM Staff WHERE sid = " + id;
        String queryFaculty = "SELECT * FROM Faculty WHERE fid = " + id;
        if (searchDB(queryStudent)) {
            return 1;
        } else if (searchDB(queryStaff)) {
            return 2;
        } else if (searchDB(queryFaculty)) {
            return 3;
        }
        return output;
    }

    /**
     * Searches database for query and returns true if found
     *
     * @param query The query to be run against the database
     * @return Returns true if the query returned a result. False if query didn't return a result.
     */
    private boolean searchDB(String query) {
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                rs.close();
                statement.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert statement != null;
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
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

    protected Object[][] getStaffFacultyResults(String query, String[] columnNames) throws SQLException {
        Object[][] output;
        int count = getCount(query);
        output = new Object[count][3];
        ResultSet rs = executeQuery(query);
        for(int i = 0; rs.next(); i++) {
            output[i][0] = rs.getInt(columnNames[0]);
            output[i][1] = rs.getString(columnNames[1]);
            output[i][2] = rs.getString(columnNames[2]);
        }
        return output;
    }

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

    protected Object[][] getEnrolledResults(String query) throws SQLException {
        Object[][] output = new Object[getCount(query)][5];
        ResultSet rs = executeQuery(query);
        for (int i = 0; rs.next(); i++) {
            output[i][0] = rs.getString("cid");
            output[i][1] = rs.getString("cname");
            output[i][2] = rs.getInt("exam1");
            output[i][3] = rs.getInt("exam2");
            output[i][4] = rs.getInt("final");
        }
        return output;
    }

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
}
