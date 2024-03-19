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
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author hieul
 */
@WebServlet(name = "AttemptExamClassController", urlPatterns = {"/AttemptExamClassURL"})
public class AttemptClassExamController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//
//        ServletContext context = this.getServletContext();
//        Properties siteMaps = (Properties) context.getAttribute("SITE_MAPS");
//        String url = "";
//        try ( PrintWriter out = response.getWriter()) {
//            String method = request.getMethod();
//            DAOExam daoExam = new DAOExam();
//            DAOQuestionExam daoQuestion = new DAOQuestionExam();
//            DAOQuestionExamAnswer daoAnswer = new DAOQuestionExamAnswer();
//            DAOTakeExam daoTakeExam = new DAOTakeExam();
//
//            int examId = Integer.parseInt(request.getParameter("examId"));
//            //   int takeExamId = Integer.parseInt(request.getParameter("takeExamId"));
//            Exam exam = daoExam.getExamById(examId);
//
//            //get current user
//            User user = (User) request.getSession().getAttribute("acc");
//            int takeExamId = daoTakeExam.takeExam(user.getAccountId(), examId);
//            TakeExam takeExam = daoTakeExam.getTakeExamByExamIdObj(examId);
//            ArrayList<QuestionExam> questionList = daoQuestion.getData("select * from questionExam where examId=" + examId);
//            //       ArrayList<QuestionExamAnswer> answerList = daoAnswer.getData("select * from questionExamAnswer");
//            HashMap<Integer, ArrayList<QuestionExamAnswer>> questionAnswer = daoAnswer.getAnswerMap(examId);
//
//            int examTimeInSeconds = exam.getTimer();
//            // Chuyển đổi giây thành mili giây
//            long examTimeInMillis = examTimeInSeconds * 1000;
//            // Tạo đối tượng Time từ mili giây
//            //Time examTime = new Time(examTimeInMillis);
//
//            Timestamp startTime = Timestamp.valueOf(takeExam.getStartDate());
//            Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis()); //lay thoi gian hien tai
//            startTime.setTime(startTime.getTime() + examTimeInMillis);
//            long differenceInMillis = startTime.getTime() - currentTimeStamp.getTime();
//            long differenceInSeconds = TimeUnit.MILLISECONDS.toSeconds(differenceInMillis);
//
//            request.setAttribute("status", 1);
//            request.setAttribute("questionCount", questionList.size());
//            request.setAttribute("takeExamId", takeExamId);
//            request.setAttribute("remainingTime", differenceInSeconds);
//            request.setAttribute("questions", questionList);
//            request.setAttribute("exam", exam);
//            request.setAttribute("answers", questionAnswer);
////            url = "exam/attemptExam.jsp";
////            request.getRequestDispatcher(url).forward(request, response);
//            if ("GET".equals(method)) {
//                url = "exam/attemptExam.jsp";
//                request.getRequestDispatcher(url).forward(request, response);
//            } else if ("POST".equalsIgnoreCase(method)) {
//                takeExamId = Integer.parseInt(request.getParameter("takeExamId"));
//                Map<QuestionExam, questionExamAnswer> useranswer = new HashMap<>();
//                for (questionExam q : questionList) {
//                    String userAnswer = request.getParameter(Integer.toString(q.getQuesId()));
//                    if (userAnswer == null) {
//                        useranswer.put(q, null);
//                    } else {
//                        int answerId = Integer.parseInt(userAnswer);
//                        for (ArrayList<QuestionExamAnswer> answers : questionAnswer.values()) {
//                            for (questionExamAnswer a : answers) {
//                                if (a.getAnswerId() == answerId && a.getQuesId() == q.getQuesId()) {
//                                    useranswer.put(q, a);
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                }
//                double score = GradeUtils.grade(questionList, useranswer);
//                daoTakeExam.completeAttempt(takeExamId, useranswer, score);
//                response.sendRedirect("ClassExamReviewURL?takeExamId=" + takeExamId);
//            }
//
//        }
//    }
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
        //    processRequest(request, response);

        DAOExam daoExam = new DAOExam();
        DAOQuestionExam daoQuestion = new DAOQuestionExam();
        DAOQuestionExamAnswer daoAnswer = new DAOQuestionExamAnswer();
        DAOTakeExam daoTakeExam = new DAOTakeExam();
        User user = (User) request.getSession().getAttribute("acc");


        int examId = Integer.parseInt(request.getParameter("examId"));
        //   int takeExamId = Integer.parseInt(request.getParameter("takeExamId"));
        Exam exam = daoExam.getExamById(examId);
        //get current user      
        int takeExamId = daoTakeExam.takeExam(user.getAccountId(), examId);

        TakeExam takeExam = daoTakeExam.getTakeExamByExamIdObj(examId);
        ArrayList<questionExam> questionList = daoQuestion.getData("select * from QuestionExam where examId=" + examId);
        //       ArrayList<QuestionExamAnswer> answerList = daoAnswer.getData("select * from questionExamAnswer");
        HashMap<Integer, ArrayList<questionExamAnswer>> questionAnswer = daoAnswer.getAnswerMap(examId);

        int examTimeInSeconds = exam.getTimer(); // Lấy thời gian giới hạn của bài kiểm tra
        long examTimeInMillis = examTimeInSeconds * 1000; // Chuyển đổi giây thành mili giây

        Timestamp startTime = Timestamp.valueOf(takeExam.getStartDate());
        Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis()); // Lấy thời gian hiện tại
        startTime.setTime(startTime.getTime() + examTimeInMillis);
        long differenceInMillis = startTime.getTime() - currentTimeStamp.getTime();
        long differenceInSeconds = TimeUnit.MILLISECONDS.toSeconds(differenceInMillis);

        request.setAttribute("status", 1);
        request.setAttribute("questionCount", questionList.size());
        request.setAttribute("takeExamId", takeExamId);
        request.setAttribute("remainingTime", differenceInSeconds);
        request.setAttribute("questions", questionList);
        request.setAttribute("exam", exam);
        request.setAttribute("answers", questionAnswer);
        String url = "exam/attemptExam.jsp";
        request.getRequestDispatcher(url).forward(request, response);

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
        //    processRequest(request, response);

        DAOQuestionExam daoQuestion = new DAOQuestionExam();
        DAOQuestionExamAnswer daoAnswer = new DAOQuestionExamAnswer();
        DAOTakeExam daoTakeExam = new DAOTakeExam();

        int examId = Integer.parseInt(request.getParameter("examId"));
        int takeExamId = Integer.parseInt(request.getParameter("takeExamId"));

        ArrayList<questionExam> questionList = daoQuestion.getData("select * from QuestionExam where examId=" + examId);
        //       ArrayList<QuestionExamAnswer> answerList = daoAnswer.getData("select * from questionExamAnswer");
        HashMap<Integer, ArrayList<questionExamAnswer>> questionAnswer = daoAnswer.getAnswerMap(examId);

