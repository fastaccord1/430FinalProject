package backEnd;
/**
 * Created by kreuter on 10/18/15.
 */
import java.sql.*;



public class Database {
    private static String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private String url;
    private String username;
    private String password;
    private Statement statement;
    private Connection conn;

    public Database(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;
        statement = null;
        conn = null;


        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connect();

    }

    public void connect(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.conn = conn;
    }

    protected void createStatement(){
        try {
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected ResultSet executeQuery(String query){
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;

    }



    public void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void statementClose(){
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
