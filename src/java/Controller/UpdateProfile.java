
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Admin;
import Entity.Student;
import Entity.Teacher;
import Entity.User;
import Model.DAOAdmin;
import Model.DAOStudent;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
@WebServlet(name = "UpdateProfile", urlPatterns = {"/UpdateProfile"})
public class UpdateProfile extends HttpServlet {

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
            out.println("<title>Servlet UpdateProfile</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProfile at " + request.getContextPath() + "</h1>");
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

        String url = siteMaps.getProperty(MyApplicationConstants.UpdateAccountFeature.USER_PROFILE_PAGE);
        DAOUser dao = new DAOUser();
        int sid = Integer.parseInt(request.getParameter("sid"));
        Map<String, Object> user = dao.getUserByIdd(sid);
        request.setAttribute("data", user);
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

        String url = siteMaps.getProperty(MyApplicationConstants.UpdateAccountFeature.USER_PROFILE_ACTION);
        DAOUser dao = new DAOUser();
        DAOAdmin daoA = new DAOAdmin();
        DAOTeacher daoT = new DAOTeacher();
        DAOStudent daoS = new DAOStudent();
        int acc = Integer.parseInt(request.getParameter("accId"));
        String username = request.getParameter("user");
        String phone = request.getParameter("phone");
        String adName = request.getParameter("adName");
        String teacherName = request.getParameter("tcName");
        String studentName = request.getParameter("stName");
        String dob = request.getParameter("dob");
        String password = request.getParameter("pass");
        String email = request.getParameter("email");

        int AccountId = dao.updateUserAndGetAccountId(acc, username, email, password);
        daoA.updateAdmin(AccountId, adName, phone);
        daoT.updateTeacher(AccountId, teacherName, phone);
        daoS.updateStudent(AccountId, studentName, phone, dob);
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
