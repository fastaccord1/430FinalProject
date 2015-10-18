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
    Connection conn;

    public Database(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;


        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        conn = connect();

    }

    public Connection connect(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
