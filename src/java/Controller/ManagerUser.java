
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Student;
import Entity.Teacher;
import Entity.User;
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
@WebServlet(name = "ManagerUserController", urlPatterns = {"/ManagerUserURL"})
public class ManagerUser extends HttpServlet {

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
        
        String url = siteMaps.getProperty(MyApplicationConstants.AdminFeature.ADMIN_MANAGER_PAGE);
        
        try ( PrintWriter out = response.getWriter()) {
            DAOUser dao = new DAOUser();
           
            
            final int PAGE_SIZE = 8;
            //phân trang
            int page = 1;
            String pageStr = request.getParameter("page");
            if (pageStr != null) {
                page = Integer.parseInt(pageStr);
            }
            if (page < 1) {
                page = 1;
            }
            int totalUser = dao.getTotalUser();
            int totalPage = totalUser / PAGE_SIZE;
            if (totalUser % PAGE_SIZE != 0) {
                totalPage += 1;
            }
            if (page > totalPage) {
                page = totalPage;
            }
            List<Map<String, Object>> userListP = dao.getAllUserWithPaging(page, PAGE_SIZE);
            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("data", userListP);
             request.setAttribute("Url", "ManagerUserURL?");
            request.getRequestDispatcher(url).forward(request, response);
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
        DAOUser dao = new DAOUser();
        String status = request.getParameter("status");
        String role = request.getParameter("role");
        

        if ("all".equals(status) && "all".equals(role)) {
            List<Map<String, Object>> userList = dao.getAllUsers();
            request.setAttribute("data", userList);
        } else if ("active".equals(status)) {
            List<Map<String, Object>> userList = null;
            if ("3".equals(role)) {
                userList = dao.getAllActiveAdminUsers();
            } else if ("2".equals(role)) {
                userList = dao.getAllActiveTeacherUsers();
            } else if ("1".equals(role)) {
                userList = dao.getAllActiveStudentUsers();
            } else {

                userList = dao.getAllActiveUsers();
            }
            request.setAttribute("data", userList);
        } else if ("suspended".equals(status)) {
            List<Map<String, Object>> userList = null;
            if ("3".equals(role)) {
                userList = dao.getAllBanAdminUsers();
            } else if ("2".equals(role)) {
                userList = dao.getAllSuspendedTeacherUsers();
            } else if ("1".equals(role)) {
                userList = dao.getAllSuspendedStudentUsers();
            } else {
                userList = dao.getAllSuspendedUsers();
            }
            request.setAttribute("data", userList);
        } else if ("all".equals(status)) {
            List<Map<String, Object>> userList = null;
            if ("3".equals(role)) {
                userList = dao.getAllAdminUsers();
            } else if ("2".equals(role)) {
                userList = dao.getAllTeacherUsers();
            } else if ("1".equals(role)) {
                userList = dao.getAllStudentUsers();
            }
            request.setAttribute("data", userList);
        }

        request.getRequestDispatcher("Admin/AdminManager.jsp").forward(request, response);
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

