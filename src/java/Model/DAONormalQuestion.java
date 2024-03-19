/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author phamg
 */
import Entity.NormalQuestion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAONormalQuestion extends DBConnect {

    public int insert(NormalQuestion obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[NormalQuestion]\n"
                + "           ([Content]\n"
                + "           ,[SetId])\n"
                + "     VALUES\n"
                + "           (?, ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, obj.getContent());
            pre.setInt(2, obj.getSetId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAONormalQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int insertDefaultQuestion(int setId) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[NormalQuestion]\n"
                + "           ([Content]\n"
                + "           ,[SetId])\n"
                + "     VALUES\n"
                + "           (?, ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, "Defaul Question");
            pre.setInt(2, setId);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAONormalQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int update(NormalQuestion obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[NormalQuestion]\n"
                + "   SET [Content] = ?\n"
                + "      ,[SetId] = ?\n"
                + " WHERE NormalQuestion.QuesId = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, obj.getContent());
            pre.setInt(2, obj.getSetId());
            pre.setInt(3, obj.getQuesId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAONormalQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int delete(int quesId) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[NormalQuestion] WHERE [QuesId] = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, quesId);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAONormalQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public ArrayList<NormalQuestion> getData(String sql) {
        ArrayList<NormalQuestion> normalQuestions = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int quesId = resultSet.getInt("QuesId");
                String content = resultSet.getString("Content");
                int setId = resultSet.getInt("SetId");
                NormalQuestion normalQuestion = new NormalQuestion(quesId, content, setId);
                normalQuestions.add(normalQuestion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAONormalQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return normalQuestions;
    }

    public int getTotalQuestionInSet(int setId) {
        int n = 0;
        String sql = "SELECT COUNT(*) as totalQuestion FROM NormalQuestion WHERE SetId = ? group by SetId";
        try ( PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, setId);
            try ( ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    n = rs.getInt("totalQuestion");
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
        DAONormalQuestion dao = new DAONormalQuestion();
//
//        dao.insert(normalQuestion);
//        NormalQuestion updatedNormalQuestion = new NormalQuestion(1, "Updated Content");
//        dao.update(updatedNormalQuestion);
//        System.out.println(dao.getTotalQuestionInSet(1));

    }
}
