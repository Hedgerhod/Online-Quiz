/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;

/**
 *
 * @author Asus
 */
public class Admin implements Serializable{
    private int AccountId;
    private String AdminName;
    private String Phone;

    public Admin() {
    }

    public Admin(int AccountId, String AdminName, String Phone) {
        this.AccountId = AccountId;
        this.AdminName = AdminName;
        this.Phone = Phone;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int AccountId) {
        this.AccountId = AccountId;
    }

    public String getAdminName() {
        return AdminName;
    }

    public void setAdminName(String AdminName) {
        this.AdminName = AdminName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    @Override
    public String toString() {
        return "admin{" + "AccountId=" + AccountId + ", AdminName=" + AdminName + ", Phone=" + Phone + '}';
    }
    
    
}
