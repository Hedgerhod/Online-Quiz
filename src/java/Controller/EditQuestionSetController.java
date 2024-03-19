/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.NormalQuestion;
import Entity.NormalQuestionAnswer;
import Entity.QuestionSet;
import Model.DAOQuestionSet;
import Model.DAOSubject;
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
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 *
 * @author hieul
 */
@WebServlet(name = "EditQuestionSet", urlPatterns = {"/EditQuestionSetURL"})
public class EditQuestionSetController extends HttpServlet {

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
        
        String url = siteMaps.getProperty(MyApplicationConstants.QuestionSetFeature.EDIT_SET_PAGE);
        
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
                int setId = Integer.parseInt(request.getParameter("setId"));                     
                DAOQuestionSet questionSetDAO = new DAOQuestionSet();
                DAOSubject subjectDAO = new DAOSubject();
                QuestionSet set = questionSetDAO.getSet(setId);
                
                ArrayList<NormalQuestion> questions = questionSetDAO.getQues(setId);
                
                HashMap<Integer, ArrayList<NormalQuestionAnswer>> questionAnswer = questionSetDAO.getAnswerMap(setId);
                HashMap<Integer, String> subjectMap = subjectDAO.SubjectMap();
                
                request.setAttribute("set", set);
                request.setAttribute("questions", questions);
                request.setAttribute("subjectMap", subjectMap);
                request.setAttribute("questionAnswer", questionAnswer);
            
                request.getRequestDispatcher(url).include(request, response);
                
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
