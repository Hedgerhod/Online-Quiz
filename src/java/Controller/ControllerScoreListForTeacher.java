/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.User;
import Entity.TakeExam;
import Model.DAOClass;
import Model.DAOTakeClass;
import Model.DAOTakeExam;
import Model.DAOTeacher;
import Model.DAOUser;
import Utils.MyApplicationConstants;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author phamg
 */
@WebServlet(name = "ControllerScoreListForTeacher", urlPatterns = {"/ScoreListForTeacherURL"})
public class ControllerScoreListForTeacher extends HttpServlet {

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
        
        String url = siteMaps.getProperty(MyApplicationConstants.TeacherClassFeature.TEACHER_SCORE_LIST_PAGE);
        
        
        try ( PrintWriter out = response.getWriter()) {
            String service = request.getParameter("go");
            HttpSession session = request.getSession();
            int ExamId = (int) Integer.parseInt(request.getParameter("ExamId"));
            session.setAttribute("ExamId", ExamId);
            int classId = (int) Integer.parseInt(session.getAttribute("classId").toString());
            DAOTakeClass daoTC = new DAOTakeClass();
            DAOUser daoU = new DAOUser();
            DAOTakeExam daoTE = new DAOTakeExam();
            ArrayList<Integer> studentIdlist = new ArrayList<>();
            ArrayList<User> StudentList = new ArrayList<>();
            ArrayList<TakeExam> ScoreList = new ArrayList<>();
            if (service == null) {
            studentIdlist = daoTC.getStudentIDbyClassID(classId);
            for (Integer studentIds : studentIdlist) {
                int studentId = studentIds;
                StudentList.add(daoU.getUserById(studentId));
            ScoreList = daoTE.getTakeExamByExamId(ExamId);
            }
            request.setAttribute("StudentList", StudentList);
            request.setAttribute("ScoreList", ScoreList);
            request.getRequestDispatcher(url).forward(request, response);
            } 
//            else {
//            
//            }
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
