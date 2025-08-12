package vn.edu.ptit.duongvct.discord_bot_test1.util;

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
}
