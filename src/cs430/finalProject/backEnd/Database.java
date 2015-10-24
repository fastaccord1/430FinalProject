package cs430.finalProject.backEnd;
/**
 * 430FinalProject
 * Created by kreuter on 10/18/15.
 * @author Kevin Reuter
 */
import java.sql.*;
import java.util.ArrayList;


public class Database {
    // Stores the driver name to be used
    private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    // Stores the url to the database
    private final String URL = "jdbc:oracle:thin:@dbserv.cs.siu.edu:1521:cs";
    // Username for the database
    private String username;
    // Password for the database
    private String password;
    // Connection object for the database
    private Connection conn;

    /**
     * Constructor that initiates database connection
     * @param username String username for database connection
     * @param password String password for database connection
     */
    public Database(String username, String password){
        this.username = username;
        this.password = password;
        conn = null;
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connect();
    }

    /**
     * Method that initiates the connection to the database
     */
    public void connect(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.conn = conn;
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

    public Object[][] searchStudent(int id, String name, String major, String level, int age){
        String query = "SELECT * FROM Student WHERE sid=? AND sname=? AND major=? AND level=?" +
                "AND age=?";
        Object[][] output = null;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            if(id != -1){
                preparedStatement.setInt(1, id);
            } else{
                preparedStatement.setString(1, "%");
            }

            if(name != null){
                preparedStatement.setString(2, name);
            } else{
                preparedStatement.setString(2, "%");
            }

            if(major != null){
                preparedStatement.setString(3, major);
            } else{
                preparedStatement.setString(3, "%");
            }

            if(level != null){
                preparedStatement.setString(4, level);
            } else{
                preparedStatement.setString(4, "%");
            }

            if(age != -1){
                preparedStatement.setInt(5, age);
            } else{
                preparedStatement.setString(5, "%");
            }

            ResultSet rs = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    }
}
