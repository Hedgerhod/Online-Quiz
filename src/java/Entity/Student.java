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
public class Student implements Serializable{
    int AccountId;
    String StudentName;
    String Phone;
    String DoB;

    public Student() {
    }

    public Student(int AccountId, String StudentName, String Phone, String DoB) {
        this.AccountId = AccountId;
        this.StudentName = StudentName;
        this.Phone = Phone;
        this.DoB = DoB;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int AccountId) {
        this.AccountId = AccountId;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String StudentName) {
        this.StudentName = StudentName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getDoB() {
        return DoB;
    }

    public void setDoB(String DoB) {
        this.DoB = DoB;
    }

    @Override
    public String toString() {
        return "Student{" + "AccountId=" + AccountId + ", StudentName=" + StudentName + ", Phone=" + Phone + ", DoB=" + DoB + '}';
    }
    
}
