/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.QuestionSet;
import Entity.Subject;
import Entity.User;
import Entity.UserSet;
import Model.DAOClass;
import Model.DAOClassQuestionSet;
import Model.DAOQuestionSet;
import Model.DAOSubject;
import Model.DAOTeacher;
import Model.DAOUserSet;
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

/**
 *
 * @author phamg
 */
@WebServlet(name = "ControllerMyEnroll", urlPatterns = {"/MyEnrollURL"})
public class ControllerMyEnroll extends HttpServlet {

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
            String service = request.getParameter("go");
            HttpSession session = request.getSession();
            User acc = (User) request.getSession().getAttribute("acc");
            DAOClass daoC = new DAOClass();
            DAOQuestionSet daoQS = new DAOQuestionSet();
            DAOSubject daoS = new DAOSubject();
            DAOUserSet daoUS = new DAOUserSet();
            if (service == null) {
                ArrayList<UserSet> SetEnrollID = (ArrayList<UserSet>) daoUS.getEnroll(acc.getAccountId());
                ArrayList<QuestionSet> questionSetListAll = new ArrayList<QuestionSet>();
                ArrayList<Integer> subIDlistAll = new ArrayList<>();
                HashMap<Integer, Entity.Subject> subjectMapAll = new HashMap<>();
                ArrayList<Subject> Categories = new ArrayList<>();
                Categories = daoS.getData("Select * from Subject");
                for (UserSet setIds : SetEnrollID) {
                    int setId = setIds.getSetId();
                    questionSetListAll.add(daoQS.getQuestionSetById(setId));
                    subIDlistAll.add(daoQS.getQuestionSetById(setId).getSubjectId());
                }
                for (Integer subID : subIDlistAll) {
                    int subId = subID;
                    subjectMapAll.put(subId, daoS.getSubject(subId));
                }
                request.setAttribute("questionSetListAll", questionSetListAll);
                request.setAttribute("subjectMapAll", subjectMapAll);
                request.setAttribute("Categories", Categories);
                request.getRequestDispatcher("/Class/MyEnroll.jsp").forward(request, response);
            } else {
                if (service.equals("search")) {
                    String searchQuery = request.getParameter("searchQuery");
                    ArrayList<UserSet> SetEnrollID = (ArrayList<UserSet>) daoUS.getEnroll(acc.getAccountId());
                    ArrayList<QuestionSet> questionSetListAll = new ArrayList<>();
                    ArrayList<Integer> subIDlistAll = new ArrayList<>();
                    ArrayList<Subject> Categories = new ArrayList<>();
                    Categories = daoS.getData("Select * from Subject");
                    HashMap<Integer, Entity.Subject> subjectMapAll = new HashMap<>();
                    for (UserSet setIds : SetEnrollID) {
                        int setId = setIds.getSetId();
                        questionSetListAll.add(daoQS.getQuestionSetById(setId));
                        subIDlistAll.add(daoQS.getQuestionSetById(setId).getSubjectId());
                    }
                    for (Integer subID : subIDlistAll) {
                        int subId = subID;
                        subjectMapAll.put(subId, daoS.getSubject(subId));
                    }

                    ArrayList<QuestionSet> filteredQuestionSets = new ArrayList<>();
                    for (QuestionSet questionSet : questionSetListAll) {
                        for (QuestionSet searchResultItem : daoQS.searchByTitle(searchQuery)) {
                            if (questionSet.getSetId() == searchResultItem.getSetId()) {
                                filteredQuestionSets.add(questionSet);
                                break;
                            }
                        }
                    }
                    request.setAttribute("questionSetListAll", filteredQuestionSets);
                    request.setAttribute("subjectMapAll", subjectMapAll);
                    request.setAttribute("Categories", Categories);
                    request.getRequestDispatcher("/Class/MyEnroll.jsp").forward(request, response);
                }
                if (service.equals("category")) {
                    String SubCode = request.getParameter("SubCode");
                    ArrayList<UserSet> SetEnrollID = (ArrayList<UserSet>) daoUS.getEnroll(acc.getAccountId());
                    ArrayList<QuestionSet> questionSetListAll = new ArrayList<>();
                    ArrayList<Integer> subIDlistAll = new ArrayList<>();
                    ArrayList<Subject> Categories = new ArrayList<>();
                    Categories = daoS.getData("Select * from Subject");
                    HashMap<Integer, Entity.Subject> subjectMapAll = new HashMap<>();
                    for (UserSet setIds : SetEnrollID) {
                        int setId = setIds.getSetId();
                        questionSetListAll.add(daoQS.getQuestionSetById(setId));
                        subIDlistAll.add(daoQS.getQuestionSetById(setId).getSubjectId());
                    }
                    for (Integer subID : subIDlistAll) {
                        int subId = subID;
                        subjectMapAll.put(subId, daoS.getSubject(subId));
                    }

                    ArrayList<QuestionSet> filteredQuestionSets = new ArrayList<>();
                    for (QuestionSet questionSet : questionSetListAll) {
                        for (QuestionSet searchResultItem : daoQS.getData("select * from QuestionSet where SubjectId = " + SubCode)) {
                            if (questionSet.getSetId() == searchResultItem.getSetId()) {
                                filteredQuestionSets.add(questionSet);
                                break;
                            }
                        }
                    }
                    request.setAttribute("questionSetListAll", filteredQuestionSets);
                    request.setAttribute("subjectMapAll", subjectMapAll);
                    request.setAttribute("Categories", Categories);
                    request.getRequestDispatcher("/Class/MyEnroll.jsp").forward(request, response);
                }
                if (service.equals("Star")) {
                    String Num = request.getParameter("Num");
                    ArrayList<UserSet> SetEnrollID = (ArrayList<UserSet>) daoUS.getEnroll(acc.getAccountId());
                    ArrayList<QuestionSet> questionSetListAll = new ArrayList<>();
                    ArrayList<Integer> subIDlistAll = new ArrayList<>();
                    ArrayList<Subject> Categories = new ArrayList<>();
                    Categories = daoS.getData("Select * from Subject");
                    HashMap<Integer, Entity.Subject> subjectMapAll = new HashMap<>();
                    for (UserSet setIds : SetEnrollID) {
                        int setId = setIds.getSetId();
                        questionSetListAll.add(daoQS.getQuestionSetById(setId));
                        subIDlistAll.add(daoQS.getQuestionSetById(setId).getSubjectId());
                    }
                    for (Integer subID : subIDlistAll) {
                        int subId = subID;
                        subjectMapAll.put(subId, daoS.getSubject(subId));
                    }

                    ArrayList<QuestionSet> filteredQuestionSets = new ArrayList<>();
                    for (QuestionSet questionSet : questionSetListAll) {
                        for (QuestionSet searchResultItem : daoQS.getData("select * from QuestionSet where SetVote = " + Num)) {
                            if (questionSet.getSetId() == searchResultItem.getSetId()) {
                                filteredQuestionSets.add(questionSet);
                                break;
                            }
                        }
                    }
                    request.setAttribute("questionSetListAll", filteredQuestionSets);
                    request.setAttribute("subjectMapAll", subjectMapAll);
                    request.setAttribute("Categories", Categories);
                    request.getRequestDispatcher("/Class/MyEnroll.jsp").forward(request, response);
                }
                if (service.equals("enroll")) {
                    int setId = Integer.parseInt(request.getParameter("SetId"));
                    UserSet userSet = new UserSet(acc.getAccountId(), setId);
                    ArrayList<UserSet> checkUS = (ArrayList<UserSet>) daoUS.getAllUserSets();
                    boolean userSetExists = false;
                    for (UserSet existingUserSet : checkUS) {
                        if (existingUserSet.getUserId() == userSet.getUserId() && existingUserSet.getSetId() == userSet.getSetId()) {
                            userSetExists = true;
                            break;
                        }
                    }
                    if (!userSetExists) {
                        daoUS.insertUserSet(userSet);
                    }
                    response.sendRedirect("QuestionSetURL?go=setDetails&SetId=" + setId);
                }
                if (service.equals("unenroll")) {
                    int setId = Integer.parseInt(request.getParameter("SetId"));
                    daoUS.deleteUserSet(acc.getAccountId(), setId);
                    response.sendRedirect("MyEnrollURL");
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
