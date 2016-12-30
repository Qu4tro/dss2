package Classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

class Utils {

    public static GregorianCalendar parseData(String dd_mm_yy) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
        Date date;
        try {
            date = fmt.parse(dd_mm_yy);
        } catch (ParseException e) {
            date = new Date();
        }
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        cal.setTime(date);
        return cal;
    }
}
