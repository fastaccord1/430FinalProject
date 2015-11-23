package cs430.finalProject.backEnd;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Child class of Database to handle student-related database actions
 * Created by kreuter on 11/13/15.
 *
 * @author Kevin Reuter
 */
public class StudentDatabase extends Database {
    private final String[] LEVELS = {"<Select>", "Freshman", "Sophomore", "Junior", "Senior", "Masters", "PhD"};

    public StudentDatabase(Connection conn) {
        super();
        super.conn = conn;
    }

    public String[] getLEVELS() {
        return LEVELS;
    }

    /**
     * Searches the database for a specific student
     *
     * @param id    int for the wanted student's id
     * @param name  String for the wanted student's name
     * @param major String for the wanted student's major
     * @param level String for the wanted student's level
     * @param age   String for the wanted student's age
     * @return Object[][] Two-dimensional array of results.
     */
    public Object[][] searchStudent(int id, String name, String major, String level, int age) {
        String query = "SELECT * FROM Student WHERE";

        ArrayList<String> conditions = new ArrayList<>();

        if (id != -1) {
            conditions.add(" sid = " + id);
        }
        if (name != null) {
            conditions.add(" sname LIKE '%" + name + "%'");
        }
        if (major != null) {
            conditions.add(" major LIKE '%" + major + "%'");
        }
        if (level != null) {
            conditions.add(" s_level = '" + level + "'");
        }
        if (age != -1) {
            conditions.add(" age = " + age);
        }

        query = finishQuery(query, conditions);
        try {
            return getStudentResults(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method returns entire Student table
     *
     * @return Two dimensional array of results
     */
    public Object[][] searchStudent() {
        String query = "SELECT * FROM Student";
        try {
            return getStudentResults(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method to insert new student into the database
     *
     * @param sid     SID for the new student
     * @param sName   Name for the new student
     * @param major   Major for the new student
     * @param s_level Level for the new student
     * @param age     Age for the new student
     */
    public void insertStudent(int sid, String sName, String major, String s_level, int age) {
        String statement = "INSERT INTO Student VALUES(";
        statement += sid + ", ";
        statement += "'" + sName + "', ";
        statement += "'" + major + "', ";
        statement += "'" + s_level + "', ";
        statement += age + ")";

        try {
            executeInsertUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to udpate an existing student entry in the database.
     *
     * @param oldSid Old SID of the student before the change
     * @param newSid New SID of the student after the change
     * @param sname New name for the student
     * @param major New major for the student
     * @param s_level New level for the student
     * @param age New age for the student
     */
    public void updateStudent(int oldSid, int newSid, String sname, String major, String s_level, int age) {
        String statement = "UPDATE Student SET ";
        statement += "sid = " + newSid + ", ";
        statement += "sname = '" + sname + "', ";
        statement += "major = '" + major + "', ";
        statement += "s_level = '" + s_level + "', ";
        statement += "age = " + age;
        statement += " WHERE sid = " + oldSid;
        try {
            executeInsertUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks to see if the ID belongs to a student
     * @param id The ID to be checked in the database
     * @return True if the ID belongs to a student. False if it doesn't.
     */
    public boolean isStudent(int id) {
        String query = "SELECT * FROM Student WHERE sid = " + id;
        try {
            return isFound(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String[] getMajors () {
        String query = "SELECT major FROM Student";
        try {
            int count = getCount(query);
            ResultSet rs = executeQuery(query);
            String[] output = new String[count + 1];
            output[0] = "Any";
            for(int i = 1; rs.next(); i++){
                output[i] = rs.getString("major");
            }
            return output;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteStudent(int id) {
        String statement = "DELETE FROM Student WHERE sid = " + id;
        try {
            executeInsertUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getStudentId(String name) {
        String query = "SELECT sid FROM Student WHERE sname = '" + name + "'";
        try {
            ResultSet rs = executeQuery(query);
            if (rs.next()) {
                return rs.getInt("sid");
            }
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String[] getStudents() {
        String query = "SELECT sname FROM Student";
        try {
            String[] output = new String[getCount(query) + 1];
            ResultSet rs = executeQuery(query);
            output[0] = "<Select>";
            for (int i = 1; rs.next(); i++) {
                output[i] = rs.getString("sname");
            }
            return output;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object[][] searchEnrolled(int fid, int sid, String sName, String major, String level, int age) {
        String query = "SELECT * FROM enrolledStudent WHERE fid = " + fid + " AND";
        ArrayList<String> conditions = new ArrayList<>();
        if (sid != -1) {
            conditions.add(" sid = " + sid);
        }
        if (sName != null) {
            conditions.add(" sname LIKE '%" + sName + "%'");
        }
        if (major != null) {
            conditions.add(" major = '" + major + "'");
        }
        if (level != null) {
            conditions.add(" s_level = '" + level + "'");
        }
        if (age != -1) {
            conditions.add(" age = " + age);
        }
        query = finishQuery(query, conditions);
        try {
            return getStudentResults(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Object[][] searchEnrolled(int fid) {
        String query = "SELECT * FROM enrolledStudent WHERE fid = " + fid;
        try {
            return getStudentResults(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object[][] getMyCourses(int sid) {
        String query = "SELECT * FROM enrolledStudent WHERE sid = " + sid;
        try {
            return getFacultyEnrolledResults(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void enroll(String cid, int sid) {
        String countQuery = "SELECT COUNT(sid) FROM Enrolled WHERE cid = '" + cid + "'";
        int count = 0, limit = 0;
        String limitQuery = "SELECT limit FROM Courses WHERE cid = '" + cid + "'";
        try {
            ResultSet countRs = executeQuery(countQuery);
            if (countRs.next()) {
                count = countRs.getInt("COUNT(sid)");
            }
            ResultSet limitRs = executeQuery(limitQuery);
            if (limitRs.next()) {
                limit = limitRs.getInt("limit");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (count < limit) {
            String statement = "INSERT INTO Enrolled (sid, cid) VALUES(" + sid + ", '" + cid + "')";
            try {
                executeInsertUpdate(statement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "You cannot register for this class because it is at the limit");
        }

    }
}
