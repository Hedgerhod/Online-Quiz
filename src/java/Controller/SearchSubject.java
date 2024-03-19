
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Entity.BlogList;
import Entity.QuestionSet;
import Entity.Subject;
import Model.DAOBlogList;
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
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Asus
 */
@WebServlet(name="SearchSubject", urlPatterns={"/SearchSubject"})
public class SearchSubject extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
        
        String url = siteMaps.getProperty(MyApplicationConstants.ApplicationScope.HOME_PAGE);
        
        try (PrintWriter out = response.getWriter()) {
             DAOQuestionSet qset = new DAOQuestionSet();
            DAOSubject sub =  new DAOSubject();
            DAOBlogList bl = new DAOBlogList();
            List<QuestionSet> listS = qset.getTop3();
            List<Subject> listSub = sub.getData("select*from Subject");
            int SubjectId = Integer.parseInt(request.getParameter("SubjectId"));
            
             final int PAGE_SIZE = 6;
             //phân trang
            int page = 1;
            String pageStr = request.getParameter("page");
            if (pageStr != null) {
                page = Integer.parseInt(pageStr);
            }
            if (page < 1) {
                page = 1;
            }
            int totalBlog = bl.getTotalBySubjectId(SubjectId);
            int totalPage = totalBlog/ PAGE_SIZE;
            if (totalBlog % PAGE_SIZE != 0) {
                totalPage += 1;
            }
            if (page > totalPage) {
                page = totalPage;
            }
            List<BlogList> listBlog = bl.getBlogListBySubjectIdWithPagination(SubjectId, page, PAGE_SIZE);
            request.setAttribute("listS", listS);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("SubNav", listSub);
            request.setAttribute("Blog", listBlog);
            request.setAttribute("page", page);
            request.setAttribute("url", "SearchSubject?SubjectId=" + SubjectId + "&");
            request.getRequestDispatcher(url).forward(request, response);
          
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
