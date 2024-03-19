/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Entity.TakeExam;
import Entity.User;
import Model.DAOClass;
import Model.DAOTakeClass;
import Model.DAOTakeExam;
import Model.DAOUser;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author phamg
 */
@WebServlet(name = "ExportExcel", urlPatterns = {"/ExportExcel"})
public class ExportExcel extends HttpServlet {

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
        HttpSession session = request.getSession();
        int ExamId = (int) session.getAttribute("ExamId");
        int classId = (int) Integer.parseInt(session.getAttribute("classId").toString());
        
        DAOTakeClass daoTC = new DAOTakeClass();
        DAOUser daoU = new DAOUser();
        DAOTakeExam daoTE = new DAOTakeExam();
        DAOClass daoC = new DAOClass();
        ArrayList<Integer> studentIdlist = new ArrayList<>();
        ArrayList<User> StudentList = new ArrayList<>();
        ArrayList<TakeExam> ScoreList = new ArrayList<>();
        studentIdlist = daoTC.getStudentIDbyClassID(classId);
        for (Integer studentIds : studentIdlist) {
            int studentId = studentIds;
            StudentList.add(daoU.getUserById(studentId));
            ScoreList = daoTE.getTakeExamByExamId(ExamId);
        }
        String name = daoC.ClassByClassID(classId).getClassName();
        // Tạo một workbook mới
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Tạo một sheet mới
        XSSFSheet sheet = workbook.createSheet("Data");

        // Tạo hàng tiêu đề
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Name");
        headerRow.createCell(1).setCellValue("Score");
        headerRow.createCell(2).setCellValue("Status");
        headerRow.createCell(3).setCellValue("Start date");
        headerRow.createCell(4).setCellValue("End date");

        int rowNum = 1;
        for (User student : StudentList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(student.getUsername());

            // Tìm điểm của sinh viên trong danh sách điểm và điền vào file Excel
            boolean found = false;
            for (TakeExam score : ScoreList) {
                if (score.getStudentAccountId() == student.getAccountId()) {
                    row.createCell(1).setCellValue(score.getScore());
                    row.createCell(2).setCellValue(score.getStatus() == "1" ? "In progress" : "Done");
                    row.createCell(3).setCellValue(score.getStartDate());
                    row.createCell(4).setCellValue(score.getEndDate());
                    found = true;
                    break;
                }
            }
            if (!found) {
                row.createCell(1).setCellValue("N/A");
                row.createCell(2).setCellValue("N/A");
                row.createCell(3).setCellValue("N/A");
                row.createCell(4).setCellValue("N/A");
            }
        }

        // Ghi workbook vào OutputStream của response
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename="+name+".xlsx");

        try ( OutputStream outputStream = response.getOutputStream()) {
            workbook.write(outputStream);
        } finally {
            workbook.close();
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
