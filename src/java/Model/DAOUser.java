
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.EncryptionUtils;

/**
 *
 * @author Asus
 */
public class DAOUser extends DBConnect {

    public List<User> getAllUser() {
        List<User> user = new ArrayList<>();

        try {
            String sql = "select * from [User]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User us = fromResultSet(rs);
                user.add(us);

            }
        } catch (Exception e) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, e);
        }
        return user;

    }

    public void insertUser(User user) {
        try {
            String sql = "INSERT INTO [User] ([Username], [Email], [Password], [RoleId], [IsActive])\n"
                    + "VALUES (?, ?, ?, ?, 1);";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user.getUsername());
            stm.setString(2, user.getEmail());
            stm.setString(3, user.getPassword());
            stm.setInt(4, user.getRoleId());

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int updateUserAndGetAccountId(int accountId, String username, String email, String password, int roleId, int isActive) {
        try {
            String sql = "UPDATE [User] SET [Username] = ?, [Email] = ?, [Password] = ?, [RoleId] = ?, [IsActive] = ? WHERE [AccountId] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, email);
            stm.setString(3, password);
            stm.setInt(4, roleId);
            stm.setInt(5, isActive);
            stm.setInt(6, accountId);
            stm.executeUpdate();
            return accountId;
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public int updateUserAndGetAccountId(int accountId, String username, String email, String password) {
        try {
            String sql = "UPDATE [User] SET [Username] = ?, [Email] = ?, [Password] = ? WHERE [AccountId] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, email);
            stm.setString(3, password);
            stm.setInt(4, accountId);
            stm.executeUpdate();
            return accountId;
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public int insertUserAndGetAccountId(String username, String email, String password, int roleId, int isActive) {
        int accountId = -1;
        try {
            String sql = "INSERT INTO [User] ([Username], [Email], [Password], [RoleId], [IsActive]) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, username);
            stm.setString(2, email);
            stm.setString(3, password);
            stm.setInt(4, roleId);
            stm.setInt(5, isActive);
            stm.executeUpdate();

            ResultSet generatedKeys = stm.getGeneratedKeys();
            if (generatedKeys.next()) {
                accountId = generatedKeys.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accountId;
    }

    public void insertUser(String username, String email, String password, int roleId, boolean isActive) {
        try {
            String sql = "INSERT INTO [User] ([Username], [Email], [Password], [RoleId], [IsActive]) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, email);
            stm.setString(3, password);
            stm.setInt(4, roleId);
            stm.setBoolean(5, isActive);

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getUserById(int accountId) {
        try {
            String sql = "select *from [User] where AccountId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getBoolean(6));
            }

        } catch (Exception e) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public User getUser(String sql) {
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getBoolean(6));
            }

        } catch (Exception e) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public void updateUser(User user) {
        try {
            String sql = "UPDATE [User]\n"
                    + "   SET\n"
                    + "		[Username] = ?\n"
                    + "      ,[Email] = ?\n"
                    + "      ,[Password] = ?\n"
                    + "		,[RoleId] = ?\n"
                    + "      ,[IsActive] = ?\n"
                    + " WHERE AccountId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user.getUsername());
            stm.setString(2, user.getEmail());
            stm.setString(3, user.getPassword());
            stm.setInt(4, user.getRoleId());
            stm.setBoolean(5, user.isActive());
            stm.setInt(6, user.getAccountId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getUser(String username, String pass) throws SQLException {
        String sql = "select * from [User] where [Username] = ? and [Password] = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, pass);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return fromResultSet(rs);
        }
        return null;
    }

    public List<User> checkUser(String username, String passWord) {
        List<User> t = new ArrayList<>();
        try {
            String sql = "select * from [User] where [Username] = ? and [Password] = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, passWord);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                t.add(fromResultSet(rs));
            }
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public boolean usernameExists(String username) {
        boolean result = false;
        try {
            String sql = "SELECT * FROM [User] WHERE Username=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = true; // Tên người dùng đã tồn tại trong cơ sở dữ liệu
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean emailCheck(String email) {
        boolean result = false;
        try {
            String sql = "SELECT * FROM [User] WHERE Email=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result = true;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public boolean usernameCheck(String username) {
        boolean result = false;
        try {
            String sql = "SELECT * FROM [User] WHERE Username=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result = true;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public User fromResultSet(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getInt(5),
                rs.getBoolean(6)
        );
    }

    public void deleteUser(int AccountId) {
        try {
            String sql = "delete from [User] where AccountId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, AccountId);
            stm.executeUpdate();

        } catch (Exception e) {
        }

    }

    public List<User> getAllUserListData(String sql) {
        List<User> userList = new ArrayList<>();
        Statement state;
        try {
            state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                int accountId = rs.getInt("AccountId");
                String username = rs.getString("Username");
                String email = rs.getString("Email");
                String password = rs.getString("Password");
                int roleId = rs.getInt("RoleId");
                boolean isActive = rs.getBoolean("isActive");

                User user = new User(accountId, username, email, password, roleId, isActive);
                userList.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return userList;
    }

    public List<Map<String, Object>> searchUsers(String keyword) {
        List<Map<String, Object>> userList = new ArrayList<>();
        try {
            String sql = "SELECT u.AccountId, u.Username, u.Email, COALESCE(s.Phone, t.Phone, a.Phone) AS Phone, u.RoleId, u.IsActive "
                    + "FROM [User] AS u "
                    + "LEFT JOIN Student AS s ON u.AccountId = s.AccountId "
                    + "LEFT JOIN Teacher AS t ON u.AccountId = t.AccountId "
                    + "LEFT JOIN Admin AS a ON u.AccountId = a.AccountId "
                    + "WHERE u.Username LIKE ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%");

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("AccountId", rs.getInt("AccountId"));
                userMap.put("Username", rs.getString("Username"));
                userMap.put("Email", rs.getString("Email"));
                userMap.put("Phone", rs.getString("Phone"));
                userMap.put("RoleId", rs.getInt("RoleId"));
                userMap.put("IsActive", rs.getBoolean("IsActive"));

                userList.add(userMap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    public List<Map<String, Object>> getAllUsers() {
        List<Map<String, Object>> userList = new ArrayList<>();
        try {
            String sql = "SELECT u.AccountId, u.Username, u.Email, COALESCE(s.Phone, t.Phone, a.Phone) AS Phone, u.RoleId, u.IsActive "
                    + "FROM [User] AS u "
                    + "LEFT JOIN Student AS s ON u.AccountId = s.AccountId "
                    + "LEFT JOIN Teacher AS t ON u.AccountId = t.AccountId "
                    + "LEFT JOIN Admin AS a ON u.AccountId = a.AccountId";

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("AccountId", rs.getInt("AccountId"));
                userMap.put("Username", rs.getString("Username"));
                userMap.put("Email", rs.getString("Email"));
                userMap.put("Phone", rs.getString("Phone"));
                userMap.put("RoleId", rs.getInt("RoleId"));
                userMap.put("IsActive", rs.getBoolean("IsActive"));

                userList.add(userMap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    public List<Map<String, Object>> getAllActiveUsers() {
        List<Map<String, Object>> userList = new ArrayList<>();
        try {
            String sql = "SELECT u.AccountId, u.Username, u.Email, COALESCE(s.Phone, t.Phone, a.Phone) AS Phone, u.RoleId, u.IsActive "
                    + "FROM [User] AS u "
                    + "LEFT JOIN Student AS s ON u.AccountId = s.AccountId "
                    + "LEFT JOIN Teacher AS t ON u.AccountId = t.AccountId "
                    + "LEFT JOIN Admin AS a ON u.AccountId = a.AccountId "
                    + "WHERE u.IsActive = 1"; // Thêm điều kiện WHERE isActive = 1

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("AccountId", rs.getInt("AccountId"));
                userMap.put("Username", rs.getString("Username"));
                userMap.put("Email", rs.getString("Email"));
                userMap.put("Phone", rs.getString("Phone"));
                userMap.put("RoleId", rs.getInt("RoleId"));
                userMap.put("IsActive", rs.getBoolean("IsActive"));

                userList.add(userMap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    public List<Map<String, Object>> getAllSuspendedUsers() {
        List<Map<String, Object>> userList = new ArrayList<>();
        try {
            String sql = "SELECT u.AccountId, u.Username, u.Email, COALESCE(s.Phone, t.Phone, a.Phone) AS Phone, u.RoleId, u.IsActive "
                    + "FROM [User] AS u "
                    + "LEFT JOIN Student AS s ON u.AccountId = s.AccountId "
                    + "LEFT JOIN Teacher AS t ON u.AccountId = t.AccountId "
                    + "LEFT JOIN Admin AS a ON u.AccountId = a.AccountId "
                    + "WHERE u.IsActive = 0"; // Thêm điều kiện WHERE isActive = 1

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("AccountId", rs.getInt("AccountId"));
                userMap.put("Username", rs.getString("Username"));
                userMap.put("Email", rs.getString("Email"));
                userMap.put("Phone", rs.getString("Phone"));
                userMap.put("RoleId", rs.getInt("RoleId"));
                userMap.put("IsActive", rs.getBoolean("IsActive"));

                userList.add(userMap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    public List<Map<String, Object>> getAllActiveAdminUsers() {
        List<Map<String, Object>> userList = new ArrayList<>();
        try {
            String sql = "SELECT u.AccountId, u.Username, u.Email, COALESCE(s.Phone, t.Phone, a.Phone) AS Phone, u.RoleId, u.IsActive "
                    + "FROM [User] AS u "
                    + "LEFT JOIN Student AS s ON u.AccountId = s.AccountId "
                    + "LEFT JOIN Teacher AS t ON u.AccountId = t.AccountId "
                    + "LEFT JOIN Admin AS a ON u.AccountId = a.AccountId "
                    + "WHERE u.RoleId = 3 AND u.IsActive = 1"; // Thêm điều kiện WHERE RoleId = 1 và IsActive = 1

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("AccountId", rs.getInt("AccountId"));
                userMap.put("Username", rs.getString("Username"));
                userMap.put("Email", rs.getString("Email"));
                userMap.put("Phone", rs.getString("Phone"));
                userMap.put("RoleId", rs.getInt("RoleId"));
                userMap.put("IsActive", rs.getBoolean("IsActive"));

                userList.add(userMap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    public List<Map<String, Object>> getAllActiveTeacherUsers() {
        List<Map<String, Object>> userList = new ArrayList<>();
        try {
            String sql = "SELECT u.AccountId, u.Username, u.Email, COALESCE(s.Phone, t.Phone, a.Phone) AS Phone, u.RoleId, u.IsActive "
                    + "FROM [User] AS u "
                    + "LEFT JOIN Student AS s ON u.AccountId = s.AccountId "
                    + "LEFT JOIN Teacher AS t ON u.AccountId = t.AccountId "
                    + "LEFT JOIN Admin AS a ON u.AccountId = a.AccountId "
                    + "WHERE u.RoleId = 2 AND u.IsActive = 1"; // Thêm điều kiện WHERE RoleId = 2 và IsActive = 1

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("AccountId", rs.getInt("AccountId"));
                userMap.put("Username", rs.getString("Username"));
                userMap.put("Email", rs.getString("Email"));
                userMap.put("Phone", rs.getString("Phone"));
                userMap.put("RoleId", rs.getInt("RoleId"));
                userMap.put("IsActive", rs.getBoolean("IsActive"));

                userList.add(userMap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    public List<Map<String, Object>> getAllSuspendedTeacherUsers() {
        List<Map<String, Object>> userList = new ArrayList<>();
        try {
            String sql = "SELECT u.AccountId, u.Username, u.Email, COALESCE(s.Phone, t.Phone, a.Phone) AS Phone, u.RoleId, u.IsActive "
                    + "FROM [User] AS u "
                    + "LEFT JOIN Student AS s ON u.AccountId = s.AccountId "
                    + "LEFT JOIN Teacher AS t ON u.AccountId = t.AccountId "
                    + "LEFT JOIN Admin AS a ON u.AccountId = a.AccountId "
                    + "WHERE u.RoleId = 2 AND u.IsActive = 0"; // Thêm điều kiện WHERE RoleId = 2 và IsActive = 0

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("AccountId", rs.getInt("AccountId"));
                userMap.put("Username", rs.getString("Username"));
                userMap.put("Email", rs.getString("Email"));
                userMap.put("Phone", rs.getString("Phone"));
                userMap.put("RoleId", rs.getInt("RoleId"));
                userMap.put("IsActive", rs.getBoolean("IsActive"));

                userList.add(userMap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    public List<Map<String, Object>> getAllActiveStudentUsers() {
        List<Map<String, Object>> userList = new ArrayList<>();
        try {
            String sql = "SELECT u.AccountId, u.Username, u.Email, COALESCE(s.Phone, t.Phone, a.Phone) AS Phone, u.RoleId, u.IsActive "
                    + "FROM [User] AS u "
                    + "LEFT JOIN Student AS s ON u.AccountId = s.AccountId "
                    + "LEFT JOIN Teacher AS t ON u.AccountId = t.AccountId "
                    + "LEFT JOIN Admin AS a ON u.AccountId = a.AccountId "
                    + "WHERE u.RoleId = 1 AND u.IsActive = 1"; // Thêm điều kiện WHERE RoleId = 3 và IsActive = 1

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("AccountId", rs.getInt("AccountId"));
                userMap.put("Username", rs.getString("Username"));
                userMap.put("Email", rs.getString("Email"));
                userMap.put("Phone", rs.getString("Phone"));
                userMap.put("RoleId", rs.getInt("RoleId"));
                userMap.put("IsActive", rs.getBoolean("IsActive"));

                userList.add(userMap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    public List<Map<String, Object>> getAllSuspendedStudentUsers() {
        List<Map<String, Object>> userList = new ArrayList<>();
        try {
            String sql = "SELECT u.AccountId, u.Username, u.Email, COALESCE(s.Phone, t.Phone, a.Phone) AS Phone, u.RoleId, u.IsActive "
                    + "FROM [User] AS u "
                    + "LEFT JOIN Student AS s ON u.AccountId = s.AccountId "
                    + "LEFT JOIN Teacher AS t ON u.AccountId = t.AccountId "
                    + "LEFT JOIN Admin AS a ON u.AccountId = a.AccountId "
                    + "WHERE u.RoleId = 1 AND u.IsActive = 0"; // Thêm điều kiện WHERE RoleId = 3 và IsActive = 0

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("AccountId", rs.getInt("AccountId"));
                userMap.put("Username", rs.getString("Username"));
                userMap.put("Email", rs.getString("Email"));
                userMap.put("Phone", rs.getString("Phone"));
                userMap.put("RoleId", rs.getInt("RoleId"));
                userMap.put("IsActive", rs.getBoolean("IsActive"));

                userList.add(userMap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    public List<Map<String, Object>> getAllBanAdminUsers() {
        List<Map<String, Object>> userList = new ArrayList<>();
        try {
            String sql = "SELECT u.AccountId, u.Username, u.Email, COALESCE(s.Phone, t.Phone, a.Phone) AS Phone, u.RoleId, u.IsActive "
                    + "FROM [User] AS u "
                    + "LEFT JOIN Student AS s ON u.AccountId = s.AccountId "
                    + "LEFT JOIN Teacher AS t ON u.AccountId = t.AccountId "
                    + "LEFT JOIN Admin AS a ON u.AccountId = a.AccountId "
                    + "WHERE u.RoleId = 3 AND u.IsActive = 0"; // Thêm điều kiện WHERE RoleId = 1 và IsActive = 1

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("AccountId", rs.getInt("AccountId"));
                userMap.put("Username", rs.getString("Username"));
                userMap.put("Email", rs.getString("Email"));
                userMap.put("Phone", rs.getString("Phone"));
                userMap.put("RoleId", rs.getInt("RoleId"));
                userMap.put("IsActive", rs.getBoolean("IsActive"));

                userList.add(userMap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    public List<Map<String, Object>> getAllTeacherUsers() {
        List<Map<String, Object>> userList = new ArrayList<>();
        try {
            String sql = "SELECT u.AccountId, u.Username, u.Email, COALESCE(t.Phone, a.Phone) AS Phone, u.RoleId, u.IsActive "
                    + "FROM [User] AS u "
                    + "LEFT JOIN Teacher AS t ON u.AccountId = t.AccountId "
                    + "LEFT JOIN Admin AS a ON u.AccountId = a.AccountId "
                    + "WHERE u.RoleId = 2"; // Lấy người dùng với RoleId = 2 (giáo viên)

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("AccountId", rs.getInt("AccountId"));
                userMap.put("Username", rs.getString("Username"));
                userMap.put("Email", rs.getString("Email"));
                userMap.put("Phone", rs.getString("Phone"));
                userMap.put("RoleId", rs.getInt("RoleId"));
                userMap.put("IsActive", rs.getBoolean("IsActive"));

                userList.add(userMap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    public List<Map<String, Object>> getAllStudentUsers() {
        List<Map<String, Object>> userList = new ArrayList<>();
        try {
            String sql = "SELECT u.AccountId, u.Username, u.Email, COALESCE(s.Phone, a.Phone) AS Phone, u.RoleId, u.IsActive "
                    + "FROM [User] AS u "
                    + "LEFT JOIN Student AS s ON u.AccountId = s.AccountId "
                    + "LEFT JOIN Admin AS a ON u.AccountId = a.AccountId "
                    + "WHERE u.RoleId = 3"; // Lấy người dùng với RoleId = 3 (sinh viên)

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("AccountId", rs.getInt("AccountId"));
                userMap.put("Username", rs.getString("Username"));
                userMap.put("Email", rs.getString("Email"));
                userMap.put("Phone", rs.getString("Phone"));
                userMap.put("RoleId", rs.getInt("RoleId"));
                userMap.put("IsActive", rs.getBoolean("IsActive"));

                userList.add(userMap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    public List<Map<String, Object>> getAllAdminUsers() {
        List<Map<String, Object>> userList = new ArrayList<>();
        try {
            String sql = "SELECT u.AccountId, u.Username, u.Email, COALESCE(s.Phone, t.Phone, a.Phone) AS Phone, u.RoleId, u.IsActive "
                    + "FROM [User] AS u "
                    + "LEFT JOIN Student AS s ON u.AccountId = s.AccountId "
                    + "LEFT JOIN Teacher AS t ON u.AccountId = t.AccountId "
                    + "LEFT JOIN Admin AS a ON u.AccountId = a.AccountId "
                    + "WHERE u.RoleId = 1"; // Lọc ra những user có RoleId = 1 (Admin)

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("AccountId", rs.getInt("AccountId"));
                userMap.put("Username", rs.getString("Username"));
                userMap.put("Email", rs.getString("Email"));
                userMap.put("Phone", rs.getString("Phone"));
                userMap.put("RoleId", rs.getInt("RoleId"));
                userMap.put("IsActive", rs.getBoolean("IsActive"));

                userList.add(userMap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ nếu có
        }
        return userList;
    }

    public List<Map<String, Object>> searchUsersWithPagination(String keyword, int page, int pageSize) {
        List<Map<String, Object>> userList = new ArrayList<>();
        try {
            String sql = "SELECT u.AccountId, u.Username, u.Email, COALESCE(s.Phone, t.Phone, a.Phone) AS Phone, u.RoleId, u.IsActive "
                    + "FROM [User] AS u "
                    + "LEFT JOIN Student AS s ON u.AccountId = s.AccountId "
                    + "LEFT JOIN Teacher AS t ON u.AccountId = t.AccountId "
                    + "LEFT JOIN Admin AS a ON u.AccountId = a.AccountId "
                    + "WHERE u.Username LIKE ? "
                    + "ORDER BY u.AccountId OFFSET (? - 1) * ? ROWS FETCH NEXT ? ROWS ONLY";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%");
            stm.setInt(2, page);
            stm.setInt(3, pageSize);
            stm.setInt(4, pageSize);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("AccountId", rs.getInt("AccountId"));
                userMap.put("Username", rs.getString("Username"));
                userMap.put("Email", rs.getString("Email"));
                userMap.put("Phone", rs.getString("Phone"));
                userMap.put("RoleId", rs.getInt("RoleId"));
                userMap.put("IsActive", rs.getBoolean("IsActive"));

                userList.add(userMap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    public boolean isUserSearchResultEmpty(String keyword, int page, int pageSize) {
        List<Map<String, Object>> userList = searchUsersWithPagination(keyword, page, pageSize);
        return userList.isEmpty();
    }

    public int getTotalUser() {
        try {
            String sql = "SELECT COUNT(u.AccountId) FROM [User] u";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // Xử lý lỗi theo nhu cầu
        }
        return 0;
    }

    public List<Map<String, Object>> getAllUserWithPaging(int page, int PAGE_SIZE) {
        List<Map<String, Object>> userList = new ArrayList<>();
        try {
            String sql = "SELECT u.AccountId, u.Username, u.Email, COALESCE(s.Phone, t.Phone, a.Phone) AS Phone, u.RoleId, u.IsActive "
                    + "FROM [User] AS u "
                    + "LEFT JOIN Student AS s ON u.AccountId = s.AccountId "
                    + "LEFT JOIN Teacher AS t ON u.AccountId = t.AccountId "
                    + "LEFT JOIN Admin AS a ON u.AccountId = a.AccountId "
                    + "ORDER BY u.AccountId OFFSET (?-1)*? ROWS FETCH NEXT ? ROWS ONLY";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, page);
            stm.setInt(2, PAGE_SIZE);
            stm.setInt(3, PAGE_SIZE);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("AccountId", rs.getInt("AccountId"));
                userMap.put("Username", rs.getString("Username"));
                userMap.put("Email", rs.getString("Email"));
                userMap.put("Phone", rs.getString("Phone"));
                userMap.put("RoleId", rs.getInt("RoleId"));
                userMap.put("IsActive", rs.getBoolean("IsActive"));

                userList.add(userMap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ nếu cần
        }
        return userList;
    }

    public int getTotalSearch(String keyword) {
        int total = 0;
        try {
            String sql = "SELECT COUNT(*) AS total FROM [User] WHERE Username LIKE ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%");
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return total;
    }

    public HashMap<String, Object> getUserByIdd(int AccountId) {
        try {
            String sql = "SELECT u.AccountId, u.Username, u.Email, COALESCE(s.Phone, t.Phone, a.Phone) AS Phone, s.Dob AS Dob, "
                    + "s.StudentName, t.TeacherName, a.AdminName, "
                    + "u.RoleId, u.IsActive, u.Password "
                    + "FROM [User] AS u "
                    + "LEFT JOIN Student AS s ON u.AccountId = s.AccountId "
                    + "LEFT JOIN Teacher AS t ON u.AccountId = t.AccountId "
                    + "LEFT JOIN Admin AS a ON u.AccountId = a.AccountId "
                    + "WHERE u.AccountId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, AccountId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                HashMap<String, Object> userMap = new HashMap<>();
                userMap.put("AccountId", rs.getInt("AccountId"));
                userMap.put("Username", rs.getString("Username"));
                userMap.put("Email", rs.getString("Email"));
                userMap.put("Phone", rs.getString("Phone"));
                userMap.put("Dob", rs.getDate("Dob")); // Đảm bảo cột "Dob" là kiểu ngày trong cơ sở dữ liệu
                userMap.put("StudentName", rs.getString("StudentName"));
                userMap.put("TeacherName", rs.getString("TeacherName"));
                userMap.put("AdminName", rs.getString("AdminName"));
                userMap.put("RoleId", rs.getInt("RoleId"));
                userMap.put("IsActive", rs.getBoolean("IsActive"));
                userMap.put("Password", rs.getString("Password")); // Lấy password
                return userMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    public User checkAccount(String username, String password) {

        String sql = "select * from [User] where Username = ? and Password = ?";
        try {
            EncryptionUtils encryptionUtils = new EncryptionUtils();
            String opass = encryptionUtils.toMD5(password);
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, opass);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new User(username, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void ChangePass(User u) {
        String sql = "update [User] set Password = ? where Username = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            EncryptionUtils e = new EncryptionUtils();
            pre.setString(1, e.toMD5(u.getPassword()));
            pre.setString(2, u.getUsername());
            pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUserByEmail(String email) {
        String sql = "select * from [User] where [Email] = ?";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return fromResultSet(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public User checkUserObj(String username, String passWord) {
        User t = null;
        try {
            String sql = "select * from [User] where [Username] = ? and [Password] = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, passWord);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                t = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getBoolean(6));
            }
            ps.execute();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public int ResetPassword(String email, String pass) {
        int n = 0;
        EncryptionUtils encrypt = new EncryptionUtils();
        pass = encrypt.toMD5(pass);
        try {
            String sql = "UPDATE [dbo].[User]\n"
                    + "   SET [Password] = ?\n"
                    + " WHERE Email = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, pass);
            stm.setString(2, email);
            n = stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public static void main(String[] args) {
        DAOUser dao = new DAOUser();

        List<User> us = dao.checkUser("hieu", "123456");
        for (User u : us) {
            System.out.println(u);

        }
//        User u = dao.getUserById(1);
//        System.out.println(u);
        int accountId = dao.insertUserAndGetAccountId("testUser", "test@example.com", "testPassword", 1, 1);
        System.out.println("Account ID: " + accountId);

//        if (user != null) {
//            System.out.println("AccountId: " + user.get("AccountId"));
//            System.out.println("Username: " + user.get("Username"));
//            System.out.println("Email: " + user.get("Email"));
//            System.out.println("Phone: " + user.get("Phone"));
//            System.out.println("RoleId: " + user.get("RoleId"));
//            System.out.println("Dob: " + user.get("Dob"));
//            System.out.println("IsActive: " + user.get("IsActive"));
//            System.out.println();
//        } else {
//            System.out.println("User not found!");
//        }
        dao.deleteUser(15);
    }

}
