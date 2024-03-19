package Model;

import Entity.questionExam;
import Entity.questionExamAnswer;
import Entity.TakeAnswer;
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

public class DAOTakeAnswer extends DBConnect {

    public int insertTakeAnswer(TakeAnswer obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[TakeAnswer]\n"
                + "           ([TakeExamId]\n"
                + "           ,[QuesId]\n"
                + "           ,[AnswerId])\n"
                + "     VALUES(?,?,?)";

        System.out.println(sql);
        try {
            PreparedStatement pre = connection.prepareCall(sql);
            pre.setInt(1, obj.getTakeExamId());
            pre.setInt(2, obj.getQuesId());
            pre.setInt(3, obj.getAnswerId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTakeAnswer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateTakeAnswer(TakeAnswer obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[TakeAnswer]\n"
                + "   SET [TakeExamId] = ?\n"
                + "      ,[QuesId] = ?\n"
                + "      ,[AnswerId] = ?\n"
                + " WHERE [TakeAnswerId] = ?";
        try {
            PreparedStatement pre = connection.prepareCall(sql);
            pre.setInt(1, obj.getTakeExamId());
            pre.setInt(2, obj.getQuesId());
            pre.setInt(3, obj.getAnswerId());
            pre.setInt(4, obj.getTakeAnswerId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTakeAnswer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteTakeAnswer(String id) {
        int n = 0;
        String sql = "DELETE FROM [TakeAnswer] WHERE TakeAnswerId = '" + id + "'";
        try {
            Statement state = connection.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOTakeAnswer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public ArrayList<TakeAnswer> getData(String sql) {
        ArrayList<TakeAnswer> List = new ArrayList<>();
        Statement state;
        try {
            state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int TakeAnswerId = rs.getInt(1);
                int TakeExamId = rs.getInt(2);
                int QuesId = rs.getInt(3);
                int AnswerId = rs.getInt(4);
                TakeAnswer obj = new TakeAnswer(TakeAnswerId, TakeExamId, QuesId, AnswerId);

                List.add(obj);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return List;
    }

    public Map<questionExam, questionExamAnswer> getUserAnswers(List<questionExam> questionList, int takeExamId) {
        Map<questionExam, questionExamAnswer> userAnswers = new HashMap<>();
        PreparedStatement pre = null;
        ResultSet rs = null;
        String sql = "  select QuestionExamAnswer.* from TakeAnswer \n"
                + "  join QuestionExamAnswer on TakeAnswer.AnswerId = QuestionExamAnswer.AnswerId\n"
                + "  where TakeAnswer.QuesId = ? and TakeExamId = ?";
        for (questionExam q : questionList) {
            try {
                pre = connection.prepareStatement(sql);
                pre.setInt(1, q.getQuesId());
                pre.setInt(2, takeExamId);
                rs = pre.executeQuery();
                if (rs.next()) {
                    int answerId = rs.getInt(1);
                    int QuesId = rs.getInt(2);
                    String content = rs.getString(3);
                    boolean correct = rs.getBoolean(4);
                    double percent = rs.getDouble(5);
                    questionExamAnswer obj = new questionExamAnswer(answerId, QuesId, content, correct, percent);
                    userAnswers.put(q, obj);
                } else {
                    userAnswers.put(q, null);
                }
                rs.close();
                pre.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAOTakeAnswer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return userAnswers;
    }

    public static void main(String[] args) {
        DAOTakeAnswer dao = new DAOTakeAnswer();
        DAOQuestionExam dao1 = new DAOQuestionExam();
        List<questionExam> questionList = dao1.getQues(5);
        Map<questionExam, questionExamAnswer> getUserAnswers = dao.getUserAnswers(questionList, 3);
        for (Map.Entry<questionExam, questionExamAnswer> entry : getUserAnswers.entrySet()) {
            questionExam question = entry.getKey();
            questionExamAnswer userAnswer = entry.getValue();

            System.out.println("Question ID: " + question.getQuesId());
            System.out.println("Question Content: " + question.getContent());

            if (userAnswer != null) {
                System.out.println("User's Answer ID: " + userAnswer.getAnswerId());
                System.out.println("User's Answer Content: " + userAnswer.getContent());
            } else {
                System.out.println("User did not answer this question.");
            }

            System.out.println("-------------------------");
        }
    }
}
