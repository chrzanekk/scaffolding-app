package pl.com.chrzanowski.scaffolding.logic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static String formatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return formatter.format(date);

    }

    public static String parseDateTime(LocalDateTime date) {
        if (date == null) {
            return "";
        }
        return date.toString();
    }

    public static  String parseDate(LocalDate date) {
        if (date == null) {
            return "";
        }
        return date.toString();
    }
}
