/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.TakeClass;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class DAOTakeClass extends DBConnect {

    public int CreateTakeClass(TakeClass obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[TakeClass]\n"
                + "           ([StudentAccountId]\n"
                + "           ,[ClassId])\n"
                + "     VALUES(?,?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, obj.getStudentAccountId());
            pre.setInt(2, obj.getClassId());
            n = pre.executeUpdate();
        } catch (SQLException e) {
        }
        return n;
    }

    public int UpdateTakeClass(TakeClass obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[TakeClass]\n"
                + "   SET [StudentAccountId] = ?\n"
                + "      ,[ClassId] = ?\n"
                + " WHERE TakeClassId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, obj.getStudentAccountId());
            pre.setInt(2, obj.getClassId());
            pre.setInt(3, obj.getTakeClassId());
            n = pre.executeUpdate();
        } catch (SQLException e) {
        }
        return n;
    }

    public int DeleteTakeClass(int id) {
        int n = 0;
        String sql = "delete from TakeClass where TakeClassId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            n = pre.executeUpdate();
        } catch (SQLException e) {
        }
        return n;
    }
        public int DeleteTakeClassByClassId(int id) {
        int n = 0;
        String sql = "delete from TakeClass where ClassId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            n = pre.executeUpdate();
        } catch (SQLException e) {
        }
        return n;
    }
    public TakeClass getTakeClass(int TakeClassId) {
        String sql = "select * from TakeClass where TakeClassId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, TakeClassId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                TakeClass obj = new TakeClass();
                obj.setTakeClassId(rs.getInt(1));
                obj.setStudentAccountId(rs.getInt(2));
                obj.setClassId(rs.getInt(3));
                return obj;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<TakeClass> getAll() {
        ArrayList<TakeClass> vector = new ArrayList<>();
        String sql = "select * from TakeClass";
        Statement state;
        try {
            state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int TakeClassId = rs.getInt(1);
                int StudentAccountId = rs.getInt(2);
                int ClassId = rs.getInt(3);
                TakeClass obj = new TakeClass(TakeClassId, StudentAccountId, ClassId);
                vector.add(obj);
            }
        } catch (SQLException e) {
        }
        return vector;

    }

    public ArrayList<Integer> getClassIDbyStudentID(int acc) {
        ArrayList<Integer> arr = new ArrayList<>();
        String sql = "SELECT ClassId FROM TakeClass WHERE StudentAccountId = ?";

        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, acc);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int classId = rs.getInt("ClassId");
                arr.add(classId);
            }
        } catch (SQLException e) {

        }

        return arr;
    }
        public ArrayList<Integer> getStudentIDbyClassID(int acc) {
        ArrayList<Integer> arr = new ArrayList<>();
        String sql = "SELECT StudentAccountId FROM TakeClass WHERE ClassId = ?";

        try ( PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, acc);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int StudentAccountId = rs.getInt("StudentAccountId");
                arr.add(StudentAccountId);
            }
        } catch (SQLException e) {

        }

        return arr;
    }

    public static void main(String[] args) {
        DAOTakeClass dao = new DAOTakeClass();
        System.out.println(dao.getStudentIDbyClassID(1));
    }
}
