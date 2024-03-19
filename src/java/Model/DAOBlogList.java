/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.BlogList;
import Entity.QuestionSet;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class DAOBlogList extends DBConnect {

    public List<BlogList> getAllBlogList() {
        List<BlogList> qs = new ArrayList<>();
        try {
            String sql = "SELECT SubjectId, Title, Username, SetVote FROM QuestionSet AS q "
                    + "JOIN [User] AS u ON q.UserAccountId = u.AccountId";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                BlogList qts = new BlogList();
                qts.setSubjectId(rs.getInt("SubjectId"));
                qts.setTitle(rs.getString("Title"));
                qts.setUsername(rs.getString("Username"));
                qts.setSetVote(rs.getInt("SetVote"));

                qs.add(qts);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qs;
    }
public int getTotal() {
    try {
        String sql = "SELECT COUNT(q.SetId) FROM QuestionSet q JOIN [User] u ON q.UserAccountId = u.AccountId";
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

     public List<BlogList> getAllBlogListWithPagging(int page, int PAGE_SIZE) {
        List<BlogList> list = new ArrayList<>();
        try {
            String sql = "SELECT SubjectId,SetId,Title, Username, SetVote FROM QuestionSet AS q "
                    + "JOIN [User] AS u ON q.UserAccountId = u.AccountId "
                    + "ORDER BY SetId OFFSET (?-1)*? ROWS FETCH NEXT ? ROWS ONLY";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, page);
            stm.setInt(2, PAGE_SIZE);
            stm.setInt(3, PAGE_SIZE);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                BlogList blog = new BlogList();
                blog.setSubjectId(rs.getInt(1));
                blog.setSetId(rs.getInt(2));
                blog.setTitle(rs.getString(3));
                blog.setUsername(rs.getString(4));
                blog.setSetVote(rs.getInt(5));

                list.add(blog);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public int countBlog(String keyword) {
    try {
        String sql = "SELECT COUNT(*) FROM QuestionSet AS q " +
                     "JOIN [User] AS u ON q.UserAccountId = u.AccountId " +
                     "WHERE Title LIKE ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, "%" + keyword + "%");
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}

public List<BlogList> searchBlogWithPagination(String keyword, int page, int pageSize) {
    List<BlogList> list = new ArrayList<>();
    try {
        String sql = "SELECT  SubjectId, SetId,Title, Username, SetVote FROM QuestionSet AS q " +
                     "JOIN [User] AS u ON q.UserAccountId = u.AccountId " +
                     "WHERE Title LIKE ? " +
                     "ORDER BY SetId OFFSET (?-1)*? ROWS FETCH NEXT ? ROWS ONLY";

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, "%" + keyword + "%");
        stm.setInt(2, page);
        stm.setInt(3, pageSize);
        stm.setInt(4, pageSize);

        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            BlogList blog = new BlogList();
                  blog.setSubjectId(rs.getInt(1));
                  blog.setSetId(rs.getInt(2));
                blog.setTitle(rs.getString(3));
                blog.setUsername(rs.getString(4));
                blog.setSetVote(rs.getInt(5));

            list.add(blog);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return list;
}
 public int getTotalBySubjectId(int subjectId) {
    try {
        String sql = "SELECT COUNT(q.SubjectId) FROM QuestionSet q "
                   + "JOIN [User] u ON q.UserAccountId = u.AccountId "
                   + "WHERE q.SubjectId = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, subjectId);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            return rs.getInt(1);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return 0;
}
 
  public List<BlogList> getBlogListBySubjectIdWithPagination(int subjectId, int page, int pageSize) {
    List<BlogList> list = new ArrayList<>();
    try {
        String sql = "SELECT SubjectId,SetId, Title, Username, SetVote FROM QuestionSet AS q "
                + "JOIN [User] AS u ON q.UserAccountId = u.AccountId "
                + "WHERE SubjectId = ? "
                + "ORDER BY SetId OFFSET (?-1)*? ROWS FETCH NEXT ? ROWS ONLY";

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, subjectId);
        stm.setInt(2, page);
        stm.setInt(3, pageSize);
        stm.setInt(4, pageSize);

        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            BlogList blog = new BlogList();
            blog.setSubjectId(rs.getInt(1));
            blog.setSetId(rs.getInt(2));
            blog.setTitle(rs.getString(3));
            blog.setUsername(rs.getString(4));
            blog.setSetVote(rs.getInt(5));

            list.add(blog);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return list;
}


   public static void main(String[] args) {
    DAOBlogList dao = new DAOBlogList();

    // Test phân trang với PAGE_SIZE = 5, page = 1
    int PAGE_SIZE = 5;
    int page = 1;
    List<BlogList> result = dao.getAllBlogListWithPagging(page, PAGE_SIZE);
    
    // In ra thông tin từ kết quả
    for (BlogList blog : result) {
        System.out.println(blog);
    }
    
    
    List<BlogList> ls = dao.getAllBlogList();
       for (BlogList l : ls) {
           System.out.println(l);
       }
}

}