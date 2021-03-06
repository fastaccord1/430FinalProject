package cs430.finalProject.backEnd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class handles database operations not related to Faculty, Staff or Students
 * Created by kreuter on 11/13/15.
 *
 * @author Kevin Reuter
 */
public class GeneralDatabase extends Database {

    /**
     * Constructor for GeneralDatabase class
     *
     * @param connection Connection object from database initialization
     */
    public GeneralDatabase(Connection connection) {
        super();
        super.conn = connection;
    }

    /**
     * Gets all entries from Department table
     *
     * @return Two-dimensional array with all results from Department table
     */
    public Object[][] searchDepartment() {
        String query = "SELECT * FROM Department";
        Object[][] output;
        try {
            int count = getCount(query);
            output = new Object[count][2];
            ResultSet rs = executeQuery(query);
            for (int i = 0; rs.next(); i++) {
                output[i][0] = rs.getInt("did");
                output[i][1] = rs.getString("dname");
            }
            return output;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Searches Department table for specific department
     *
     * @param did   ID for the department to be found
     * @param dName Name for the department to be found
     * @return Two-dimensional array of departments found
     */
    public Object[][] searchDepartment(int did, String dName) {
        String query = "SELECT * FROM Department WHERE";
        ArrayList<String> conditions = new ArrayList<>();
        if (did != -1) {
            conditions.add(" did = " + did);
        }
        if (dName != null) {
            conditions.add(" dname LIKE '%" + dName + "%'");
        }

        try {
            query = finishQuery(query, conditions);
            int count = getCount(query);
            Object[][] output = new Object[count][2];
            ResultSet rs = executeQuery(query);
            for (int i = 0; rs.next(); i++) {
                output[i][0] = rs.getInt("did");
                output[i][1] = rs.getString("dname");
            }
            return output;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets all department names from the database
     *
     * @return String array of database names
     */
    public String[] getDepartmentNames() {
        String query = "SELECT dname FROM Department";
        try {
            String[] output = new String[getCount(query) + 1];
            ResultSet rs = executeQuery(query);
            output[0] = "<Select>";
            for (int i = 1; rs.next(); i++) {
                output[i] = rs.getString("dname");
            }
            return output;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the department's ID from its name
     *
     * @param deptName The name of the department to be found
     * @return The department's ID
     */
    public int getDepartmentId(String deptName) {
        String query = "SELECT did FROM Department WHERE dname = '" + deptName + "'";
        ResultSet rs;
        int output = -1;
        try {
            rs = executeQuery(query);
            if (rs.next()) {
                output = rs.getInt("did");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }

    /**
     * Inserts a department into the database
     *
     * @param id   The ID for the new department
     * @param name The name for the new department
     */
    public void insertDepartment(int id, String name) {
        String statement = "INSERT INTO Department VALUES(";
        statement += id + ", ";
        statement += "'" + name + "')";
        System.out.println(statement);

        try {
            executeInsertUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates a department in the database
     *
     * @param oldId The old ID for the department
     * @param id    The new ID for the department
     * @param name  The new name for the department
     */
    public void updateDepartment(int oldId, int id, String name) {
        String statement = "UPDATE Department SET ";
        statement += "did = " + id + ", ";
        statement += "dname = '" + name + "' ";
        statement += "WHERE did = " + oldId;

        try {
            executeInsertUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all courses from the database
     *
     * @return Two-dimensional array of courses
     */
    public Object[][] searchCourse() {
        String query = "SELECT * FROM CourseView";
        try {
            ResultSet rs = executeQuery(query);
            return getCourseResults(rs, getCount(query));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Searches database for specific courses
     *
     * @param id    The ID to be found
     * @param name  The course name to be found
     * @param meets When the course meets
     * @param room  The room where the course meets
     * @param fname The faculty name for the course
     * @param limit The limit for the course
     * @return Two-dimensional array of courses
     */
    public Object[][] searchCourse(String id, String name, String meets, String room, String fname, int limit) {
        String query = "SELECT * FROM courseView WHERE";
        ArrayList<String> conditions = new ArrayList<>();
        if (id != null) {
            conditions.add(" cid LIKE '%" + id + "%'");
        }
        if (name != null) {
            conditions.add(" cname LIKE '%" + name + "%'");
        }
        if (meets != null) {
            conditions.add(" meets_at LIKE '%" + meets + "%'");
        }
        if (room != null) {
            conditions.add(" room LIKE '%" + room + "%'");
        }
        if (fname != null) {
            conditions.add(" fname = '" + fname + "'");
        }
        if (limit != -1) {
            conditions.add(" limit = " + limit);
        }
        query = finishQuery(query, conditions);
        try {
            ResultSet rs = executeQuery(query);
            return getCourseResults(rs, getCount(query));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Converts results of course search to array for table
     *
     * @param rs    The results to be converted
     * @param count The count of results
     * @return Two-dimensional array for the table
     * @throws SQLException
     */
    public Object[][] getCourseResults(ResultSet rs, int count) throws SQLException {
        Object[][] output = new Object[count][6];
        for (int i = 0; rs.next(); i++) {
            output[i][0] = rs.getString("cid");
            output[i][1] = rs.getString("cname");
            output[i][2] = rs.getString("meets_at");
            output[i][3] = rs.getString("room");
            output[i][4] = rs.getString("fname");
            output[i][5] = rs.getInt("limit");
        }
        return output;
    }

    /**
     * Inserts a course into the database
     *
     * @param id    The ID for the course
     * @param name  The name for the course
     * @param meets When the course meets
     * @param room  The room where the course meets
     * @param fId   The faculty ID for the course
     * @param limit The limit for the course
     */
    public void insertCourse(String id, String name, String meets, String room, int fId, int limit) {
        String statement = "INSERT INTO Courses VALUES(";
        statement += "'" + id + "', ";
        statement += "'" + name + "', ";
        statement += "'" + meets + "', ";
        statement += "'" + room + "', ";
        statement += fId + ", ";
        statement += limit + ")";

        try {
            executeInsertUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates a course in the database
     *
     * @param oldCid The old ID for the course
     * @param cid    The new ID for the course
     * @param name   The new name for the course
     * @param meets  The new time at which the course meets
     * @param room   The new room where the course meets
     * @param fid    The new faculty ID for the course
     * @param limit  The new limit for the course
     */
    public void updateCourse(String oldCid, String cid, String name, String meets, String room, int fid, int limit) {
        String statement = "UPDATE Courses SET";
        statement += " cid = '" + cid + "', ";
        statement += "cname = '" + name + "', ";
        statement += "meets_at = '" + meets + "', ";
        statement += "room = '" + room + "', ";
        statement += "fid = " + fid + ", ";
        statement += "limit = " + limit + " WHERE cid = '" + oldCid + "'";
        try {
            executeInsertUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all course IDs from the database
     *
     * @return String array of all course IDs
     */
    public String[] getCourses() {
        String query = "SELECT cid FROM Courses";
        try {
            String[] output = new String[getCount(query) + 1];
            ResultSet rs = executeQuery(query);
            output[0] = "<Select>";
            for (int i = 1; rs.next(); i++) {
                output[i] = rs.getString("cid");
            }
            return output;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets all results from the Enrolled table
     *
     * @return Two-dimensional array of enrolled students
     */
    public Object[][] searchEnrolled() {
        String query = "SELECT * FROM enrolledStudent";
        try {
            return getStudentEnrolledResults(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Searches database for specific enrolled students
     *
     * @param cid       The ID to be found
     * @param sName     The student to be found
     * @param exam1     The exam 1 score to be found
     * @param exam2     The exam 2 score to be found
     * @param finalExam The final exam score to be found
     * @return A two-dimensional array for the table
     */
    public Object[][] searchEnrolled(String cid, String sName, int exam1, int exam2, int finalExam) {
        String query = "SELECT * FROM enrolledStudent WHERE";
        ArrayList<String> conditions = new ArrayList<>();

        if (cid != null) {
            conditions.add(" cid = '" + cid + "'");
        }
        if (sName != null) {
            conditions.add(" sname = '" + sName + "'");
        }
        if (exam1 != -1) {
            conditions.add(" exam1 = " + exam1);
        }
        if (exam2 != -1) {
            conditions.add(" exam2 = " + exam2);
        }
        if (finalExam != -1) {
            conditions.add(" final = " + finalExam);
        }
        query = finishQuery(query, conditions);

        try {
            return getStudentEnrolledResults(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Searches for all entries in Enrolled for courses taught by a specific faculty
     *
     * @param fid The faculty's ID
     * @return Two-dimensional array for the table
     */
    public Object[][] searchEnrolled(int fid) {
        String query = "SELECT * FROM enrolledStudent WHERE fid = " + fid;
        try {
            return getStudentEnrolledResults(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Searches for specific entry in Enrolled for courses taught by a specific faculty
     *
     * @param fid       The faculty's ID
     * @param sName     Student's name to be searched
     * @param exam1     Exam 1 score to be found
     * @param exam2     Exam 2 score to be found
     * @param finalExam Final exam score to be found
     * @return Two-dimensional array for the table
     */
    public Object[][] searchEnrolled(int fid, String sName, int exam1, int exam2, int finalExam) {
        String query = "SELECT * FROM enrolledStudent WHERE fid = " + fid + " AND";
        ArrayList<String> conditions = new ArrayList<>();
        if (sName != null) {
            conditions.add(" sname = '" + sName + "'");

        }
        if (exam1 != -1) {
            conditions.add(" exam1 = " + exam1);
        }
        if (exam2 != -1) {
            conditions.add(" exam2 = " + exam2);
        }
        if (finalExam != -1) {
            conditions.add(" final = " + finalExam);
        }
        query = finishQuery(query, conditions);
        try {
            return getStudentEnrolledResults(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Inserts new entry into Enrolled table
     *
     * @param cid       The course ID
     * @param sid       The student's ID
     * @param exam1     The exam 1 score
     * @param exam2     The exam 2 score
     * @param finalExam The final exam score
     */
    public void insertEnrolled(String cid, int sid, int exam1, int exam2, int finalExam) {
        String statement = "INSERT INTO Enrolled VALUES(";
        statement += sid + ", ";
        statement += "'" + cid + "', ";
        statement += exam1 + ", ";
        statement += exam2 + ", ";
        statement += finalExam + ")";
        System.out.println(statement);
        try {
            executeInsertUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an entry in the Enrolled table
     *
     * @param oldCid    The old course ID
     * @param oldSid    The old student ID
     * @param cid       The new course ID
     * @param sid       The new student ID
     * @param exam1     The new exam 1 score
     * @param exam2     The new exam 2 score
     * @param finalExam The new final exam score
     */
    public void updateEnrolled(String oldCid, int oldSid, String cid, int sid, int exam1, int exam2, int finalExam) {
        String statement = "UPDATE Enrolled SET";
        statement += " cid = '" + cid + "', ";
        statement += "sid = " + sid + ", ";
        statement += "exam1 = " + exam1 + ", ";
        statement += "exam2 = " + exam2 + ", ";
        statement += "final = " + finalExam;
        statement += " WHERE cid = '" + oldCid + "' AND sid = " + oldSid;

        try {
            executeInsertUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a course from the database
     *
     * @param id The course ID to be deleted
     */
    public void deleteCourse(String id) {
        String statement = "DELETE FROM Courses WHERE cid = '" + id + "'";
        try {
            executeInsertUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a department from the database
     *
     * @param id The department ID to be deleted
     */
    public void deleteDepartment(int id) {
        String statement = "DELETE FROM Department WHERE did = " + id;
        try {
            executeInsertUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes an enrolled student from the database
     *
     * @param cid The course ID
     * @param sid The student ID
     */
    public void deleteEnrolled(String cid, int sid) {
        String statement = "DELETE FROM Enrolled WHERE sid = " + sid + " AND cid = '" + cid + "'";
        try {
            executeInsertUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets array of course info for specific course
     *
     * @param cid The ID to be found
     * @return Object array of course info
     */
    public Object[] getCourseInfo(String cid) {
        String query = "SELECT * FROM courseView WHERE cid = '" + cid + "'";
        Object[] data = new Object[5];
        try {
            ResultSet rs = executeQuery(query);
            if (rs.next()) {
                data[0] = rs.getString("cname");
                data[1] = rs.getString("meets_at");
                data[2] = rs.getString("room");
                data[3] = rs.getString("fname");
                data[4] = rs.getInt("limit");
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
