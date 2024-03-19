/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.DAOAdmin;
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
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author Asus
 */
@WebServlet(name = "UpdateSetting", urlPatterns = {"/UpdateSettingURL"})
public class UpdateSetting extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateSetting</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateSetting at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAPS");

        String url = siteMaps.getProperty(MyApplicationConstants.AdminFeature.ADMIN_UPDATE_SETTING_PAGE);

        int sid = Integer.parseInt(request.getParameter("sid"));

        String type = request.getParameter("type");
        DAOAdmin dao = new DAOAdmin();
        Map<String, Object> setting = dao.getSettingById(sid, type);
        request.setAttribute("dataa", setting);
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

        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAPS");

        String url = siteMaps.getProperty(MyApplicationConstants.AdminFeature.ADMIN_SETTING_ACTION);

        DAOAdmin dao = new DAOAdmin();
        int id = Integer.parseInt(request.getParameter("id"));
        String subjectCode = request.getParameter("subjectCode");
        String type = request.getParameter("typeId");
        String name = request.getParameter("name");
        if ("subject".equals(type)) {
            if (dao.checkSubjectCodeExistence(subjectCode)) {
                HttpSession session = request.getSession();
                int sessionTimeoutInSeconds = 2;
                session.setMaxInactiveInterval(sessionTimeoutInSeconds);
                session.setAttribute("messagee", "SubjectCode " + subjectCode + " already exists.");
            } else {
                dao.updateSubjectNameByCode(subjectCode, name);
                HttpSession session = request.getSession();
                int sessionTimeoutInSeconds = 2;
                session.setMaxInactiveInterval(sessionTimeoutInSeconds);
                session.setAttribute("messagee", "UpdateSuccess");
            }
        } else if ("role".equals(type)) {
            dao.updateSettingRole(id, name);
            HttpSession session = request.getSession();

            int sessionTimeoutInSeconds = 2;
            session.setMaxInactiveInterval(sessionTimeoutInSeconds);
            session.setAttribute("messagee", "UpdateSuccess");
        }
        response.sendRedirect(url);
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
