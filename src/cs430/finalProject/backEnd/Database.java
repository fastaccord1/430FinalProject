package cs430.finalProject.backEnd;
/**
 * 430FinalProject
 * Created by kreuter on 10/18/15.
 *
 * @author Kevin Reuter
 */

import oracle.jdbc.driver.OracleDriver;

import java.sql.*;
import java.util.ArrayList;


public class Database {
    // Stores the url to the database
    protected final String URL = "jdbc:oracle:thin:@dbserv.cs.siu.edu:1521:cs";
    // Connection object for the database
    protected Connection conn;

    /**
     * Constructor that initiates database connection
     *
     * @param username String username for database connection
     * @param password String password for database connection
     */
    public Database(String username, String password) {
        conn = null;
        try {
            DriverManager.registerDriver(new OracleDriver());
            conn = DriverManager.getConnection(URL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

        System.out.println(countQuery);
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
     * Executes insert against either Faculty or Staff
     *
     * @param table  Table to run the insert against
     * @param id     ID for the member to be inserted
     * @param name   Name for the member to be inserted
     * @param deptId Department ID for the member to be inserted
     * @throws SQLException
     */
    protected void executeFacultyStaffInsert(String table, int id, String name, int deptId) throws SQLException {
        String query = "INSERT INTO ? VALUES(?, ?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, table);
        preparedStatement.setInt(2, id);
        preparedStatement.setString(3, name);
        preparedStatement.setInt(4, deptId);

        preparedStatement.execute();
    }
}
