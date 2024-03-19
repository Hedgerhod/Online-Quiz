/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;

/**
 *
 * @author ACER
 */
public class questionExam implements Serializable{
    private int ExamId;
    private int QuesId;
    private String Content;
    private double Score;

    public questionExam() {
    }

    public questionExam(int ExamId, int QuesId, String Content, double Score) {
        this.ExamId = ExamId;
        this.QuesId = QuesId;
        this.Content = Content;
        this.Score = Score;
    }
    
    public questionExam(int ExamId, String Content, double Score) {
        this.ExamId = ExamId;
        this.Content = Content;
        this.Score = Score;
    }
    
    public int getExamId() {
        return ExamId;
    }

    public void setExamId(int ExamId) {
        this.ExamId = ExamId;
    }

    public int getQuesId() {
        return QuesId;
    }

    public void setQuesId(int QuesId) {
        this.QuesId = QuesId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public double getScore() {
        return Score;
    }

    public void setScore(double Score) {
        this.Score = Score;
    }

    @Override
    public String toString() {
        return "quetionExam{" + "ExamId=" + ExamId + ", QuesId=" + QuesId + ", Content=" + Content + ", Score=" + Score + '}';
    }
    
}
