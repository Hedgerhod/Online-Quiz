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
public class Role implements Serializable{
    private int RoleId;
    private String Role;

    public Role() {
    }

    public Role(int RoleId, String Role) {
        this.RoleId = RoleId;
        this.Role = Role;
    }

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int RoleId) {
        this.RoleId = RoleId;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    @Override
    public String toString() {
        return "role{" + "RoleId=" + RoleId + ", Role=" + Role + '}';
    }
    
}
