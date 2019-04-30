package uts.isd.model.dao;

import uts.isd.model.Student;
import java.sql.*;

/**
 *
 * @author George
 */
public class DBManager {

    private Statement st;

    public DBManager(Connection conn) throws SQLException {
        st = conn.createStatement();
    }

    //Find student by ID in the database
    public Student findStudent(String ID, String password) throws SQLException {
        String fetch = "select * from ISDUSER.Students where ID = '" + ID + "' and password='" + password + "'";
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) {
            String userID = rs.getString(1);
            String userPass = rs.getString(4);
            if (userID.equals(ID) && userPass.equals(password)) {
                String userEmail = rs.getString(2);
                String userName = rs.getString(3);                
                String userDOB = rs.getString(5);
                String favcol = rs.getString(6);
                return new Student(userID, userEmail, userName, userPass, userDOB, favcol);
            }
        }
        return null;
    }

    //Check if a student exist in the database
    public boolean checkStudent(String ID, String password) throws SQLException {
        String fetch = "select * from ISDUSER.Students where ID = '" + ID + "' and password='" + password + "'";
        ResultSet rs = st.executeQuery(fetch);

        while (rs.next()) {
            String userID = rs.getString(1);
            String userPass = rs.getString(4);
            if (userID.equals(ID) && userPass.equals(password)) {
                return true;
            }
        }
        return false;
    }

    //Add a student-data into the database
    public void addStudent(String ID, String email, String name, String password, String dob, String favcol) throws SQLException {        
        st.executeUpdate("INSERT INTO ISDUSER.Students " + "VALUES ('" + ID + "', '" + email + "', '" + name + "', '" + password + "', '" + dob + "', '" + favcol + "')");
    }

    //update a student details in the database
    public void updateStudent(String ID, String email, String name, String password, String dob, String favcol) throws SQLException {
        st.executeUpdate("UPDATE ISDUSER.Students SET EMAIL='" + email + "',NAME='" + name + "',PASSWORD='" + password + "',dob='" + dob + "',FAVORITECOLOR='" + favcol + "' WHERE ID='" + ID + "'");
    }
    
    //delete a student from the database
    public void deleteStudent(String ID) throws SQLException{
        st.executeUpdate("DELETE FROM ISDUSER.Students WHERE ID='" + ID + "'");
    }
}
