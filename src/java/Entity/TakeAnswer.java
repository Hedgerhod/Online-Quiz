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
public class TakeAnswer implements Serializable{
    private int TakeAnswerId;
    private int TakeExamId;
    private int QuesId;
    private int AnswerId;

    public TakeAnswer() {
    }

    public TakeAnswer(int TakeAnswerId, int TakeExamId, int QuesId, int AnswerId) {
        this.TakeAnswerId = TakeAnswerId;
        this.TakeExamId = TakeExamId;
        this.QuesId = QuesId;
        this.AnswerId = AnswerId;
    }

    public int getTakeAnswerId() {
        return TakeAnswerId;
    }

    public void setTakeAnswerId(int TakeAnswerId) {
        this.TakeAnswerId = TakeAnswerId;
    }

    public int getTakeExamId() {
        return TakeExamId;
    }

    public void setTakeExamId(int TakeExamId) {
        this.TakeExamId = TakeExamId;
    }

    public int getQuesId() {
        return QuesId;
    }

    public void setQuesId(int QuesId) {
        this.QuesId = QuesId;
    }

    public int getAnswerId() {
        return AnswerId;
    }

    public void setAnswerId(int AnswerId) {
        this.AnswerId = AnswerId;
    }

    @Override
    public String toString() {
        return "takeAnswer{" + "TakeAnswerId=" + TakeAnswerId + ", TakeExamId=" + TakeExamId + ", QuesId=" + QuesId + ", AnswerId=" + AnswerId + '}';
    }
    
}
