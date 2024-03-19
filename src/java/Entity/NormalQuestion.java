/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;

/**
 *
 * @author phamg
 */
public class NormalQuestion implements Serializable{
    private int quesId;
    private String content;
    private int setId;

    public NormalQuestion() {
    }

    public NormalQuestion(String content, int setId) {
        this.content = content;
        this.setId = setId;
    }

    public NormalQuestion(int quesId, String content, int setId) {
        this.quesId = quesId;
        this.content = content;
        this.setId = setId;
    }

    public int getQuesId() {
        return quesId;
    }

    public void setQuesId(int quesId) {
        this.quesId = quesId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSetId() {
        return setId;
    }

    public void setSetId(int setId) {
        this.setId = setId;
    }

    @Override
    public String toString() {
        return "NormalQuestion{" + "quesId=" + quesId + ", content=" + content + ", setId=" + setId + '}';
    }

    
}
