/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author phamg
 */
public class UserSet {
 private int userId;
 private int setId;

    public UserSet() {
    }

    public UserSet(int userId, int setId) {
        this.userId = userId;
        this.setId = setId;
    }

    public int getUserId() {
        return userId;
    }

    public int getSetId() {
        return setId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setSetId(int setId) {
        this.setId = setId;
    }

    @Override
    public String toString() {
        return "UserSet{" + "userId=" + userId + ", setId=" + setId + '}';
    }
 
}