//        takeExamId = Integer.parseInt(request.getParameter("takeExamId"));
        Map<questionExam, questionExamAnswer> userAnswers = new HashMap<>();
//        for(questionExam question : questionList){
//            String userAns = request.getParameter(Integer.toString(question.getQuesId()));
//            if(userAnswer == null){
//                userAnswer.put(question, -1);
//            } else {
//                userAnswer.put(question, Integer.parseInt(userAns));
//            }
//        }
//        daoTakeExam.completeAttempt(takeExamId, userAnswer, GradeUtils.grade(questionList, userAnswer));
        for (questionExam question : questionList) {
            String userAnswerId = request.getParameter(Integer.toString(question.getQuesId()));
            if (userAnswerId == null) {
                userAnswers.put(question, null);
            } else {
                int answerId = Integer.parseInt(userAnswerId);
                for (ArrayList<questionExamAnswer> answers : questionAnswer.values()) {
                    for (questionExamAnswer answer : answers) {
                        if (answer.getAnswerId() == answerId && answer.getQuesId() == question.getQuesId()) {
                            userAnswers.put(question, answer);
                            break;
                        }
                    }
                }
            }
        }
        double score = GradeUtils.grade(questionList, userAnswers);
        daoTakeExam.completeAttempt(takeExamId, userAnswers, score);
        //    request.getRequestDispatcher("ClassExamReviewURL?takeExamId=" + takeExamId).forward(request, response);
        response.sendRedirect("ClassExamReviewURL?takeExamId=" + takeExamId);
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
