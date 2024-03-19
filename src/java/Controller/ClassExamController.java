/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Exam;
import Entity.TakeExam;
import Entity.User;
import Model.DAOExam;
import Model.DAOQuestionExam;
import Model.DAOTakeExam;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author hieul
 */
@WebServlet(name = "ClassExamController", urlPatterns = {"/ClassExamURL"})
public class ClassExamController extends HttpServlet {

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

        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAPS");
        String url = "";

        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            int examId = Integer.parseInt(request.getParameter("examId"));
            DAOExam daoExam = new DAOExam();
            DAOTakeExam daoTakeExam = new DAOTakeExam();
            DAOQuestionExam daoQuestion = new DAOQuestionExam();
            Exam exam = daoExam.getExamById(examId);

            User user = (User) request.getSession().getAttribute("acc");
            ArrayList<TakeExam> takeExams = daoTakeExam.getTakeExamByExamId(examId);
            TakeExam best = daoTakeExam.bestAttempt(user.getAccountId(), examId);
            int questionCount = daoQuestion.getTotalQuestionInSet(examId);
            
            int limit = exam.getTakingTimes();
            int time = daoTakeExam.getTimeDone(user.getAccountId(), examId);
            request.setAttribute("bestAttempt", best);
            request.setAttribute("limit", limit);
            request.setAttribute("time", time);
            request.setAttribute("exam", exam);
            request.setAttribute("takeExams", takeExams);
            request.setAttribute("questionCount", questionCount);

            request.getRequestDispatcher("/exam/exam.jsp").forward(request, response);
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
