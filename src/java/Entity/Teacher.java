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
public class Teacher implements Serializable{

    private int accountId;
    private String teacherName;
    private String phone;

    public Teacher() {
    }

    public Teacher(int accountId, String teacherName, String phone) {
        this.accountId = accountId;
        this.teacherName = teacherName;
        this.phone = phone;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Teacher{" + "accountId=" + accountId + ", teacherName=" + teacherName + ", phone=" + phone + '}';
    }

}
