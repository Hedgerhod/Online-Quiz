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
public class Subject implements Serializable{

    private int SubjectId;
    private String SubjectCode;
    private String SubjectName;

    public Subject() {
    }

    public Subject(String SubjectCode, String SubjectName) {
        this.SubjectCode = SubjectCode;
        this.SubjectName = SubjectName;
    }

    public Subject(int SubjectId, String SubjectCode, String SubjectName) {
        this.SubjectId = SubjectId;
        this.SubjectCode = SubjectCode;
        this.SubjectName = SubjectName;
    }

    public int getSubjectId() {
        return SubjectId;
    }

    public String getSubjectCode() {
        return SubjectCode;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectId(int SubjectId) {
        this.SubjectId = SubjectId;
    }

    public void setSubjectCode(String SubjectCode) {
        this.SubjectCode = SubjectCode;
    }

    public void setSubjectName(String SubjectName) {
        this.SubjectName = SubjectName;
    }

    @Override
    public String toString() {
        return "Subject{" + "SubjectId=" + SubjectId + ", SubjectCode=" + SubjectCode + ", SubjectName=" + SubjectName + '}';
    }

}
