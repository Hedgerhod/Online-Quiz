/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.NormalQuestion;
import Entity.NormalQuestionAnswer;
import Entity.QuestionSet;
import Model.DAONormalQuestion;
import Model.DAONormalQuestionAnswer;
import Model.DAOQuestionSet;
import Utils.MyApplicationConstants;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author Admin
 */
@WebServlet(name = "FlashCardController", urlPatterns = {"/FlashCardURL"})
public class FlashCardController extends HttpServlet {

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
            out.println("<title>Servlet FlashCardController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FlashCardController at " + request.getContextPath() + "</h1>");
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
        try ( PrintWriter out = response.getWriter()) {

            ServletContext context = this.getServletContext();
            Properties siteMaps = (Properties) context.getAttribute("SITE_MAPS");

            String url = siteMaps.getProperty(MyApplicationConstants.QuestionSetFeature.FLASH_CARD_PAGE);
            DAOQuestionSet dao = new DAOQuestionSet();
            DAONormalQuestion questionDAO = new DAONormalQuestion();
            DAONormalQuestionAnswer answerDAO = new DAONormalQuestionAnswer();
            String service = request.getParameter("go");
            if (service == null) {
                service = "listAllSets";
            }
            if (service.equals("flashCard")) {
                ArrayList<QuestionSet> allQuesSet = dao.getData("select * from QuestionSet");

                int setId = Integer.parseInt(request.getParameter("SetId"));

                ArrayList<NormalQuestion> Ques = dao.getQues(setId);

                ArrayList<ArrayList<NormalQuestionAnswer>> QuesAnswers = dao.getAnswer(setId);
                request.setAttribute("data", allQuesSet);
                request.setAttribute("question", Ques);
                request.setAttribute("content", QuesAnswers);
                request.getRequestDispatcher(url).forward(request, response);
            }
        }
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
