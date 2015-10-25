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
     * This method gets a list of tables in the database
     * @return ArrayList of tables
     */
    public ArrayList<String> getTables(){
        ArrayList<String> tables = new ArrayList<String>();
        int count = 1;
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rs = dbmd.getTables("", "cs", "*", null);
            while(rs.next()){
                System.out.println(rs.getString(1));
                //tables.add(rs.getString(count));
                //count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;
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
    public Object[][] searchStudent(int id, String name, String major, String level, int age){
        String query = "SELECT * FROM Student WHERE sid=? AND sname=? AND major=? AND level=?" +
                "AND age=?";
        Object[][] output = null;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            replaceInt(preparedStatement, 1, id);
            replaceString(preparedStatement, 2, name);
            replaceString(preparedStatement, 3, major);
            replaceString(preparedStatement, 4, level);
            replaceInt(preparedStatement, 5, age);

            ResultSet rs = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    }

    /**
     * Method to get the count for a specific query
     * @param tempStatement PreparedStatement to be used for the count
     * @return int count for results
     * @throws SQLException
     */
    public int getCount(PreparedStatement tempStatement) throws SQLException {
        int out = 0;
        String countQuery = "SELECT COUNT(*) FROM (";
        countQuery += tempStatement.toString();
        countQuery += ")";

        System.out.println(countQuery);
        //PreparedStatement preparedStatement = conn.prepareStatement(countQuery);

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
