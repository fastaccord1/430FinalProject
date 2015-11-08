package cs430.finalProject.backEnd;
/**
 * 430FinalProject
 * Created by kreuter on 10/18/15.
 * @author Kevin Reuter
 */
import java.sql.*;
import java.util.ArrayList;

import oracle.jdbc.driver.*;


public class Database {
    // Stores the driver name to be used
    private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    // Stores the url to the database
    private final String URL = "jdbc:oracle:thin:@dbserv.cs.siu.edu:1521:cs";
    // Connection object for the database
    private Connection conn;

    /**
     * Constructor that initiates database connection
     * @param username String username for database connection
     * @param password String password for database connection
     */
    public Database(String username, String password){
        conn = null;
        try {
            DriverManager.registerDriver(new OracleDriver());
            conn = DriverManager.getConnection(URL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /**
     * Method that executes query against the database
     * @param preparedStatementString String query to be run
     * @return ResultSet of the results from query
     */
    public ResultSet executeQuery(String preparedStatementString){
        ResultSet rs = null;
        try {
            PreparedStatement prep = conn.prepareStatement(preparedStatementString);
            rs = prep.executeQuery();
            prep.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * This method executes insert or update operations
     * @param preparedStatementString String query to be run
     */
    public void executeInsertUpdate(String preparedStatementString){
        try {
            PreparedStatement prep = conn.prepareStatement(preparedStatementString);
            System.out.println("Created prepared statement");
            prep.execute();
            prep.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method closes the connection to the database
     */
    public void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Finds the type of a certain id
     * @param id The id to be checked against the database
     * @return An int representing the type of employee. -1 if not found
     * @throws SQLException
     */
    public int findType(int id) throws SQLException {
        int output = -1;
        String queryStudent = "SELECT * FROM Student WHERE sid = " + id;
        String queryStaff = "SELECT * FROM Staff WHERE sid = " + id;
        String queryFaculty = "SELECT * FROM Faculty WHERE fid = " + id;
        if(searchDB(queryStudent)){
            return 1;
        } else if(searchDB(queryStaff)){
            return 2;
        } else if(searchDB(queryFaculty)){
            return 3;
        }
        return output;
    }

    /**
     * Searches database for query and returns true if found
     * @param query The query to be run against the database
     * @return Returns true if the query returned a result. False if query didn't return a result.
     */
    private boolean searchDB(String query){
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            //System.out.println(rs.getStatement().toString());
            if(rs.next()){
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
     * Searches the database for a specific student
     * @param id int for the wanted student's id
     * @param name String for the wanted student's name
     * @param major String for the wanted student's major
     * @param level String for the wanted student's level
     * @param age String for the wanted student's age
     * @return Object[][] double array to be used in the table
     */
    public Object[][] searchStudent(int id, String name, String major, String level, int age) throws SQLException {
        String query = "SELECT * FROM Student WHERE";
        if(id != -1){
            query += " sid = " + id;
        }
        if(name != null){
            query += " sname = " + name;
        }
        if(major != null){
            query += " major = " + major;
        }
        if(level != null){
            query += " s_level = " + level;
        }
        if(age != -1){
            query += " age = " + age;
        }
        int length = getCount(query);
        Object[][] output = new Object[length][5];
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        for(int i = 0; rs.next(); i++){
            output[i][0] = rs.getInt("sid");
            output[i][1] = rs.getString("sname");
            output[i][2] = rs.getString("major");
            output[i][3] = rs.getString("s_level");
            output[i][4] = rs.getInt("age");
        }
        return output;
    }

    public Object[][] searchStudent() throws SQLException {
        String query = "SELECT * FROM Student";
        int length = getCount(query);
        Object[][] output = new Object[length][5];
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        int i = 0;
        while(rs.next()){
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

    /**
     * Method to get the count for a specific query
     * @param startingQuery PreparedStatement to be used for the count
     * @return int count for results
     * @throws SQLException
     */
    public int getCount(String startingQuery) throws SQLException {
        int out;
        String countQuery = "SELECT COUNT(*) FROM (";
        countQuery += startingQuery;
        countQuery += ")";

        System.out.println(countQuery);
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(countQuery);
        if(rs.next()){
            out = rs.getInt(1);
        } else{
            return -1;
        }
        rs.close();
        statement.close();
        return out;
    }

    /**
     * Method to replace String objects in a PreparedStatement
     * @param preparedStatement PreparedStatement to be used
     * @param index int index for the position to be replaced
     * @param input String input to use in replacement
     * @throws SQLException
     */
    public void replaceString(PreparedStatement preparedStatement, int index, String input) throws SQLException {

        if(input != null){
            preparedStatement.setString(index, input);
        } else{
            preparedStatement.setString(index, "%");
        }
    }

    /**
     * Method to replace integers in PreparedStatement
     * @param preparedStatement PreparedStatement to be used
     * @param index int index for the position to be replaced
     * @param input int input to be used in completed statement
     * @throws SQLException
     */
    public void replaceInt(PreparedStatement preparedStatement, int index, int input) throws SQLException {
        if(input != -1){
            preparedStatement.setInt(index, input);
        } else{
            preparedStatement.setString(index, "%");
        }
    }

    /**
     * Method to replace floats in PreparedStatement
     * @param preparedStatement PreparedStatement to be used
     * @param index int index for the position to be replaced
     * @param input float input to be used in completed statement
     * @throws SQLException
     */
    public void replaceFloat(PreparedStatement preparedStatement, int index, float input) throws SQLException {
        if(input != -1){
            preparedStatement.setFloat(index, input);
        } else{
            preparedStatement.setString(index, "%");
        }
    }

}
