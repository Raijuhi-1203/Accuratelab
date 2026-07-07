package digitalbull.app.accuratelab.Services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormate {

    public static String getYear(String raw) {
        String inputPattern = "dd-MMM-yyyy";
        String outputPattern = "yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(raw);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
       return  str;
    }

    public static String getMonth(String raw) {
        String inputPattern = "dd-MMM-yyyy";
        String outputPattern = "MM";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(raw);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  str;
    }

    public static String getDay(String raw) {
        String inputPattern = "dd-MMM-yyyy";
        String outputPattern = "dd";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(raw);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  str;
    }
}
