/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author phamg
 */
import Entity.NormalQuestionAnswer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAONormalQuestionAnswer extends DBConnect {

    public int insert(NormalQuestionAnswer obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[NormalQuestionAnswer] "
                + "([QuesId], [Content], [Correct], [Percent]) "
                + "VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, obj.getQuesId());
            pre.setString(2, obj.getContent());
            pre.setBoolean(3, obj.isCorrect());
            pre.setDouble(4, obj.getPercent());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAONormalQuestionAnswer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int insertDefaultAnswer(int quesId) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[NormalQuestionAnswer] "
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

    public int update(NormalQuestionAnswer obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[NormalQuestionAnswer] "
                + "SET [QuesId] = ?, [Content] = ?, [Correct] = ?, [Percent] = ? "
                + "WHERE [AnswerId] = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, obj.getQuesId());
            pre.setString(2, obj.getContent());
            pre.setBoolean(3, obj.isCorrect());
            pre.setDouble(4, obj.getPercent());
            pre.setInt(5, obj.getAnswerId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAONormalQuestionAnswer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int delete(int answerId) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[NormalQuestionAnswer] WHERE [AnswerId] = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, answerId);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAONormalQuestionAnswer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public ArrayList<NormalQuestionAnswer> getData(String sql) {
        ArrayList<NormalQuestionAnswer> normalQuestionAnswers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int answerId = resultSet.getInt("AnswerId");
                int quesId = resultSet.getInt("QuesId");
                String content = resultSet.getString("Content");
                boolean correct = resultSet.getBoolean("Correct");
                double percent = resultSet.getDouble("Percent");

                NormalQuestionAnswer normalQuestionAnswer = new NormalQuestionAnswer(answerId, quesId, content, correct, percent);
                normalQuestionAnswers.add(normalQuestionAnswer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAONormalQuestionAnswer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return normalQuestionAnswers;
    }

    public int getTotalAnswerOfQuestion(int quesId) {
        int n = 0;
        String sql = "SELECT COUNT(*) as totalAnswer\n"
                + "FROM NormalQuestionAnswer\n"
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

    public static void main(String[] args) {
        NormalQuestionAnswer nqa = new NormalQuestionAnswer(1, 1, "Sample Content", true, 0);
        DAONormalQuestionAnswer dao = new DAONormalQuestionAnswer();

       // dao.update(nqa);
       
        System.out.println(dao.getTotalAnswerOfQuestion(1));
    }
}
