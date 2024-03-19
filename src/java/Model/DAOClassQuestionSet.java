/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.ClassQuestionSet;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class DAOClassQuestionSet extends DBConnect {

    public int CreateClassQuestionSet(ClassQuestionSet obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[ClassQuestionSet]\n"
                + "           ([ClassId]\n"
                + "           ,[SetId])\n"
                + "     VALUES(?,?)";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, obj.getClassId());
            pre.setInt(2, obj.getSetId());
            n = pre.executeUpdate();
        } catch (Exception e) {
        }
        return n;
    }

    public int CreateClassQuestionSetById(int ClassId, int SetId) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[ClassQuestionSet]\n"
                + "           ([ClassId]\n"
                + "           ,[SetId])\n"
                + "     VALUES(?,?)";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, ClassId);
            pre.setInt(2, SetId);
            n = pre.executeUpdate();
        } catch (Exception e) {
        }
        return n;
    }

    public int UpdateClassQuestionSet(ClassQuestionSet obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[ClassQuestionSet]\n"
                + "   SET [ClassId] = ?\n"
                + "      ,[SetId] = ?\n"
                + " WHERE ClassSetId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, obj.getClassId());
            pre.setInt(2, obj.getSetId());
            pre.setInt(3, obj.getClassSetId());
            n = pre.executeUpdate();
        } catch (Exception e) {
        }
        return n;
    }

    public int DeleteClassQuestionSet(int id) {
        int n = 0;
        String sql = "delete from ClassQuestionSet where ClassSetId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            n = pre.executeUpdate();
        } catch (Exception e) {
        }
        return n;
    }

    public int DeleteQuestionSet(int id, int classId) {
        int n = 0;
        String sql = "delete from ClassQuestionSet where SetId = ? and ClassId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            pre.setInt(2, classId);
            n = pre.executeUpdate();
        } catch (SQLException e) {
        }
        return n;
    }

    public int DeleteQuestionSetInClass(int classId) {
        int n = 0;
        String sql = "delete from ClassQuestionSet where ClassId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, classId);
            n = pre.executeUpdate();
        } catch (SQLException e) {
        }
        return n;
    }

    public ClassQuestionSet ClassQuestionSet(int ClassSetId) {
        int n = 0;
        String sql = "select * from ClassQuestionSet where ClassSetId = ?";
        try {

            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, ClassSetId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                ClassQuestionSet obj = new ClassQuestionSet();
                obj.setClassSetId(rs.getInt(1));
                obj.setClassId(rs.getInt(2));
                obj.setSetId(rs.getInt(3));
                return obj;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Vector<ClassQuestionSet> getAll() {
        Vector<ClassQuestionSet> vector = new Vector<ClassQuestionSet>();
        String sql = "select * from ClassQuestionSet";
        Statement state;
        try {
            state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int ClassSetId = rs.getInt(1);
                int ClassId = rs.getInt(2);
                int SetId = rs.getInt(3);
                ClassQuestionSet obj = new ClassQuestionSet(ClassSetId, ClassId, SetId);
                vector.add(obj);
            }
        } catch (Exception e) {
        }
        return vector;
    }

    public ArrayList<Integer> getSetIdbyClassId(int classId) {
        ArrayList<Integer> setIdList = new ArrayList<>();
        String sql = "SELECT SetId FROM ClassQuestionSet WHERE ClassId = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, classId);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                int setId = rs.getInt("SetId");
                setIdList.add(setId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOClassQuestionSet.class.getName()).log(Level.SEVERE, null, ex);
        }

        return setIdList;
    }

    public static void main(String[] args) {
        DAOClassQuestionSet dao = new DAOClassQuestionSet();
        System.out.println(dao.DeleteQuestionSet(6, 1));
    }
}
