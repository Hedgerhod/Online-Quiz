
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.Teacher;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hieul
 */
public class DAOTeacher extends DBConnect {

    public void updateTeacher(int accountId, String teacherName, String phone) {
        try {
            String sql = "UPDATE [dbo].[Teacher]\n"
                    + "SET \n"
                    + "    [TeacherName] = ?,\n"
                    + "    [Phone] = ?\n"
                    + "WHERE AccountId = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, teacherName);
            stm.setString(2, phone);
            stm.setInt(3, accountId);

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertTeacher(int accountId, String teacherName, String phone) {
        try {
            String sql = "INSERT INTO [dbo].[Teacher] ([AccountId], [TeacherName], [Phone]) VALUES (?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountId);
            stm.setString(2, teacherName);
            stm.setString(3, phone);

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int insertTeacher(Teacher obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Teacher]([AccountId], [TeacherName], [phone])\n"
                + "VALUES (?, ?, ?)";
        PreparedStatement pre = null;
        try {
            pre = connection.prepareStatement(sql);
            pre.setInt(1, obj.getAccountId());
            pre.setString(2, obj.getTeacherName());
            pre.setString(3, obj.getPhone());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return n;
    }

    public int updateTeacher(Teacher obj) {
        int n = 0;
        PreparedStatement pre = null;
        String sql = "UPDATE [dbo].[Teacher]\n"
                + "   SET [TeacherName] = ?\n"
                + "      ,[phone] = ?\n"
                + " WHERE [AccountId] = ?";
        try {
            pre = connection.prepareStatement(sql);
            pre.setString(1, obj.getTeacherName());
            pre.setString(2, obj.getPhone());
            pre.setInt(3, obj.getAccountId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // Log error or notify user here
                }
            }
        }
        return n;
    }

    public int deleteTeacher(String id) {
        int n = 0;
        PreparedStatement pre = null;
        String sql = "DELETE FROM Teacher WHERE [AccountId] = ?";
        try {
            pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return n;
    }

    public ArrayList<Teacher> getTeacher(String sql) {
        ArrayList<Teacher> list = new ArrayList<Teacher>();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, //hỗ trợ Thread Safe
                    ResultSet.CONCUR_UPDATABLE);
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                int accountId = rs.getInt("AccountId");
                String name = rs.getString("TeacherName");
                String phone = rs.getString("Phone");
                Teacher obj = new Teacher(accountId, name, phone);
                list.add(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return list;
    }

    public ResultSet getTeacherDB(String sql) {
        ResultSet rs = null;
        Statement state;
        try {
            state = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public Teacher getTeacherByAccountId(int accountId) {
        String sql = "SELECT * FROM Teacher WHERE AccountId = ?";
        try {

            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, accountId);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                Teacher obj = new Teacher();
                obj.setAccountId(rs.getInt(1));
                obj.setTeacherName(rs.getString(2));
                obj.setPhone(rs.getString(3));
                return obj;
            }
        } catch (Exception e) {
//             e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        DAOTeacher dao = new DAOTeacher();
        Teacher rs = dao.getTeacherByAccountId(6);
        System.out.println(rs);
    }
}
