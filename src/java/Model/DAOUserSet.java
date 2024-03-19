/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.UserSet;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phamg
 */
public class DAOUserSet extends DBConnect  {

    public boolean insertUserSet(UserSet userSet) {
        try {
            String sql = "INSERT INTO [UserSetSaved] ([UserId], [SetId]) VALUES (?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userSet.getUserId());
            stm.setInt(2, userSet.getSetId());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserSet.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean updateUserSet(int userId, int setId) {
        try {
            String sql = "UPDATE [UserSetSaved] SET [SetId] = ? WHERE [UserId] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, setId);
            stm.setInt(2, userId);
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserSet.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deleteAllUserSet(int userId) {
        try {
            String sql = "DELETE FROM [UserSetSaved] WHERE [UserId] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserSet.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean deleteUserSet(int userId, int setId) {
    try {
        String sql = "DELETE FROM [UserSetSaved] WHERE [UserId] = ? AND [SetId] = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, userId);
        stm.setInt(2, setId);
        int rowsAffected = stm.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException ex) {
        Logger.getLogger(DAOUserSet.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
    }
    public List<UserSet> getAllUserSets() {
        List<UserSet> userSets = new ArrayList<>();
        try {
            String sql = "SELECT [UserId], [SetId] FROM [UserSetSaved]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("UserId");
                int setId = rs.getInt("SetId");
                UserSet userSet = new UserSet(userId, setId);
                userSets.add(userSet);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUserSet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userSets;
    } 
    
public List<UserSet> getEnroll(int UserId) {
    List<UserSet> userSets = new ArrayList<>();
    try {
        String sql = "SELECT [UserId], [SetId] FROM [UserSetSaved] WHERE [UserId] = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, UserId); 
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            int setId = rs.getInt("SetId");
            UserSet userSet = new UserSet(UserId, setId); 
            userSets.add(userSet);
        }
    } catch (SQLException ex) {
        Logger.getLogger(DAOUserSet.class.getName()).log(Level.SEVERE, null, ex);
    }
    return userSets;
}

    public static void main(String[] args) {
        DAOUserSet dao = new DAOUserSet();
        System.out.println(dao.getEnroll(1));
    }
}
