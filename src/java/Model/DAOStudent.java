/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.Student;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class DAOStudent extends DBConnect {

    public void insertStudent(int accountId, String studentName, String phone, String dob) {
        try {
            String sql = "INSERT INTO [dbo].[Student] ([AccountId], [StudentName], [Phone], [DoB]) VALUES (?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountId);
            stm.setString(2, studentName);
            stm.setString(3, phone);
            stm.setString(4, dob);

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateStudent(int accountId, String studentName, String phone, String dob) {
        try {
            String sql = "UPDATE [dbo].[Student]\n"
                    + "SET \n"
                    + "    [StudentName] = ?,\n"
                    + "    [Phone] = ?,\n"
                    + "    [DoB] = ?\n"
                    + "WHERE AccountId = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, studentName);
            stm.setString(2, phone);
            stm.setString(3, dob);
            stm.setInt(4, accountId);

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int CreateStudent(Student obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Student]\n"
                + "           ([AccountId]\n"
                + "           ,[StudentName]\n"
                + "           ,[Phone]\n"
                + "           ,[DoB])\n"
                + "     VALUES(?,?,?,?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, obj.getAccountId());
            pre.setString(2, obj.getStudentName());
            pre.setString(3, obj.getPhone());
            pre.setString(4, obj.getDoB());
            n = pre.executeUpdate();
        } catch (Exception e) {
        }

        return n;
    }

    public int UpdateStudent(Student obj) {
        int n = 0;

        String sql = "UPDATE [dbo].[Student]\n"
                + "   SET [StudentName] = ?\n"
                + "      ,[Phone] = ?\n"
                + "      ,[DoB] = ?\n"
                + " WHERE [AccountId] = ? ";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, obj.getStudentName());
            pre.setString(2, obj.getPhone());
            pre.setString(3, obj.getDoB());
            pre.setInt(4, obj.getAccountId());
            n = pre.executeUpdate();
        } catch (Exception e) {
        }
        return n;
    }

    public int DeleteStudent(int id) {
        int n = 0;
        String sql = "delete from Student where AccountId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            n = pre.executeUpdate();
        } catch (Exception e) {
        }
        return n;
    }

    public Student getStudent(int AccountId) {
        String sql = "select * from Student where AccountId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, AccountId);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                Student obj = new Student();
                obj.setAccountId(rs.getInt(1));
                obj.setStudentName(rs.getString(2));
                obj.setPhone(rs.getString(3));
                obj.setDoB(rs.getString(4));
                return obj;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Vector<Student> getAll() {
        Vector<Student> vector = new Vector<Student>();
        String sql = "select * from Student";
        Statement state;
        try {
            state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int AccountId = rs.getInt(1);
                String StudentName = rs.getString(2);
                String Phone = rs.getString(3);
                String DoB = rs.getString(4);
                Student obj = new Student(AccountId, StudentName, Phone, DoB);
                vector.add(obj);
            }
        } catch (Exception e) {
        }

        return vector;
    }
}
