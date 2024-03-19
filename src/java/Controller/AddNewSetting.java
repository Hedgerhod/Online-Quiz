/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.DAORole;
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
import jakarta.servlet.http.HttpSession;
import java.util.Properties;

/**
 *
 * @author Asus
 */
@WebServlet(name = "AddNewSetting", urlPatterns = {"/AddSettingURL"})
public class AddNewSetting extends HttpServlet {

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

        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAPS");

        String url = siteMaps.getProperty(MyApplicationConstants.AdminFeature.ADMIN_SETTING_ACTION);

        DAORole daoR = new DAORole();
        DAOSubject daoS = new DAOSubject();
        String name = request.getParameter("name");
        String type = request.getParameter("typeId");
        String idParam = request.getParameter("id");
        int id = -1;

        if (idParam != null && !idParam.isEmpty()) {
            id = Integer.parseInt(idParam);
        }

        String subjectCode = request.getParameter("subjectCode");

        if ("role".equals(type)) {
            if ((daoR.checkRoleExists(id))) {
                HttpSession session = request.getSession();
                int sessionTimeoutInSeconds = 2;
                session.setMaxInactiveInterval(sessionTimeoutInSeconds);
                session.setAttribute("messagee", "Role with roleId " + id + " already exists.");
            } else {
                daoR.insertRole(id, name);

                HttpSession session = request.getSession();
                int sessionTimeoutInSeconds = 2;
                session.setMaxInactiveInterval(sessionTimeoutInSeconds);
                session.setAttribute("messagee", "Added successfully");
            }
        } else if ("subject".equals(type)) {
            if (daoS.checkSubjectCodeExistence(subjectCode)) {
                HttpSession session = request.getSession();
                int sessionTimeoutInSeconds = 2;
                session.setMaxInactiveInterval(sessionTimeoutInSeconds);
                session.setAttribute("messagee", "Subject Code " + name + " already exists.");
            } else {
                HttpSession session = request.getSession();
                int sessionTimeoutInSeconds = 2;
                session.setMaxInactiveInterval(sessionTimeoutInSeconds);
                session.setAttribute("messagee", "Added successfully");
                daoS.insertSubject(subjectCode, name);
            }
        }
        response.sendRedirect(url);

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
