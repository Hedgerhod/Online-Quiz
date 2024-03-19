/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.Exam;
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
public class DAOExam extends DBConnect {

    public Exam fromResultSet(ResultSet rs) throws SQLException {
        return new Exam(
                rs.getInt(1),
                rs.getInt(2),
                rs.getInt(3),
                rs.getString(4),
                rs.getString(5),
                rs.getDouble(6),
                rs.getString(7),
                rs.getString(8),
                rs.getInt(9),
                rs.getInt(10),
                rs.getBoolean(11)
        );
    }

    public int insertExam(Exam obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Exam]\n"
                + "           ([ClassId]\n"
                + "           ,[TeacherAccountId]\n"
                + "           ,[Title]\n"
                + "           ,[Summary]\n"
                + "           ,[Score]\n"
                + "           ,[StartDate]\n"
                + "           ,[EndDate]\n"
                + "           ,[Timer]\n"
                + "           ,[TakingTimes]\n"
                + "           ,[Permission])\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pre = null;
        try {
            pre = connection.prepareStatement(sql);
            pre.setInt(1, obj.getClassId());
            pre.setInt(2, obj.getTeacherAccountId());
            pre.setString(3, obj.getTitle());
            pre.setString(4, obj.getSummary());
            pre.setDouble(5, obj.getScore());
            pre.setString(6, obj.getStartDate());
            pre.setString(7, obj.getEndDate());
            pre.setInt(8, obj.getTimer());
            pre.setInt(9, obj.getTakingTimes());
            pre.setBoolean(10, false);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOExam.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return n;
    }

    public int updateExam(Exam obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[Exam]\n"
                + "   SET [Title] = ?\n"
                + "      ,[Summary] = ?\n"
                + "      ,[Score] = ?\n"
                + "      ,[StartDate] = ?\n"
                + "      ,[EndDate] = ?\n"
                + "      ,[Timer] = ?\n"
                + "      ,[TakingTimes] = ?\n"
                + "      ,[Permission] = ?\n"
                + " WHERE [ExamId] = ?";
        PreparedStatement pre = null;
        try {
            pre = connection.prepareStatement(sql);
            pre.setString(1, obj.getTitle());
            pre.setString(2, obj.getSummary());
            pre.setDouble(3, obj.getScore());
            pre.setString(4, obj.getStartDate());
            pre.setString(5, obj.getEndDate());
            pre.setInt(6, obj.getTimer());
            pre.setInt(7, obj.getTakingTimes());
            pre.setBoolean(8, obj.isPermission());
            pre.setInt(9, obj.getExamId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOExam.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return n;
    }
    
    
    
    public int deleteExam(String examId) {
        int n = 0;
        PreparedStatement pre = null;
        String sql = "DELETE FROM [dbo].[Exam]\n"
                + "      WHERE [ExamId] = ?";
        try {
            pre = connection.prepareStatement(sql);
            pre.setString(1, examId);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOExam.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOExam.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return n;
    }

    public ArrayList<Exam> getExam(String sql) {
        ArrayList<Exam> list = new ArrayList<Exam>();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, //hỗ trợ Thread Safe
                    ResultSet.CONCUR_UPDATABLE);
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                int ExamId = rs.getInt("ExamId");
                int classId = rs.getInt("ClassId");
                int teacherAccountId = rs.getInt("TeacherAccountId");
                String title = rs.getString("Title");
                String summary = rs.getString("Summary");
                double score = rs.getDouble("Score");
                String startDate = rs.getString("StartDate");
                String endDate = rs.getString("EndDate");
                int timer = rs.getInt("Timer");
                int takingTimes = rs.getInt("TakingTimes");
                boolean permission = rs.getBoolean("Permission");
                Exam obj = new Exam(ExamId, classId, teacherAccountId, title, summary, score, startDate, endDate, timer, takingTimes, permission);
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

    public Exam getNewExam() throws SQLException {
        String sql = "SELECT TOP 1 *\n"
                + "FROM Exam\n"
                + "ORDER BY ExamId DESC;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return fromResultSet(rs);
        }
        return null;
    }

    public Exam getExamById(int examId) {
        String sql = "select * from Exam where ExamId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, examId);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return fromResultSet(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOExam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int createDefaultExam(int ClassId, int TeacherId) throws SQLException {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Exam]\n"
                + "           ([ClassId]\n"
                + "           ,[TeacherAccountId]\n"
                + "           ,[Title]\n"
                + "           ,[Summary]\n"
                + "           ,[Score]\n"
                + "           ,[StartDate]\n"
                + "           ,[EndDate]\n"
                + "           ,[Timer]\n"
                + "           ,[TakingTimes]\n"
                + "           ,[Permission])\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pre = null;
        try {
            pre = connection.prepareStatement(sql);
            pre.setInt(1, ClassId);
            pre.setInt(2, TeacherId);
            pre.setString(3, "default Title exam");
            pre.setString(4, "");
            pre.setDouble(5, 100);
            pre.setString(6, null);
            pre.setString(7, null);
            pre.setInt(8, 30 * 60); //30 minute
            pre.setInt(9, 1);
            pre.setBoolean(10, false);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOExam.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return n;
    }

    public ResultSet getExamDB(String sql) {
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


    public int updateExamPermission(int examId, boolean permission) {
        int n = 0;
        String sql = "UPDATE [dbo].[Exam]\n"
                + "SET [Permission] = ?\n"
                + "WHERE [ExamId] = ?";
        PreparedStatement pre = null;
        try {
            pre = connection.prepareStatement(sql);
            pre.setBoolean(1, permission);
            pre.setInt(2, examId);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOExam.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return n;
    }

    public static void main(String[] args) {
        DAOExam daoE = new DAOExam();
       //daoE.updateExamPermission(1, false);
       Exam e = daoE.getExamById(5);
        System.out.println(e);
    }
}
