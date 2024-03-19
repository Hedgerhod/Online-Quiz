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
public class User implements Serializable{
    private int AccountId;
    private String Username;
    private String Email;
    private String Password;
    private int RoleId;
    private boolean isActive;

    public User() {
    }

    public User(int AccountId, String Username, String Email, String Password, int RoleId, boolean isActive) {
        this.AccountId = AccountId;
        this.Username = Username;
        this.Email = Email;
        this.Password = Password;
        this.RoleId = RoleId;
        this.isActive = isActive;
    }

    public User(String Username, String Email, String Password, int RoleId) {
        this.Username = Username;
        this.Email = Email;
        this.Password = Password;
        this.RoleId = RoleId;
    }

    public User(String Username, String Password) {
        this.Username = Username;
        this.Password = Password;
    }

    

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int AccountId) {
        this.AccountId = AccountId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int RoleId) {
        this.RoleId = RoleId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "User{" +
                "AccountId=" + AccountId +
                ", Username='" + Username + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", RoleId=" + RoleId +
                ", isActive=" + isActive +
                '}';
    }
}