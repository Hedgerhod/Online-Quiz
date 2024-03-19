
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

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
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author Asus
 */
@WebServlet(name = "SearchUserByAD", urlPatterns = {"/SearchUserURL"})
public class SearchUserByAD extends HttpServlet {

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

        String url = siteMaps.getProperty(MyApplicationConstants.AdminFeature.ADMIN_SETTING_LIST_PAGE);

        try ( PrintWriter out = response.getWriter()) {
            String txt = request.getParameter("txt");
            DAOUser dao = new DAOUser();
            final int PAGE_SIZE = 2;
            //phân trang
            int page = 1;
            String pageStr = request.getParameter("page");
            if (pageStr != null) {
                page = Integer.parseInt(pageStr);
            }
            if (page < 1) {
                page = 1;
            }
            int totalUser = dao.getTotalSearch(txt);
            int totalPage = totalUser / PAGE_SIZE;
            if (totalUser % PAGE_SIZE != 0) {
                totalPage += 1;
            }
            if (page > totalPage) {
                page = totalPage;
            }

            if (!dao.isUserSearchResultEmpty(txt, page, PAGE_SIZE)) {
                List<Map<String, Object>> userListS = dao.searchUsersWithPagination(txt, page, PAGE_SIZE);
                request.setAttribute("data", userListS);
            } else {
                HttpSession session = request.getSession();
                int sessionTimeoutInSeconds = 2;
                session.setMaxInactiveInterval(sessionTimeoutInSeconds);
                session.setAttribute("messageeee", "Not Found!!!!!");
            }

            request.setAttribute("page", page);
            request.setAttribute("txt", txt);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("Url", "SearchUserURL?txt=" + txt + "&");
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
