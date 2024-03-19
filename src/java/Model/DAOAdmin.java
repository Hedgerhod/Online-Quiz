/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.Admin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.List;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class DAOAdmin extends DBConnect {
//         public void insertAccount(String User, String pass) {
//        try {
//            String sql = "INSERT INTO [Account]\n"
//                    + "           ([User]\n"
//                    + "           ,[pass]\n"
//                    + "           ,[isSell]\n"
//                    + "           ,[isAdmin]\n"
//                    + "           ,[active])\n"
//                    + "     VALUES\n"
//                    + "           (?\n"
//                    + "           ,?\n"
//                    + "           ,0\n"
//                    + "           ,0\n"
//                    + "           ,1)";
//            PreparedStatement stm = connection.prepareStatement(sql);
//            stm.setString(1, User);
//            stm.setString(2, pass);
//            stm.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(AcountDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//

    public List<Admin> getAllAdmin() {
        List<Admin> ad = new ArrayList<>();

        try {
            String sql = "select*from Admin";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Admin adm = new Admin();

                adm.setAccountId(rs.getInt(1));
                adm.setAdminName(rs.getString(2));
                adm.setPhone(rs.getString(3));

                ad.add(adm);

            }
        } catch (Exception e) {
                    e.printStackTrace();

        }
        return ad;

    }

    public void insertAdmin(int accountId, String adminName, String phone) {
        try {
            String sql = "INSERT INTO [dbo].[Admin] ([AccountId], [AdminName], [Phone]) VALUES (?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountId);
            stm.setString(2, adminName);
            stm.setString(3, phone);

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateAdmin(int accountId, String adminName, String phone) {
        try {
            String sql = "UPDATE [dbo].[Admin]\n"
                    + "SET \n"
                    + "    [AdminName] = ?,\n"
                    + "    [Phone] = ?\n"
                    + "WHERE AccountId = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, adminName);
            stm.setString(2, phone);
            stm.setInt(3, accountId);

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertAdmin(Admin ad) {
        try {
            String sql = "INSERT INTO [dbo].[Admin]\n"
                    + "([AccountId]\n"
                    + ",[AdminName]\n"
                    + ",[Phone])\n"
                    + "VALUES\n"
                    + "(?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, ad.getAccountId());
            stm.setString(2, ad.getAdminName());
            stm.setString(3, ad.getPhone());

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Admin getAdminById(int AccountId) {
        try {
            String sql = "select *from Admin where AccountId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, AccountId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new Admin(rs.getInt(1), rs.getString(2), rs.getString(3));

            }

        } catch (Exception e) {
        }
        return null;
    }

    public void updateAdmin(Admin a) {
        try {
            String sql = "UPDATE [dbo].[Admin]\n"
                    + "SET \n"
                    + "    [AdminName] = ?,\n"
                    + "    [Phone] = ?\n"
                    + "WHERE AccountId = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, a.getAdminName());
            stm.setString(2, a.getPhone());
            stm.setInt(3, a.getAccountId());

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteAdmin(int AccountId) {
        try {
            String sql = "delete from [admin] where AccountId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, AccountId);
            stm.executeUpdate();

        } catch (Exception e) {
        }

    }

    public List<Map<String, Object>> getAllSetting(int page, int PAGE_SIZE) {
        List<Map<String, Object>> settingList = new ArrayList<>();
        try {
            String sql = "SELECT Name, Type, Value, OrderNumber FROM ( "
                    + "    SELECT Role AS Name, 'Role' AS Type, Role AS Value, CAST(RoleId AS NVARCHAR(MAX)) AS OrderNumber "
                    + "    FROM Role "
                    + "    UNION ALL "
                    + "    SELECT SubjectName AS Name, 'Subject' AS Type, SubjectCode AS Value, CAST(SubjectId AS NVARCHAR(MAX)) AS OrderNumber "
                    + "    FROM Subject "
                    + ") AS CombinedData "
                    + "ORDER BY OrderNumber "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, (page - 1) * PAGE_SIZE);
            stm.setInt(2, PAGE_SIZE);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Map<String, Object> settingMap = new HashMap<>();
                settingMap.put("Name", rs.getString("Name"));
                settingMap.put("Type", rs.getString("Type"));
                settingMap.put("Value", rs.getString("Value"));
                settingMap.put("Order", rs.getString("OrderNumber")); // Giữ nguyên dưới dạng chuỗi

                settingList.add(settingMap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ nếu cần
        }
        return settingList;
    }
public boolean isSettingListEmpty(String keyword, int page, int PAGE_SIZE) {
    List<Map<String, Object>> settingList = searchSettingList(keyword, page, PAGE_SIZE);
    return settingList.isEmpty();
}
    public List<Map<String, Object>> searchSettingList(String keyword, int page, int PAGE_SIZE) {
        List<Map<String, Object>> settingList = new ArrayList<>();
        try {
            String sql = "SELECT Name, Type, Value, OrderNumber FROM ( "
                    + "    SELECT Role AS Name, 'Role' AS Type, Role AS Value, CAST(RoleId AS NVARCHAR(MAX)) AS OrderNumber "
                    + "    FROM Role "
                    + "    UNION ALL "
                    + "    SELECT SubjectName AS Name, 'Subject' AS Type, SubjectName AS Value, CAST(SubjectId AS NVARCHAR(MAX)) AS OrderNumber "
                    + "    FROM Subject "
                    + ") AS CombinedData "
                    + "WHERE Name LIKE ? " // Tìm kiếm theo tên
                    + "ORDER BY OrderNumber "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%"); // Thiết lập tham số cho tìm kiếm
            stm.setInt(2, (page - 1) * PAGE_SIZE);
            stm.setInt(3, PAGE_SIZE);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Map<String, Object> settingMap = new HashMap<>();
                settingMap.put("Name", rs.getString("Name"));
                settingMap.put("Type", rs.getString("Type"));
                settingMap.put("Value", rs.getString("Value"));
                settingMap.put("Order", rs.getString("OrderNumber"));

                settingList.add(settingMap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ nếu cần
        }
        return settingList;
    }

    public List<Map<String, Object>> getSettingsByRole(int page, int PAGE_SIZE) {
        List<Map<String, Object>> settingList = new ArrayList<>();
        try {
            String sql = "SELECT Name, Type, Value, OrderNumber FROM ( "
                    + "    SELECT Role AS Name, 'Role' AS Type, Role AS Value, CAST(RoleId AS NVARCHAR(MAX)) AS OrderNumber "
                    + "    FROM Role "
                    + ") AS CombinedData "
                    + "ORDER BY OrderNumber "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, (page - 1) * PAGE_SIZE);
            stm.setInt(2, PAGE_SIZE);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Map<String, Object> settingMap = new HashMap<>();
                settingMap.put("Name", rs.getString("Name"));
                settingMap.put("Type", rs.getString("Type"));
                settingMap.put("Value", rs.getString("Value"));
                settingMap.put("Order", rs.getString("OrderNumber"));

                settingList.add(settingMap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ nếu cần
        }
        return settingList;
    }

    public List<Map<String, Object>> getAllSetting() {
        List<Map<String, Object>> settingList = new ArrayList<>();
        try {
            String sql = "SELECT Role AS Name, 'Role' AS Type, Role AS Value, CAST(RoleId AS NVARCHAR(MAX)) AS OrderNumber FROM Role "
                    + "UNION ALL "
                    + "SELECT SubjectName AS Name, 'Subject' AS Type, SubjectName AS Value, CAST(SubjectId AS NVARCHAR(MAX)) AS OrderNumber FROM Subject "
                    + "ORDER BY OrderNumber";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Map<String, Object> settingMap = new HashMap<>();
                settingMap.put("Name", rs.getString("Name"));
                settingMap.put("Type", rs.getString("Type"));
                settingMap.put("Value", rs.getString("Value"));
                settingMap.put("Order", rs.getString("OrderNumber")); // Sử dụng "OrderNumber" thay vì "Order"

                settingList.add(settingMap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ nếu cần
        }
        return settingList;
    }

    public int getTotalRole() {
        try {
            String sql = "SELECT COUNT(*) FROM Role";
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

    public int getTotalSubject() {
        try {
            String sql = "SELECT COUNT(*) FROM Subject";
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

    public int getTotalSearchSetting(String keyword) {
        int totalSetting = 0;
        try {
            String sql = "SELECT COUNT(*) FROM ( "
                    + "    SELECT Role AS Name, 'Role' AS Type, Role AS Value, CAST(RoleId AS NVARCHAR(MAX)) AS OrderNumber "
                    + "    FROM Role "
                    + "    WHERE Role LIKE ? "
                    + "    UNION ALL "
                    + "    SELECT SubjectName AS Name, 'Subject' AS Type, SubjectName AS Value, CAST(SubjectId AS NVARCHAR(MAX)) AS OrderNumber "
                    + "    FROM Subject "
                    + "    WHERE SubjectName LIKE ? "
                    + ") AS CombinedData";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%");
            stm.setString(2, "%" + keyword + "%");

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                totalSetting = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ nếu cần
        }
        return totalSetting;
    }

    public int getTotalSetting() {
        try {
            String sql = "SELECT COUNT(*) FROM (SELECT RoleId FROM Role UNION ALL SELECT SubjectId FROM Subject) AS Combined";
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

    public List<String> getDistinctTypes() {
        List<String> typeList = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT Type "
                    + "FROM ( "
                    + "    SELECT 'Role' AS Type FROM Role "
                    + "    UNION ALL "
                    + "    SELECT 'Subject' AS Type FROM Subject "
                    + ") AS CombinedData";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String type = rs.getString("Type");
                typeList.add(type);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ nếu cần
        }
        return typeList;
    }

    public List<Map<String, Object>> getSettingsByType(String type) {
        List<Map<String, Object>> settingList = new ArrayList<>();
        try {
            String sql = "";
            if ("Role".equals(type)) {
                sql = "SELECT Role AS Name, 'Role' AS Type, Role AS Value, CAST(RoleId AS NVARCHAR(MAX)) AS OrderNumber FROM Role";
            } else if ("Subject".equals(type)) {
                sql = "SELECT SubjectName AS Name, 'Subject' AS Type, SubjectName AS Value, CAST(SubjectId AS NVARCHAR(MAX)) AS OrderNumber FROM Subject";
            }

            if (!sql.isEmpty()) {
                sql += " ORDER BY OrderNumber";

                PreparedStatement stm = connection.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    Map<String, Object> settingMap = new HashMap<>();
                    settingMap.put("Name", rs.getString("Name"));
                    settingMap.put("Type", rs.getString("Type"));
                    settingMap.put("Value", rs.getString("Value"));
                    settingMap.put("Order", rs.getString("OrderNumber")); // Sử dụng "OrderNumber" thay vì "Order"

                    settingList.add(settingMap);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ nếu cần
        }
        return settingList;
    }

    public List<Map<String, Object>> getSettingsBySubject(int page, int PAGE_SIZE) {
        List<Map<String, Object>> settingList = new ArrayList<>();
        try {
            String sql = "SELECT Name, Type, Value, OrderNumber FROM ( "
                    + "    SELECT SubjectName AS Name, 'Subject' AS Type, SubjectName AS Value, CAST(SubjectId AS NVARCHAR(MAX)) AS OrderNumber "
                    + "    FROM Subject "
                    + ") AS CombinedData "
                    + "ORDER BY OrderNumber "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, (page - 1) * PAGE_SIZE);
            stm.setInt(2, PAGE_SIZE);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Map<String, Object> settingMap = new HashMap<>();
                settingMap.put("Name", rs.getString("Name"));
                settingMap.put("Type", rs.getString("Type"));
                settingMap.put("Value", rs.getString("Value"));
                settingMap.put("Order", rs.getString("OrderNumber"));

                settingList.add(settingMap);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ nếu cần
        }
        return settingList;
    }

    public Map<String, Object> getSettingById(int id, String type) {
        Map<String, Object> settingMap = new HashMap<>();
        try {
            String sql = "";
            if ("Role".equals(type)) {
                sql = "SELECT RoleId AS Id, Role AS Name, 'Role' AS Type, Role AS Value FROM Role WHERE RoleId = ?";
            } else if ("Subject".equals(type)) {
                sql = "SELECT SubjectId AS Id, SubjectName AS Name, 'Subject' AS Type, SubjectCode AS Value FROM Subject WHERE SubjectId = ?";
            } else {
                // Xử lý trường hợp loại không hợp lệ nếu cần
                return settingMap;
            }

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                settingMap.put("Id", rs.getInt("Id")); // Thêm ID vào map
                settingMap.put("Name", rs.getString("Name"));
                settingMap.put("Type", rs.getString("Type"));
                settingMap.put("Value", rs.getString("Value"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ nếu cần
        }
        return settingMap;
    }

    public void updateSettingRole(int id, String newValue) {
        try {
            String sql = "UPDATE Role SET Role = ? WHERE RoleId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, newValue);
            stm.setInt(2, id);

            stm.executeUpdate();

            // Không cần trả về giá trị hay xử lý ngoại lệ ở đây
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ nếu cần
        }
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

    public int updateSubjectNameByCode(String subjectCode, String newSubjectName) {
        int n = 0;
        String sql = "UPDATE [dbo].[Subject]\n"
                + "SET [SubjectName] = ?\n"
                + "WHERE [SubjectCode] = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, newSubjectName);
            pre.setString(2, subjectCode);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSubject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public static void main(String[] args) {
        DAOAdmin dao = new DAOAdmin();
//        List<Admin> a = dao.getAllAdmin();
//        for (Admin object : a) {
//            System.out.println(a);
//        }
//
//        Admin newAdmin = new Admin(3, "kienhghg", "0988488999");
//
//        // Thực hiện thêm Admin mới vào cơ sở dữ liệu
//        dao.insertAdmin(newAdmin);
//
//        // Hiển thị danh sách Admin sau khi thêm mới
//        System.out.println("\nDanh sách admin sau khi thêm mới:");
//        List<Admin> adminsAfterInsert = dao.getAllAdmin();
//        for (Admin admin : adminsAfterInsert) {
//            System.out.println(admin);
//
//        }

String subjectCodeToCheck = "SWQ"; // Thay thế bằng mã môn học bạn muốn kiểm tra
        boolean isExist = dao.checkSubjectCodeExistence(subjectCodeToCheck);
        
        // In kết quả kiểm tra
        if (isExist) {
            System.out.println("Subject code '" + subjectCodeToCheck + "' exists.");
        } else {
            System.out.println("Subject code '" + subjectCodeToCheck + "' does not exist.");
        }

    }

}
