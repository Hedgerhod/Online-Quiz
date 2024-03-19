/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Exam;
import Entity.questionExam;
import Entity.questionExamAnswer;
import Entity.TakeExam;
import Entity.User;
import Model.DAOExam;
import Model.DAOQuestionExam;
import Model.DAOQuestionExamAnswer;
import Model.DAOTakeExam;
import Utils.GradeUtils;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hieul
 */
@WebServlet(name = "CompleteAttempt", urlPatterns = {"/CompleteAttemptURL"})
public class CompleteAttempt extends HttpServlet {

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
            DAOQuestionExam daoQuestion = new DAOQuestionExam();
            DAOQuestionExamAnswer daoAnswer = new DAOQuestionExamAnswer();
            DAOTakeExam daoTakeExam = new DAOTakeExam();

            int examId = Integer.parseInt(request.getParameter("examId"));

            //get current user
            ArrayList<questionExam> questionList = daoQuestion.getData("select * from QuestionExam where examId=" + examId);
            //       ArrayList<QuestionExamAnswer> answerList = daoAnswer.getData("select * from questionExamAnswer");
            HashMap<Integer, ArrayList<questionExamAnswer>> questionAnswer = daoAnswer.getAnswerMap(examId);

            int takeExamId = Integer.parseInt(request.getParameter("takeExamId"));
            Map<questionExam, questionExamAnswer> useranswer = new HashMap<>();
            for (questionExam q : questionList) {
                String userAnswer = request.getParameter(Integer.toString(q.getQuesId()));
                if (userAnswer == null) {
                    useranswer.put(q, null);
                } else {
                    int answerId = Integer.parseInt(userAnswer);
                    for (ArrayList<questionExamAnswer> answers : questionAnswer.values()) {
                        for (questionExamAnswer a : answers) {
                            if (a.getAnswerId() == answerId && a.getQuesId() == q.getQuesId()) {
                                useranswer.put(q, a);
                                break;
                            }
                        }
                    }
                }
            }
            double score = GradeUtils.grade(questionList, useranswer);
            daoTakeExam.completeAttempt(takeExamId, useranswer, score);
            response.sendRedirect("ClassExamReviewURL?takeExamId=" + takeExamId);
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
