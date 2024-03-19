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
public class Exam implements Serializable{
    private int examId;
    private int classId;
    private int teacherAccountId;
    private String title;
    private String summary;
    private double score;
    private String startDate;
    private String endDate;
    private int timer;
    private int takingTimes;
    private boolean permission;

    public Exam() {
    }

    public Exam(int classId, int teacherAccountId, String title, String summary, double score, String startDate, String endDate, int timer, int takingTimes, boolean permission) {
        this.classId = classId;
        this.teacherAccountId = teacherAccountId;
        this.title = title;
        this.summary = summary;
        this.score = score;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timer = timer;
        this.takingTimes = takingTimes;
        this.permission = permission;
    }
    

    public Exam(int examId, int classId, int teacherAccountId, String title, String summary, double score, String startDate, String endDate, int timer, int takingTimes, boolean permission) {
        this.examId = examId;
        this.classId = classId;
        this.teacherAccountId = teacherAccountId;
        this.title = title;
        this.summary = summary;
        this.score = score;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timer = timer;
        this.takingTimes = takingTimes;
        this.permission = permission;
    }

    public Exam(int examId, String title, String summary, Double score, String startDate, String endDate, int timer, int takingTime, boolean permission) {
        this.examId = examId;
        this.title = title;
        this.summary = summary;
        this.score = score;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timer = timer;
        this.takingTimes = takingTime;
        this.permission = permission; 
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getTeacherAccountId() {
        return teacherAccountId;
    }

    public void setTeacherAccountId(int teacherAccountId) {
        this.teacherAccountId = teacherAccountId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public int getTakingTimes() {
        return takingTimes;
    }

    public void setTakingTimes(int takingTimes) {
        this.takingTimes = takingTimes;
    }

    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }
    

    @Override
    public String toString() {
        return "Exam{" + "examId=" + examId + ", classId=" + classId + ", teacherAccountId=" + teacherAccountId + ", title=" + title + ", summary=" + summary + ", score=" + score + ", startDate=" + startDate + ", endDate=" + endDate + ", timer=" + timer + ", takingTimes=" + takingTimes + ", permission=" + permission + '}';
    }
    
    
}
