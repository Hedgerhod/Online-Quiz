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
public class TakeClass implements Serializable{
    int TakeClassId;
    int StudentAccountId;
    int ClassId;

    public TakeClass() {
    }

    public TakeClass(int TakeClassId, int StudentAccountId, int ClassId) {
        this.TakeClassId = TakeClassId;
        this.StudentAccountId = StudentAccountId;
        this.ClassId = ClassId;
    }

    public TakeClass(int StudentAccountId, int ClassId) {
        this.StudentAccountId = StudentAccountId;
        this.ClassId = ClassId;
    }

    public int getTakeClassId() {
        return TakeClassId;
    }

    public void setTakeClassId(int TakeClassId) {
        this.TakeClassId = TakeClassId;
    }

    public int getStudentAccountId() {
        return StudentAccountId;
    }

    public void setStudentAccountId(int StudentAccountId) {
        this.StudentAccountId = StudentAccountId;
    }

    public int getClassId() {
        return ClassId;
    }

    public void setClassId(int ClassId) {
        this.ClassId = ClassId;
    }

    @Override
    public String toString() {
        return "TakeClass{" + "TakeClassId=" + TakeClassId + ", StudentAccountId=" + StudentAccountId + ", ClassId=" + ClassId + '}';
    }

    public TakeClass(int ClassId) {
        this.ClassId = ClassId;
    }
    
}
