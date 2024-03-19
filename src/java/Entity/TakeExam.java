/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;

/**
 *
 * @author hieul
 */
public class TakeExam implements Serializable{
    private int takeExamId;
    private int studentAccountId;
    private int examId;
    private String status;
    private double score;
    private String StartDate;
    private String endDate;

    public TakeExam() {
    }

    public TakeExam(int takeExamId, int studentAccountId, int examId, String status, double score, String StartDate, String endDate) {
        this.takeExamId = takeExamId;
        this.studentAccountId = studentAccountId;
        this.examId = examId;
        this.status = status;
        this.score = score;
        this.StartDate = StartDate;
        this.endDate = endDate;
    }

    public int getTakeExamId() {
        return takeExamId;
    }

    public void setTakeExamId(int takeExamId) {
        this.takeExamId = takeExamId;
    }

    public int getStudentAccountId() {
        return studentAccountId;
    }

    public void setStudentAccountId(int studentAccountId) {
        this.studentAccountId = studentAccountId;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "TakeExam{" + "takeExamId=" + takeExamId + ", studentAccountId=" + studentAccountId + ", examId=" + examId + ", status=" + status + ", score=" + score + ", StartDate=" + StartDate + ", endDate=" + endDate + '}';
    }
    
    
}
