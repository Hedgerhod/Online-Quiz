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
public class questionExamAnswer implements Serializable {

    private int AnswerId;
    private int QuesId;
    private String Content;
    private boolean Correct;
    private double Percent;

    public questionExamAnswer() {
    }

    public questionExamAnswer(int AnswerId, int QuesId, String Content, boolean Correct, double Percent) {
        this.AnswerId = AnswerId;
        this.QuesId = QuesId;
        this.Content = Content;
        this.Correct = Correct;
        this.Percent = Percent;
    }

    public questionExamAnswer(int QuesId, String Content, boolean Correct, double Percent) {
        this.QuesId = QuesId;
        this.Content = Content;
        this.Correct = Correct;
        this.Percent = Percent;
    }

    public int getAnswerId() {
        return AnswerId;
    }

    public void setAnswerId(int AnswerId) {
        this.AnswerId = AnswerId;
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

    public boolean isCorrect() {
        return Correct;
    }

    public void setCorrect(boolean Correct) {
        this.Correct = Correct;
    }

    public double getPercent() {
        return Percent;
    }

    public void setPercent(double Percent) {
        this.Percent = Percent;
    }

    @Override
    public String toString() {
        return "quetionExamAnswer{" + "AnswerId=" + AnswerId + ", QuesId=" + QuesId + ", Content=" + Content + ", Correct=" + Correct + ", Percent=" + Percent + '}';
    }

}
