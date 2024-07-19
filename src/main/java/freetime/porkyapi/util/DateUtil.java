package freetime.porkyapi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class DateUtil {
        public static String ISO_LOCAL_DATE = "yyyy-MM-dd";
        public static String BRITISH_DATE = "dd-MM-yyyy";
        public static String US_DATE = "MM/dd/yyyy";

    public static boolean dateChecker (String startDateStr, String endDateStr, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(false);

        try {
            GregorianCalendar startDate = new GregorianCalendar();
            startDate.setTime(sdf.parse(startDateStr));

            GregorianCalendar endDate = new GregorianCalendar();
            endDate.setTime(sdf.parse(endDateStr));

            return !startDate.after(endDate);
        } catch (ParseException e) {
            System.out.println("Invalid date format: " + e.getMessage());
            return false;
        }
    }

    public static boolean afterToday(String dateStr, String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(false);

        try {
            GregorianCalendar date = new GregorianCalendar();
            date.setTime(sdf.parse(dateStr));

            GregorianCalendar today = new GregorianCalendar();
            today.setTime(new java.util.Date());

            return date.after(today);
        } catch (ParseException e) {
            System.out.println("Invalid date format: " + e.getMessage());
            return false;
        }
    }
}
