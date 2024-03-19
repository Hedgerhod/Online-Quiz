
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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;

import java.util.List;
import java.util.Properties;

/**
 *
 * @author ACER
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginURL"})
public class LoginController extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("go");
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(7200);
            if (service == null) {
                service = "showLogin";
            }
            if (service.equals("showLogin")) {
                System.out.println("showLogin");
                if (request.getSession().getAttribute("acc") != null) {
                    User user = (User) request.getSession().getAttribute("acc");
                    request.setAttribute("username", user.getUsername());
                    request.setAttribute("password", user.getPassword());
                                                System.out.println(user.toString());

                }
                url=siteMaps.getProperty(MyApplicationConstants.LoginFeature.LOGIN_PAGE);
                request.getRequestDispatcher(url).forward(request, response);
                boolean inValid = "".equals(request.getSession().getAttribute("validate"));
                if (!inValid) {
                    request.getSession().setAttribute("validate", "");
                }
            }
            if (service.equals("login")) {
                System.out.println("Login day nay");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                boolean remember = "".equals(request.getParameter("remember-account"));
                String validate = "user name or password is incorrect.";
                DAOUser DAOUser = new DAOUser();
                EncryptionUtils eu = new EncryptionUtils();
                List<User> isUser = DAOUser.checkUser(username, eu.toMD5(password));
                if (!isUser.isEmpty()) {
                    try {
                        User user = DAOUser.getUser(username, eu.toMD5(password));
                        if (!user.isActive()) {
                            request.getSession().setAttribute("validate", "You have been restricted to use our service!");
                            url=siteMaps.getProperty(MyApplicationConstants.ApplicationScope.LOGIN_ACTION);
                            response.sendRedirect(url);
                            return;
                        }

                        request.getSession().setAttribute("acc", user);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (!remember) {
                        Cookie cEmail = new Cookie("username", username);
                        Cookie cPassword = new Cookie("password", password);
                        cEmail.setMaxAge(60 * 60 * 24);
                        cPassword.setMaxAge(60 * 60 * 24);
                        response.addCookie(cEmail);
                        response.addCookie(cPassword);
                    }
//                    if (DAOUser.checkAdmin(email, eu.toMD5(password))) {
//                        response.sendRedirect("/admin");
//                        return;
//                    }
                    System.out.println("login thanh cong");
                    request.getSession().setAttribute("validate", "");
                    url=siteMaps.getProperty(MyApplicationConstants.ApplicationScope.HOME_ACTION);
                    response.sendRedirect(url);
                } else {
                    System.out.println("quay lai login");
                     url=siteMaps.getProperty(MyApplicationConstants.ApplicationScope.LOGIN_ACTION);
                    request.getSession().setAttribute("validate", validate);
                    response.sendRedirect(url);
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

}