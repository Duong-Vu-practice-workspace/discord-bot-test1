package vn.edu.ptit.duongvct.discord_bot_test1.util;

import vn.edu.ptit.duongvct.discord_bot_test1.entity.Course;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.CourseMark;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.LearningResource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CommonUtil {
    public static LocalDateTime convertDateTimeFormat(String dateTime) {
        DateTimeFormatter formatterWithSeconds = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        DateTimeFormatter formatterWithoutSeconds = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            return LocalDateTime.parse(dateTime, formatterWithSeconds);
        } catch (DateTimeParseException ex) {
            return LocalDateTime.parse(dateTime, formatterWithoutSeconds);
        }
    }
    public static boolean validateString(String s) {
        return s != null && !s.isBlank();
    }
    public static boolean validateDouble(Double a) {
        return a != null && a != 0.0;
    }
    public static CourseMark validateAndSetNewCourseMark(Double weight, Double score, CourseMark original) {
        if (validateDouble(weight)) {
            original.setWeight(weight);
        }
        if (validateDouble(score)) {
            original.setScore(score);
        }
        return original;
    }
    public static Double calculateFinalScore(Course course) {
        return course.getCc().getWeight() * course.getCc().getScore()
                + course.getTbkt().getWeight() * course.getTbkt().getScore()
                + course.getBttl().getWeight() * course.getBttl().getScore()
                + course.getCk().getWeight() * course.getCk().getScore();
    }
    public static String calculateFinalScoreGrade(Double score) {
        if (score >= 9.0) {
            return "A+";
        } else if (score >= 8.5) {
            return "A";
        } else if (score >= 8.0) {
            return "B+";
        } else if (score >= 7.0) {
            return "B";
        } else if (score >= 6.5) {
            return "C+";
        } else if (score >= 5.5) {
            return "C";
        } else if (score >= 5.0) {
            return "D+";
        } else if (score >= 4.0) {
            return "D";
        }
        return "F";
    }
    public static String getStringNullSafety(String input) {
        return input != null ? input : "N/A";
    }
}
