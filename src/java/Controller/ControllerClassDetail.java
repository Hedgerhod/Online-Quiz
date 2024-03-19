/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.QuestionSet;
import Model.DAOClass;
import Model.DAOClassQuestionSet;
import Model.DAOQuestionSet;
import Model.DAOTeacher;
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
@WebServlet(name = "ControllerClassDetail", urlPatterns = {"/ClassDetailURL"})
public class ControllerClassDetail extends HttpServlet {

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

        String url = siteMaps.getProperty(MyApplicationConstants.ClassFeature.CLASS_DETAIL_PAGE);

        try ( PrintWriter out = response.getWriter()) {
            String service = request.getParameter("go");
            HttpSession session = request.getSession();
            int classId1 = (int) Integer.parseInt(request.getParameter("classId"));
            session.setAttribute("classId", classId1);
            int classId = (int) Integer.parseInt(session.getAttribute("classId").toString());
            DAOClass daoC = new DAOClass();
            DAOTeacher daoT = new DAOTeacher();
            DAOClassQuestionSet daoCQS = new DAOClassQuestionSet();
            DAOQuestionSet daoQS = new DAOQuestionSet();
            if (service == null) {
                Entity.Class myClass = daoC.ClassByClassID(classId);
                Entity.Teacher teacher = daoT.getTeacherByAccountId(myClass.getTeacherAccountId());
                ArrayList<Integer> setList = daoCQS.getSetIdbyClassId(classId);
                ArrayList<QuestionSet> questionSetList = new ArrayList<>();
                for (Integer setIds : setList) {
                    int setId = setIds;
                    questionSetList.add(daoQS.getQuestionSetById(setId));
                }
                request.setAttribute("classId", classId1);
                request.setAttribute("myClass", myClass);
                request.setAttribute("teacher", teacher);
                request.setAttribute("questionSetList", questionSetList);
                request.getRequestDispatcher(url).forward(request, response);
            } else {

                if (service.equals("updateClass")) {
                    String className = request.getParameter("className");
                    String subject = request.getParameter("subject");
                    className = cleanAndFormatInput(className);
                    subject = cleanAndFormatInput(subject);
                    String FullClassName = className + " " + subject;
                    daoC.updateClassName(classId, FullClassName);
                    url = siteMaps.getProperty(MyApplicationConstants.ClassFeature.CLASS_DETAIL_ACTION);
                    response.sendRedirect(url + classId);
                }
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

    private String cleanAndFormatInput(String input) {
        input = input.trim();
        input = input.replaceAll("\\s+", " ");
        return input;
    }
}
