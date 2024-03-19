/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.Subject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phamg
 */
public class DAOSubject extends DBConnect {

    public int insert(Subject obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Subject]"
                + "([SubjectCode], [SubjectName]) "
                + "VALUES(?, ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, obj.getSubjectCode());
            pre.setString(2, obj.getSubjectName());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSubject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
 public boolean checkSubjectCodeExistence(String subjectCode) {
        boolean isExist = false;
        String sql = "SELECT COUNT(*) AS count FROM [dbo].[Subject] WHERE [SubjectCode] = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, subjectCode);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                isExist = count > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSubject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isExist;
    }

    public boolean checkSubjectNameExists(String subjectName) {
        boolean exists = false;
        String sql = "SELECT COUNT(*) AS count FROM [dbo].[Subject] WHERE [SubjectName] = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, subjectName);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                exists = count > 0;
            }
            rs.close();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSubject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exists;
    }

    public int insertSubject(String subjectCode, String subjectName) {
        int numRowsInserted = 0;
        String sql = "INSERT INTO [dbo].[Subject] ([SubjectCode], [SubjectName]) VALUES (?, ?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, subjectCode);
            stm.setString(2, subjectName);

            numRowsInserted = stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSubject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numRowsInserted;
    }

    public int update(Subject obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[Subject]\n"
                + "   SET [SubjectCode] = ? \n"
                + "      ,[SubjectName] = ? \n"
                + " WHERE [SubjectId] = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, obj.getSubjectCode());
            pre.setString(2, obj.getSubjectName());
            pre.setInt(3, obj.getSubjectId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSubject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    

    public int delete(int SubjectId) {
        int n = 0;
        String sql = "DELETE FROM Subject WHERE [SubjectId] = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, SubjectId);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSubject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public ArrayList<Subject> getData(String sql) {
        ArrayList<Subject> subjects = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int SubjectID = resultSet.getInt("SubjectID");
                String SubjectCode = resultSet.getString("SubjectCode");
                String SubjectName = resultSet.getString("SubjectName");
                Subject subject = new Subject(SubjectID, SubjectCode, SubjectName);
                subjects.add(subject);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOSubject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subjects;
    }

    public Subject getSubject(int SubjectID) {
        String sql = "select * from Subject where SubjectId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, SubjectID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                Subject obj = new Subject();
                obj.setSubjectId(rs.getInt(1));
                obj.setSubjectCode(rs.getString(2));
                obj.setSubjectName(rs.getString(3));
                return obj;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public HashMap<Integer, String> SubjectMap() {
        HashMap<Integer, String> subjectList = new HashMap<>();
        String sql = "select SubjectId, SubjectCode from Subject";
        Statement state;
        try {
            state = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("SubjectId");
                String code = rs.getString("SubjectCode");
                subjectList.put(id, code);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return subjectList;
    }

    public static void main(String[] args) {

        DAOSubject dao = new DAOSubject();

      

    }
}
