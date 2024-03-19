/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Entity.NormalQuestion;
import Entity.NormalQuestionAnswer;
import Entity.QuestionSet;
import Model.DAOQuestionSet;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
@WebServlet(name="AnsController", urlPatterns={"/AnsURL"})
public class AnsController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       DAOQuestionSet dao = new DAOQuestionSet();
        ArrayList<QuestionSet> allQuesSet = dao.getData("select * from QuestionSet");
        int setId = Integer.parseInt(request.getParameter("SetId"));
        ArrayList<NormalQuestion> Ques = dao.getQues(setId);
        ArrayList<ArrayList<NormalQuestionAnswer>> QuesAnswers = dao.getAnswer(setId);
        int totalQuestions = Ques.size();
        ArrayList<String> selectedAnswers = new ArrayList<>();
        int correctCount = 0; // Biến đếm số câu trả lời đúng

        for (int i = 0; i < Ques.size(); i++) {
            String paramName = "question" + (i + 1);
            String userAnswer = request.getParameter(paramName);
            selectedAnswers.add(userAnswer); // Lưu đáp án người dùng chọn
            // Lấy danh sách đáp án cho câu hỏi hiện tại
            ArrayList<NormalQuestionAnswer> answers = QuesAnswers.get(i);
            // So sánh đáp án người dùng chọn với các đáp án có sẵn
            for (NormalQuestionAnswer answer : answers) {
                if (answer.getContent().equals(userAnswer) && answer.isCorrect()) {
                    correctCount++; // Nếu trùng khớp và là đáp án đúng, tăng biến đếm lên 1
                    break; // Thoát khỏi vòng lặp khi tìm thấy đáp án đúng
                }
            }
        }

        HttpSession session = request.getSession();
        session.setAttribute("selectedAnswers", selectedAnswers);
        session.setAttribute("correctCount", correctCount);
        session.setAttribute("total", totalQuestions);
        request.setAttribute("data", allQuesSet);

        request.setAttribute("question", Ques);
        request.setAttribute("content", QuesAnswers);
        request.getRequestDispatcher("Question/answerQuiz.jsp").forward(request, response);
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
