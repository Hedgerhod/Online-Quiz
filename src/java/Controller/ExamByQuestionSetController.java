/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Exam;
import Entity.NormalQuestion;
import Entity.NormalQuestionAnswer;
import Entity.QuestionSet;
import Entity.User;
import Entity.questionExam;
import Entity.questionExamAnswer;
import Model.DAOExam;
import Model.DAOQuestionExam;
import Model.DAOQuestionExamAnswer;
import Model.DAOQuestionSet;
import Utils.ParseUtils;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
@WebServlet(name = "ExamByQuestionSetController", urlPatterns = {"/ExamByQuestionSetURL"})
public class ExamByQuestionSetController extends HttpServlet {

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
            String service = request.getParameter("go");
            DAOQuestionSet daoQuestionSet = new DAOQuestionSet();
            DAOExam daoExam = new DAOExam();

            if (service == null) {
                service = "showQuestionBank";
            }
            if (service.equals("showQuestionBank")) {
                User user = (User) request.getSession().getAttribute("acc");
                ArrayList<QuestionSet> allQuesSet = daoQuestionSet.getData("select * from QuestionSet where UserAccountId =" + user.getAccountId());
                request.setAttribute("QuestionSetList", allQuesSet);
                request.getRequestDispatcher("exam/createdExamByBank.jsp").forward(request, response);
            }
            if (service.equals("createdExam")) {
                DAOQuestionExam daoQuestionExam = new DAOQuestionExam();
                DAOQuestionExamAnswer daoExamAnswer = new DAOQuestionExamAnswer();

                User user = (User) request.getSession().getAttribute("acc");
                int teacherId = user.getAccountId();
                int classId = ParseUtils.parseIntWithDefault(request.getParameter("classId"), 1);
                String title = request.getParameter("ExamTitle");
                String summary = request.getParameter("ExamSummary");
                int hour = Integer.parseInt(request.getParameter("hour"));
                int minute = Integer.parseInt(request.getParameter("minute"));
                int second = Integer.parseInt(request.getParameter("second"));
                int timer = hour * 3600 + minute * 60 + second;
                String startDate = request.getParameter("ExamStart");
                String endDate = request.getParameter("ExamEnd");
                Double score = Double.parseDouble(request.getParameter("ExamScore"));
                int takingTime = Integer.parseInt(request.getParameter("ExamTakingTimers"));
                boolean permission = Boolean.parseBoolean(request.getParameter("permission"));
                try {
                    daoExam.createDefaultExam(classId, teacherId);
                    Exam newExam = daoExam.getNewExam();
                    Exam exam = new Exam(newExam.getExamId(), title, summary, score, startDate, endDate, timer, takingTime, permission);
                    daoExam.updateExam(exam);
                    String[] selectedQuetionSet = request.getParameterValues("selectedQuetionSet");
                    if (selectedQuetionSet != null) {
                        for (String option : selectedQuetionSet) {
                            // Xử lý từng giá trị checkbox đã được chọn
                            ArrayList<NormalQuestion> question = daoQuestionSet.getQues(Integer.parseInt(option));
                            HashMap<Integer, ArrayList<NormalQuestionAnswer>> answerHashmap = daoQuestionSet.getAnswerMap(Integer.parseInt(option));
                            if (answerHashmap.isEmpty()) {
                                System.out.println("hashmap khong co gi ca");
                            } else {
                                for (int key : answerHashmap.keySet()) {
                                    System.out.println(key);
                                }
                            }
                            double scoreQuestion = score / question.size();
                            for (NormalQuestion normalQuestion : question) {
                                questionExam ques = new questionExam(newExam.getExamId(), normalQuestion.getContent(), scoreQuestion);
                                daoQuestionExam.insertQuetionExam(ques);
                                int questionExamId = daoQuestionExam.getLast();
                                ArrayList<NormalQuestionAnswer> answer = answerHashmap.get(normalQuestion.getQuesId());

                                int correctCount = 0;
                                if (answer != null) {
                                    System.out.println("so dap an cua cau hoi la " + answer.size());
                                    for (NormalQuestionAnswer normalQuestionAnswer : answer) {
                                        if (normalQuestionAnswer.isCorrect()) {
                                            correctCount++;
                                        }
                                    }

                                    for (NormalQuestionAnswer normalQuestionAnswer : answer) {
                                        float percentage = correctCount > 0 ? 100.0f / correctCount : 0.0f;
                                        boolean correct = normalQuestionAnswer.isCorrect();
                                        float percent = correct ? percentage : 0.0f;
                                        questionExamAnswer ExamAnswer = new questionExamAnswer(questionExamId, normalQuestionAnswer.getContent(), correct, percent);
                                        daoExamAnswer.insertQuetionExamAnswer(ExamAnswer);
                                    }
                                }
                            }
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ExamByQuestionSetController.class.getName()).log(Level.SEVERE, null, ex);
                }

                request.getRequestDispatcher("exam/createdExamByBank.jsp").forward(request, response);

            }
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
