/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.QuestionSet;
import Model.DAOClass;
import Model.DAOClassQuestionSet;
import Model.DAOQuestionSet;
import Model.DAOSubject;
import Model.DAOTeacher;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

/**
 *
 * @author phamg
 */
@WebServlet(name = "EditQSClassURL", urlPatterns = {"/EditQSClassURL"})
public class ControllerEditQSClass extends HttpServlet {

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
            String service = request.getParameter("go");
            HttpSession session = request.getSession();
            int classId = (int) Integer.parseInt(session.getAttribute("classId").toString());
            DAOClass daoC = new DAOClass();
            DAOTeacher daoT = new DAOTeacher();
            DAOClassQuestionSet daoCQS = new DAOClassQuestionSet();
            DAOQuestionSet daoQS = new DAOQuestionSet();
            DAOSubject daoS = new DAOSubject();
            if (service == null) {
                Entity.Class myClass = daoC.ClassByClassID(classId);
                Entity.Teacher teacher = daoT.getTeacherByAccountId(myClass.getTeacherAccountId());
                ArrayList<Integer> setList = daoCQS.getSetIdbyClassId(classId);
                ArrayList<QuestionSet> questionSetListAll = (ArrayList<QuestionSet>) daoQS.getAllQuestionSet();
                ArrayList<QuestionSet> questionSetList = new ArrayList<>();
                ArrayList<Integer> subIDlist = new ArrayList<>();
                ArrayList<Integer> subIDlistAll = daoQS.getAllSubjectId();
                HashMap<Integer, Entity.Subject> subjectMap = new HashMap<>();
                HashMap<Integer, Entity.Subject> subjectMapAll = new HashMap<>();
                for (Integer setIds : setList) {
                    int setId = setIds;
                    questionSetList.add(daoQS.getQuestionSetById(setId));
                    subIDlist.add(daoQS.getQuestionSetById(setId).getSubjectId());
                }
                for (Integer subID : subIDlist) {
                    int subId = subID;
                    subjectMap.put(subId, daoS.getSubject(subId));
                }
                for (Integer subID : subIDlistAll) {
                    int subId = subID;
                    subjectMapAll.put(subId, daoS.getSubject(subId));
                }
                request.setAttribute("questionSetListAll", questionSetListAll);
                request.setAttribute("subjectMapAll", subjectMapAll);
                request.setAttribute("subjectMap", subjectMap);
                request.setAttribute("myClass", myClass);
                request.setAttribute("teacher", teacher);
                request.setAttribute("questionSetList", questionSetList);
                url = siteMaps.getProperty(MyApplicationConstants.TeacherClassFeature.EDIT_CLASS_QS_PAGE);
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                if (service.equals("delete")) {
                    String setIdParam = request.getParameter("setId");
                    int setId = Integer.parseInt(setIdParam);
                    daoCQS.DeleteQuestionSet(setId, classId);
                }
                if (service.equals("cleanAll")) {
                    daoCQS.DeleteQuestionSetInClass(classId);
                    url = siteMaps.getProperty(MyApplicationConstants.TeacherClassFeature.EDIT_CLASS_QS_ACTION);
                    response.sendRedirect(url);
                }
                if (service.equals("search")) {
                    String searchQuery = request.getParameter("searchQuery");
                    Entity.Class myClass = daoC.ClassByClassID(classId);
                    Entity.Teacher teacher = daoT.getTeacherByAccountId(myClass.getTeacherAccountId());
                    ArrayList<Integer> setList = daoCQS.getSetIdbyClassId(classId);
                    ArrayList<QuestionSet> questionSetList = new ArrayList<>();
                    ArrayList<Integer> subIDlist = new ArrayList<>();
                    ArrayList<Integer> subIDlistAll = daoQS.getAllSubjectId();
                    HashMap<Integer, Entity.Subject> subjectMap = new HashMap<>();
                    HashMap<Integer, Entity.Subject> subjectMapAll = new HashMap<>();
                    for (Integer setIds : setList) {
                        int setId = setIds;
                        questionSetList.add(daoQS.getQuestionSetById(setId));
                        subIDlist.add(daoQS.getQuestionSetById(setId).getSubjectId());
                    }
                    for (Integer subID : subIDlist) {
                        int subId = subID;
                        subjectMap.put(subId, daoS.getSubject(subId));
                    }
                    for (Integer subID : subIDlistAll) {
                        int subId = subID;
                        subjectMapAll.put(subId, daoS.getSubject(subId));
                    }
                    request.setAttribute("subjectMapAll", subjectMapAll);
                    request.setAttribute("subjectMap", subjectMap);
                    request.setAttribute("myClass", myClass);
                    request.setAttribute("teacher", teacher);
                    request.setAttribute("questionSetList", questionSetList);
                    request.setAttribute("questionSetListAll", daoQS.searchByTitle(searchQuery));
                    url = siteMaps.getProperty(MyApplicationConstants.TeacherClassFeature.EDIT_CLASS_QS_PAGE);
                    request.getRequestDispatcher(url).forward(request, response);
                }
                if (service.equals("add")) {
                    String[] selectedSetIds = request.getParameterValues("selectedSetIds[]");
                    if (selectedSetIds != null) {
                        for (String setId : selectedSetIds) {
                            int setIdValue = Integer.parseInt(setId);
                            daoCQS.CreateClassQuestionSetById(classId, setIdValue);
                        }
                    }
                    url = siteMaps.getProperty(MyApplicationConstants.TeacherClassFeature.EDIT_CLASS_QS_ACTION);
                    response.sendRedirect(url);
                }
                if (service.equals("add")) {
                    int setIdParam = Integer.parseInt(request.getParameter("setId"));
                    daoCQS.CreateClassQuestionSetById(classId, setIdParam);
                    QuestionSet newQuestionSet = daoQS.getQuestionSetById(setIdParam);
                    Integer subID = newQuestionSet.getSubjectId();
                    Entity.Subject subject = daoS.getSubject(subID);

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
