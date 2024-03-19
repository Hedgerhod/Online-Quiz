/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.questionExam;
import Entity.questionExamAnswer;
import Entity.TakeExam;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hieul
 */
public class DAOTakeExam extends DBConnect {

    public int insertTakeExam(TakeExam obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[TakeExam]\n"
                + "           ([StudentAccountId]\n"
                + "           ,[ExamId]\n"
                + "           ,[Status]\n"
                + "           ,[Score]\n"
                + "           ,[StartDate]\n"
                + "           ,[EndDate])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?)";
        PreparedStatement pre = null;
        try {
            pre = connection.prepareStatement(sql);
            pre.setInt(1, obj.getStudentAccountId());
            pre.setInt(2, obj.getExamId());
            pre.setString(3, obj.getStatus());
            pre.setDouble(4, obj.getScore());
            pre.setString(5, obj.getStartDate());
            pre.setString(6, obj.getEndDate());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOTakeExam.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return n;
    }

    public int updateTakeExam(TakeExam obj) {
        int n = 0;
        PreparedStatement pre = null;
        String sql = "UPDATE [dbo].[TakeExam]\n"
                + "   SET [StudentAccountId] = ?\n"
                + "      ,[ExamId] = ?\n"
                + "      ,[Status] = ?\n"
                + "      ,[Score] = ?\n"
                + "      ,[StartDate] = ?\n"
                + "      ,[EndDate] = ?\n"
                + " WHERE [TakeExamId] = ?";
        try {
            pre = connection.prepareStatement(sql);
            pre.setInt(1, obj.getStudentAccountId());
            pre.setInt(2, obj.getExamId());
            pre.setString(3, obj.getStatus());
            pre.setDouble(4, obj.getScore());
            pre.setString(5, obj.getStartDate());
            pre.setString(6, obj.getEndDate());
            pre.setInt(7, obj.getTakeExamId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTakeExam.class.getName()).log(Level.SEVERE, null, ex);
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

    public int deleteTakeExam(String takeExamId) {
        int n = 0;
        PreparedStatement pre = null;
        String sql = "DELETE FROM [dbo].[TakeExam]\n"
                + "      WHERE [TakeExamId] = ?";
        try {
            pre = connection.prepareStatement(sql);
            pre.setString(1, takeExamId);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOTakeExam.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return n;
    }

    public ArrayList<TakeExam> getTakeExam(String sql) {
        ArrayList<TakeExam> list = new ArrayList<TakeExam>();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, //hỗ trợ Thread Safe
                    ResultSet.CONCUR_UPDATABLE);
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                int takeExamId = rs.getInt("TakeExamId");
                int studentAccountId = rs.getInt("StudentAccountId");
                int examId = rs.getInt("ExamId");
                String status = rs.getString("Status");
                Double score = rs.getDouble("Score");
                String startDate = rs.getString("StartDate");
                String endDate = rs.getString("EndDate");

                TakeExam obj = new TakeExam(takeExamId, studentAccountId, examId, status, score, startDate, endDate);

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

    public ResultSet getTakeExamDB(String sql) {
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

    public ArrayList<TakeExam> getTakeExamByExamId(int examId) {
        ArrayList<TakeExam> list = new ArrayList<TakeExam>();
        PreparedStatement pre = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM [dbo].[TakeExam] WHERE [ExamId] = ?";
        try {
            pre = connection.prepareStatement(sql);
            pre.setInt(1, examId);
            rs = pre.executeQuery();
            while (rs.next()) {
                int takeExamId = rs.getInt("TakeExamId");
                int studentAccountId = rs.getInt("StudentAccountId");
                String status = rs.getString("Status");
                double score = rs.getDouble("Score");
                String startDate = rs.getString("StartDate");
                String endDate = rs.getString("EndDate");

                TakeExam obj = new TakeExam(takeExamId, studentAccountId, examId, status, score, startDate, endDate);
                list.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTakeExam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public TakeExam getTakeExamByTakeExamIdObj(int takeExamId) {
        TakeExam takeExam = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM [dbo].[TakeExam] WHERE [TakeExamId] = ?";
        try {
            pre = connection.prepareStatement(sql);
            pre.setInt(1, takeExamId);
            rs = pre.executeQuery();
            while (rs.next()) {

                int studentAccountId = rs.getInt("StudentAccountId");
                String status = rs.getString("Status");
                int examId = rs.getInt("ExamId");
                double score = rs.getDouble("Score");
                String startDate = rs.getString("StartDate");
                String endDate = rs.getString("EndDate");

                takeExam = new TakeExam(takeExamId, studentAccountId, examId, status, score, startDate, endDate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTakeExam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return takeExam;
    }

    public TakeExam getTakeExamByExamIdObj(int examId) {
        TakeExam takeExam = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM [dbo].[TakeExam] WHERE [ExamId] = ?";
        try {
            pre = connection.prepareStatement(sql);
            pre.setInt(1, examId);
            rs = pre.executeQuery();
            while (rs.next()) {
                int takeExamId = rs.getInt("TakeExamId");
                int studentAccountId = rs.getInt("StudentAccountId");
                String status = rs.getString("Status");
                double score = rs.getDouble("Score");
                String startDate = rs.getString("StartDate");
                String endDate = rs.getString("EndDate");

                takeExam = new TakeExam(takeExamId, studentAccountId, examId, status, score, startDate, endDate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTakeExam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return takeExam;
    }

    public int takeExam(int userId, int examId) {
        int check = isTakingExam(userId, examId);
        if (check != -1) {
            return check;
        }
        ResultSet rs = null;
        PreparedStatement pre = null;
        String sql = "INSERT INTO [dbo].[TakeExam]\n"
                + "           ([StudentAccountId]\n"
                + "           ,[ExamId]\n"
                + "           ,[Status]\n"
                + "           ,[Score]\n"
                + "           ,[StartDate]\n"
                + "           ,[EndDate])\n"
                + "     VALUES\n"
                + "           (?,?, 1, 0, GETDATE(), GETDATE())";
        try {
            pre = connection.prepareStatement(sql);
            pre.setInt(1, userId);
            pre.setInt(2, examId);
            pre.executeUpdate();
            pre.close(); // Close the previous PreparedStatement

            sql = "  select * from TakeExam where StudentAccountId=? and ExamId = ? and Status = 1";
            pre = connection.prepareStatement(sql); // Create a new PreparedStatement
            pre.setInt(1, userId);
            pre.setInt(2, examId);
            rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getInt("TakeExamId");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTakeExam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public TakeExam bestAttempt(int userId, int examId) {
        TakeExam best = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        String sql = " select top (1) * from TakeExam\n"
                + "  where StudentAccountId=? and ExamId = ? and Score >= all(select Score from TakeExam where Score is not null and ExamId = ?)";
        try {
            pre = connection.prepareStatement(sql);
            pre.setInt(1, userId);
            pre.setInt(2, examId);
            pre.setInt(3, examId);
            rs = pre.executeQuery();
            while (rs.next()) {
                int takeExamId = rs.getInt("TakeExamId");
                int studentAccountId = rs.getInt("StudentAccountId");
                String status = rs.getString("Status");
                double score = rs.getDouble("Score");
                String startDate = rs.getString("StartDate");
                String endDate = rs.getString("EndDate");

                best = new TakeExam(takeExamId, studentAccountId, examId, status, score, startDate, endDate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOTakeExam.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return best;
    }

    public int isTakingExam(int userId, int ExamId) {
        int n = -1;
        PreparedStatement pre = null;
        ResultSet rs = null;
        String sql = "  select * from TakeExam where status=? and StudentAccountId = ? and ExamId = ?";
        try {
            pre = connection.prepareStatement(sql);
            pre.setInt(1, 1);
            pre.setInt(2, userId);
            pre.setInt(3, ExamId);
            rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOTakeExam.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return n;
    }

    public int getTimeDone(int userId, int ExamId) {
        int n = 0;
        PreparedStatement pre = null;
        ResultSet rs = null;
        String sql = "    select count(TakeExamId) as time from TakeExam where StudentAccountId = ? and ExamId = ?";
        try {
            pre = connection.prepareStatement(sql);
            pre.setInt(1, userId);
            pre.setInt(2, ExamId);
            rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOTakeExam.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return n;
    }

    public void completeAttempt(int takeExamId, Map<questionExam, questionExamAnswer> userAnswers, double score) {
        PreparedStatement pre = null;
        String sql = "UPDATE [dbo].[TakeExam]\n"
                + "   SET [Status] = 2\n"
                + "      ,[Score] = ?\n"
                + "      ,[EndDate] = GETDATE()\n"
                + " WHERE TakeExamId = ?";
        try {
            pre = connection.prepareStatement(sql);
            pre.setDouble(1, score);
            pre.setInt(2, takeExamId);
            pre.executeUpdate();
            for (Map.Entry<questionExam, questionExamAnswer> entry : userAnswers.entrySet()) {
                questionExam key = entry.getKey();
                questionExamAnswer val = entry.getValue();
                if (val != null) {
                    sql = "INSERT INTO [dbo].[TakeAnswer]\n"
                            + "           ([TakeExamId]\n"
                            + "           ,[QuesId]\n"
                            + "           ,[AnswerId])\n"
                            + "     VALUES (?, ?, ?)";
                    pre = connection.prepareStatement(sql);
                    pre.setInt(1, takeExamId);
                    pre.setInt(2, key.getQuesId());
                    pre.setInt(3, val.getAnswerId());
                    pre.executeUpdate();
                }
            }
//            for (Map.Entry<QuestionExam, Integer> entry : userAnswers.entrySet()) {
//                questionExam key = entry.getKey();
//                int val = entry.getValue();
//                if (val != -1) {
//                    sql = " insert into TakeAnswer (TakeExamId, QuesId, AnswerId)\n"
//                            + " values(?,?,?)";
//                    pre = connection.prepareStatement(sql);
//                    pre.setInt(1, takeExamId);
//                    pre.setInt(2, key.getQuesId());
//                    pre.setInt(3, val);
//                    pre.executeUpdate();
//                }
//            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DAOTakeExam dao = new DAOTakeExam();
//        System.out.println(dao.getTakeExamByExamId(1));

//        ArrayList<TakeExam> result = dao.getTakeExamByExamId(5);
//        for (TakeExam te : result) {
//            System.out.println(te);
//        }
        TakeExam best = dao.getTakeExamByTakeExamIdObj(4);
        System.out.println(best);
    }
}
