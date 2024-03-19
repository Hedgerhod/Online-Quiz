
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.Student;
import Entity.Teacher;
import Entity.User;
import Model.DAOStudent;
import Model.DAOTeacher;
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
import java.util.Properties;

/**
 *
 * @author ACER
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterURL"})
public class RegisterController extends HttpServlet {

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
            if (service == null) {
                service = "showRegister";
            }
            if (service.equals("showRegister")) {
                url=siteMaps.getProperty(MyApplicationConstants.RegisterFeature.REGISTER_PAGE);
                request.getRequestDispatcher(url).forward(request, response);
            }

            if (service.equals("registerStudent")) {
                String username = request.getParameter("username");
                String fullname = request.getParameter("fullname");
                String date = request.getParameter("date");
                String phone = request.getParameter("phone");
                String password = request.getParameter("password");
                String email = request.getParameter("email");
                request.setAttribute("email", email);
                request.setAttribute("fullname", fullname);


                String error = "";

                EncryptionUtils encrypt = new EncryptionUtils();
                DAOUser dao = new DAOUser();
                DAOStudent daos = new DAOStudent();
                if (dao.emailCheck(email)) {
                    error += "Email provided is already registered!";
                    request.getSession().setAttribute("Email_DUP", error);
                } 
                if(dao.usernameCheck(username)) {
                    error += "\nUsername provided is already registered!";
                    request.getSession().setAttribute("Email_DUP", error);
                }

                password = encrypt.toMD5(password);

                if (error.length() > 0) {
                    url = siteMaps.getProperty(MyApplicationConstants.RegisterFeature.REGISTER_PAGE);
                } else {
                    User user = new User(username, email, password, 1);
                    System.out.println(user.toString());
                    dao.insertUser(user);
                    user = dao.getUser("SELECT TOP 1 * FROM [User] ORDER BY [AccountId] DESC");
                    Student student = new Student(user.getAccountId(), fullname, phone, date);
                    daos.CreateStudent(student);
                    url = siteMaps.getProperty(MyApplicationConstants.LoginFeature.LOGIN_PAGE);
                }
                request.getRequestDispatcher(url).forward(request, response);
            }
            
            if (service.equals("registerTeacher")) {
                String username = request.getParameter("username");
                String fullname = request.getParameter("fullname");
                String phone = request.getParameter("phone");
                String password = request.getParameter("password");
                String email = request.getParameter("email");
                request.setAttribute("email", email);
                request.setAttribute("fullname", fullname);

           //     String url;
                String error = "";

                EncryptionUtils encrypt = new EncryptionUtils();
                DAOUser dao = new DAOUser();
                DAOTeacher daot = new DAOTeacher();
                if (dao.emailCheck(email)) {
                    error += "Email provided is already registered!";
                    request.getSession().setAttribute("Email_DUP", error);
                } if(dao.usernameCheck(username)) {
                    error += "\nUsername provided is already registered!";
                    request.getSession().setAttribute("Email_DUP", error);
                }

                password = encrypt.toMD5(password);

                if (error.length() > 0) {
                    url = siteMaps.getProperty(MyApplicationConstants.RegisterFeature.REGISTER_PAGE);
                } else {
                    User user = new User(username, email, password, 2);
                    dao.insertUser(user);
                    user = dao.getUser("SELECT TOP 1 * FROM [User] ORDER BY [AccountId] DESC");
                    Teacher teacher = new Teacher(user.getAccountId(), fullname, phone);
                    daot.insertTeacher(teacher);
                    url =siteMaps.getProperty(MyApplicationConstants.LoginFeature.LOGIN_PAGE);
                }
                request.getRequestDispatcher(url).forward(request, response);
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
