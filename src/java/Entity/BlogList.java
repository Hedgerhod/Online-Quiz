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
public class BlogList implements Serializable{
    private int SubjectId;
    private int SetId;
    private String Title;
    private String Username;
    private int SetVote;

    public BlogList() {
    }

    public BlogList(int SubjectId, int SetId, String Title, String Username, int SetVote) {
        this.SubjectId = SubjectId;
        this.SetId = SetId;
        this.Title = Title;
        this.Username = Username;
        this.SetVote = SetVote;
    }

    public int getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(int SubjectId) {
        this.SubjectId = SubjectId;
    }

    public int getSetId() {
        return SetId;
    }

    public void setSetId(int SetId) {
        this.SetId = SetId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public int getSetVote() {
        return SetVote;
    }

    public void setSetVote(int SetVote) {
        this.SetVote = SetVote;
    }

    @Override
    public String toString() {
        return "BlogList{" + "SubjectId=" + SubjectId + ", SetId=" + SetId + ", Title=" + Title + ", Username=" + Username + ", SetVote=" + SetVote + '}';
    }

   

    
}
