/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Exam;
import Entity.questionExam;
import Entity.questionExamAnswer;
import Model.DAOExam;
import Model.DAOQuestionExam;
import Model.DAOQuestionExamAnswer;
import Utils.MyApplicationConstants;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

/**
 *
 * @author ACER
 */
@WebServlet(name = "EditExamController", urlPatterns = {"/EditExamURL"})
public class EditExamController extends HttpServlet {

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
            DAOExam dao = new DAOExam();
            DAOQuestionExam questionDAO = new DAOQuestionExam();
            DAOQuestionExamAnswer answerDAO = new DAOQuestionExamAnswer();
            String service = request.getParameter("go");
            if (service == null) {
                service = "showEditExam";
            }
            if (service.equals("showEditExam")) {

                int examId = Integer.parseInt(request.getParameter("examId"));

                Exam exam = dao.getExamById(examId);
                ArrayList<questionExam> Ques = questionDAO.getQues(examId);
                HashMap<Integer, ArrayList<questionExamAnswer>> QuesAnswers = answerDAO.getAnswerMap(examId);

                request.setAttribute("exam", exam);
                request.setAttribute("questions", Ques);
                request.setAttribute("questionAnswer", QuesAnswers);
                url=siteMaps.getProperty(MyApplicationConstants.TeacherExamFeature.EDIT_EXAM_PAGE);
                request.getRequestDispatcher(url).forward(request, response);
            }
            if (service.equals("saveExam")) {
                String action = request.getParameter("action");
                if (action != null) {
                    int examId = Integer.parseInt(request.getParameter("examId"));
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

                    Exam exam = new Exam(examId, title, summary, score, startDate, endDate, timer, takingTime, permission);
                    dao.updateExam(exam);
                    // lay cau hoi trong set
                    int index1 = questionDAO.getTotalQuestionInSet(examId);
                    for (int i = 0; i < index1; i++) {
                        String question = request.getParameter("ExamQuestionDetail" + i);
                        int questionId = Integer.parseInt(request.getParameter("QuestionId" + i));

                        questionExam ques = new questionExam(examId, questionId, question, score / index1);
                        questionDAO.updateQuetionExam(ques);

                        int index2 = answerDAO.getTotalAnswerOfQuestion(questionId);
                        int correctCount = 0;
                        for (int j = 0; j < index2; j++) {
                            String isCorrect = request.getParameter("IsTrueAnswer" + i + "-" + j);
                            if ("true".equals(isCorrect)) {
                                correctCount++;
                            }
                        }
                        float percentage = correctCount > 0 ? 100.0f / correctCount : 0.0f;

                        for (int j = 0; j < index2; j++) {
                            String answer = request.getParameter("AnswerDetail" + i + "-" + j);
                            String isCorrect = request.getParameter("IsTrueAnswer" + i + "-" + j);
                            String answerId = request.getParameter("AnswerId" + i + "-" + j);

                            boolean correct = "true".equals(isCorrect);
                            int id = Integer.parseInt(answerId);

                            float percent = correct ? percentage : 0.0f;

                            questionExamAnswer quesAnswer = new questionExamAnswer(id, questionId, answer, correct, percent);
                            answerDAO.updateQuetionExamAnswer(quesAnswer);
                        }
                    }
                    url=siteMaps.getProperty(MyApplicationConstants.TeacherExamFeature.EDIT_EXAM_ACTION);
                    response.sendRedirect(url + examId);
                }
            }
            if (service.equals("deleteExam")) {
                String examId = request.getParameter("examId");
                dao.deleteExam(examId);
                url=siteMaps.getProperty(MyApplicationConstants.ApplicationScope.QUESTION_SET_ACTION);
                response.sendRedirect(url);
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
