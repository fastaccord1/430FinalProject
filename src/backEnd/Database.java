package backEnd;
/**
 * Created by kreuter on 10/18/15.
 */
import javax.xml.transform.Result;
import java.sql.*;



public class Database {
    private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private final String URL = "jdbc:oracle:thin:@dbserv.cs.siu.edu:1521:cs";
    private String username;
    private String password;
    private Connection conn;

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

    public void connect(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.conn = conn;
    }



    public ResultSet executeQuery(String preparedStatementString, String[] types, String [] inputStrings,
                                  int[] inputInts, float[] inputFloats){
        ResultSet rs = null;

        try {
            PreparedStatement prep = conn.prepareStatement(preparedStatementString);
            for(int i=0; i <= types.length; i++){
                if(types[i].equals("String")){
                    //prep.setString(i, s);
                }
            }
            rs = prep.executeQuery();
            prep.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return rs;

    }

    /**
     * This method executes insert
     * @param preparedStatementString
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

    public void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
