/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.User;
import Model.DAOUser;
import Utils.EncryptionUtils;
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
 * @author Admin
 */
@WebServlet(name = "ChangeServlet", urlPatterns = {"/change"})
public class ChangeServlet extends HttpServlet {

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

        String url = siteMaps.getProperty(MyApplicationConstants.UpdateAccountFeature.CHANGE_PASS_PAGE);

        HttpSession session = request.getSession();
        String users = request.getParameter("username");
        String oldPassword = request.getParameter("opass");
        String newPassword = request.getParameter("npass");
        String rePassword = request.getParameter("cnpass");

        boolean check = true;

        if (oldPassword == null || oldPassword.length() == 0) {
            request.setAttribute("op", "Old password can not be null");
            check = false;
        }
        if (newPassword == null || newPassword.length() == 0) {
            request.setAttribute("np", "New password can not be null");
            check = false;
        }
        if (rePassword == null || rePassword.length() == 0) {
            request.setAttribute("rp", "Confirm password can not be null");
            check = false;
        }
        if (rePassword != null && newPassword != null && !rePassword.equals(newPassword)) {
            request.setAttribute("rp", "Confirm password is not equal to new password!");
            check = false;

        }
        if (!check) {
            request.getRequestDispatcher("ChangePass.jsp").forward(request, response);
        }

        DAOUser daoUser = new DAOUser();
        User user = daoUser.getUser("select * from [User] where Username = '" + users + "'");
        if (user == null) {
            String errorMessage = "Username or Password is incorrect!";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("ChangePass.jsp").forward(request, response);
        } else {
            User newUser = new User(users, newPassword);
            daoUser.ChangePass(newUser);
            String successMessage = "Change Successfully!";
            request.setAttribute("successMessage", successMessage);
            session.setAttribute("username", users);

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
        String users = request.getParameter("username");
        HttpSession session = request.getSession();
        session.setAttribute("username", users);
        request.getRequestDispatcher("ChangePass.jsp").forward(request, response);

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
