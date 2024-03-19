/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Exam;
import Entity.questionExam;
import Entity.questionExamAnswer;
import Entity.TakeExam;
import Model.DAOExam;
import Model.DAOQuestionExam;
import Model.DAOQuestionExamAnswer;
import Model.DAOTakeAnswer;
import Model.DAOTakeExam;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hieul
 */
@WebServlet(name = "ClassExamReviewController", urlPatterns = {"/ClassExamReviewURL"})
public class ClassExamReviewController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            int takeExamId = Integer.parseInt(request.getParameter("takeExamId"));

            DAOExam daoExam = new DAOExam();
            DAOTakeExam daoTakeExam = new DAOTakeExam();
            DAOTakeAnswer daoTakeAnswer = new DAOTakeAnswer();
            DAOQuestionExam daoQuestion = new DAOQuestionExam();
            DAOQuestionExamAnswer daoQuestionExamAnswer = new DAOQuestionExamAnswer();

            TakeExam takeExam = daoTakeExam.getTakeExamByTakeExamIdObj(takeExamId);
            int examId = takeExam.getExamId();
            Exam exam = daoExam.getExamById(examId);
            
            List<questionExam> questions = daoQuestion.getQues(examId);
            HashMap<Integer, ArrayList<questionExamAnswer>> answers = daoQuestionExamAnswer.getAnswerMap(examId);
            Map<questionExam, questionExamAnswer> userAnswers = daoTakeAnswer.getUserAnswers(questions, takeExamId);

            request.setAttribute("status", 2);
            request.setAttribute("exam", exam);
            request.setAttribute("takeExam", takeExam);
            request.setAttribute("questions", questions);
            request.setAttribute("answers", answers);
            request.setAttribute("questionCount", questions.size());
            request.setAttribute("userAnswers", userAnswers);
            String url="/exam/reviewExam.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
