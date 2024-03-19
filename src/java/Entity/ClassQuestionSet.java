/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;

/**
 *
 * @author admin
 */
public class ClassQuestionSet implements Serializable{
    int ClassSetId;
    int ClassId;
    int SetId;

    public ClassQuestionSet() {
    }

    public ClassQuestionSet(int ClassSetId, int ClassId, int SetId) {
        this.ClassSetId = ClassSetId;
        this.ClassId = ClassId;
        this.SetId = SetId;
    }

    public ClassQuestionSet(int ClassId, int SetId) {
        this.ClassId = ClassId;
        this.SetId = SetId;
    }

    public int getClassSetId() {
        return ClassSetId;
    }

    public void setClassSetId(int ClassSetId) {
        this.ClassSetId = ClassSetId;
    }

    public int getClassId() {
        return ClassId;
    }

    public void setClassId(int ClassId) {
        this.ClassId = ClassId;
    }

    public int getSetId() {
        return SetId;
    }

    public void setSetId(int SetId) {
        this.SetId = SetId;
    }

    @Override
    public String toString() {
        return "ClassQuestionSet{" + "ClassSetId=" + ClassSetId + ", ClassId=" + ClassId + ", SetId=" + SetId + '}';
    }
    
}
