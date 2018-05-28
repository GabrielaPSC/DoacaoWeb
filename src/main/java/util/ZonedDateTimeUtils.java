package util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Gabriela Santos
 */
public class ZonedDateTimeUtils {
    
    public static String toDateString(ZonedDateTime zonedDateTime) {
        return zonedDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
            
}
