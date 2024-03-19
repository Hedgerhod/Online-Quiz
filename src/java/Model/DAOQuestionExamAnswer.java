package Model;

import Entity.questionExamAnswer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOQuestionExamAnswer extends DBConnect {

    public int insertQuetionExamAnswer(questionExamAnswer obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[QuestionExamAnswer]\n"
                + "           ([QuesId]\n"
                + "           ,[Content]\n"
                + "           ,[Correct]\n"
                + "           ,[Percent])\n"
                + "     VALUES(?,?,?,?)";

        System.out.println(sql);
        try {
            PreparedStatement pre = connection.prepareCall(sql);
            pre.setInt(1, obj.getQuesId());
            pre.setString(2, obj.getContent());
            pre.setBoolean(3, obj.isCorrect());
            pre.setDouble(4, obj.getPercent());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuestionExamAnswer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateQuetionExamAnswer(questionExamAnswer obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[QuestionExamAnswer]\n"
                + "   SET [QuesId] = ?\n"
                + "      ,[Content] = ?\n"
                + "      ,[Correct] = ?\n"
                + "      ,[Percent] = ?\n"
                + " WHERE [AnswerId] = ?";
        try {
            PreparedStatement pre = connection.prepareCall(sql);
            pre.setInt(1, obj.getQuesId());
            pre.setString(2, obj.getContent());
            pre.setBoolean(3, obj.isCorrect());
            pre.setDouble(4, obj.getPercent());
            pre.setInt(5, obj.getAnswerId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuestionExamAnswer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteQuetionExamAnswer(String id) {
        int n = 0;
        String sql = "DELETE FROM [QuestionExamAnswer] WHERE AnswerId = '" + id + "'";
        try {
            Statement state = connection.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuestionExamAnswer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public ArrayList<questionExamAnswer> getData(String sql) {
        ArrayList<questionExamAnswer> List = new ArrayList<>();
        Statement state;
        try {
            state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int AnswerId = rs.getInt(1);
                int QuesId = rs.getInt(2);
                String Content = rs.getString(3);
                boolean Correct = rs.getBoolean(4);
                double Percent = rs.getDouble(5);
                questionExamAnswer obj = new questionExamAnswer(AnswerId, QuesId, Content, Correct, Percent);

                List.add(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return List;
    }

    public int delete(int answerId) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[QuestionExamAnswer] WHERE [AnswerId] = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, answerId);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAONormalQuestionAnswer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int insertDefaultAnswer(int quesId) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[QuestionExamAnswer] "
                + "([QuesId], [Content], [Correct], [Percent]) "
                + "VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, quesId);
            pre.setString(2, "Default Answer");
            pre.setBoolean(3, false);
            pre.setDouble(4, 0);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAONormalQuestionAnswer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int getTotalAnswerOfQuestion(int quesId) {
        int n = 0;
        String sql = "SELECT COUNT(*) as totalAnswer\n"
                + "FROM QuestionExamAnswer\n"
                + "where QuesId = ?\n"
                + "group by QuesId";
        try ( PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, quesId);
            try ( ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    n = rs.getInt("totalAnswer");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return n;
    }

    public HashMap<Integer, ArrayList<questionExamAnswer>> getAnswerMap(int examId) {
        HashMap<Integer, ArrayList<questionExamAnswer>> map = new HashMap<>();
        String sql = "select QuestionExamAnswer.* \n"
                + "from QuestionExamAnswer join QuestionExam on QuestionExamAnswer.QuesId = QuestionExam.QuesId\n"
                + "where QuestionExam.ExamId = ?";

        try ( PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, examId);
            try ( ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    int answerId = rs.getInt("AnswerId");
                    int quesId = rs.getInt("QuesId");
                    String quesContent = rs.getString("Content");
                    boolean correct = rs.getBoolean("Correct");
                    double percent = rs.getDouble("Percent");

                    questionExamAnswer answer = new questionExamAnswer(answerId, quesId, quesContent, correct, percent);

                    // Kiểm tra quesId đã tồn tại trong danh sách chưa
                    map.computeIfAbsent(quesId, k -> new ArrayList<>()).add(answer);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String[] args) {
        DAOQuestionExamAnswer dao = new DAOQuestionExamAnswer();
        HashMap<Integer, ArrayList<questionExamAnswer>> answerMap = dao.getAnswerMap(5);

        // print the results
        for (Map.Entry<Integer, ArrayList<questionExamAnswer>> entry : answerMap.entrySet()) {
            System.out.println("Question ID: " + entry.getKey());
            for (questionExamAnswer answer : entry.getValue()) {
                System.out.println("Answer ID: " + answer.getAnswerId());
                System.out.println("Content: " + answer.getContent());
                System.out.println("Correct: " + answer.isCorrect());
                System.out.println("Percent: " + answer.getPercent());
                System.out.println("---");
            }
            System.out.println("==========");
        }

    }
}
