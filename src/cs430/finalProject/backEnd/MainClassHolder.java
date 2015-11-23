package cs430.finalProject.backEnd;

import cs430.finalProject.frontEnd.SelectRole;
import oracle.jdbc.driver.OracleDriver;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.System.err;


/**
 * 430FinalProject
 * Created by kreuter on 10/20/15.
 *
 * @author Kevin Reuter
 */
public class MainClassHolder {


    // Database object to be used for connection
    private static StudentDatabase studentDatabase;
    private static FacultyDatabase facultyDatabase;
    private static StaffDatabase staffDatabase;
    private static GeneralDatabase generalDatabase;
    protected final String URL = "jdbc:oracle:thin:@dbserv.cs.siu.edu:1521:cs";
    // String constant for the path to the config file
    private final String PATH = "/cs430/finalProject/config/database.conf";
    private Connection conn;

    /**
     * Default constructor to initialize variables
     */
    public MainClassHolder() {
        String[] userPass = null;
        try {
            userPass = getDatabaseInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (userPass != null) {
            String username = userPass[0];
            String password = userPass[1];
            try {
                DriverManager.registerDriver(new OracleDriver());
                this.conn = DriverManager.getConnection(URL, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            studentDatabase = new StudentDatabase(conn);
            facultyDatabase = new FacultyDatabase(conn);
            staffDatabase = new StaffDatabase(conn);
            generalDatabase = new GeneralDatabase(conn);
        } else {
            err.println("Something went wrong!");
            System.exit(1);
        }
    }

    /**
     * Getter for the StudentDatabase object
     *
     * @return Returns the Student Database object
     */
    public static StudentDatabase getStudentDatabase() {
        return studentDatabase;
    }

    /**
     * Getter for faculty database
     *
     * @return FacultyDatabase object to be used
     */
    public static FacultyDatabase getFacultyDatabase() {
        return facultyDatabase;
    }

    /**
     * Getter for staff database
     *
     * @return StaffDatabase object to be used
     */
    public static StaffDatabase getStaffDatabase() {
        return staffDatabase;
    }

    /**
     * Getter for the general database
     *
     * @return GeneralDatabase object to be used
     */
    public static GeneralDatabase getGeneralDatabase() {
        return generalDatabase;
    }

    /**
     * This method is called when a fresh install isn't required.
     */
    public void noInstall() {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        java.awt.EventQueue.invokeLater(() -> new SelectRole().setVisible(true));
    }

    /**
     * Closes the connection on the database
     */
    public void close() {
        studentDatabase.closeConnection();
        facultyDatabase.closeConnection();
        staffDatabase.closeConnection();
        generalDatabase.closeConnection();
    }

    /**
     * This method gets the username and password from the config file
     *
     * @return Array of strings for the username and password
     * @throws IOException
     */
    public String[] getDatabaseInfo() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(PATH);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line, username = null, password = null;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains("username:")) {
                username = line.substring(10);
            }
            if (line.contains("password:")) {
                password = line.substring(10);
            }
        }
        if (username == null || password == null) {
            err.println((username == null) ? "Username" : "Password" + " not found. Exiting program.");
            System.exit(1);
        }
        return new String[]{username, password};
    }

}
