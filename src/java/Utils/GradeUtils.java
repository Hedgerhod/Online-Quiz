/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import Entity.questionExam;
import Entity.questionExamAnswer;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hieul
 */
public class GradeUtils {
    public static double grade(List<questionExam> questionList, Map<questionExam, questionExamAnswer> userAnswer) {
        double grade = 0;
        for (questionExam q : questionList) {
            questionExamAnswer answer = userAnswer.get(q);
            if (answer != null && answer.isCorrect()) {
                grade += (q.getScore() * answer.getPercent() * 0.01);
            }
        }
        return grade;
    }
}
