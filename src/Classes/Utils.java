package Classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Utils {

    public static String formatGreg(GregorianCalendar g){
        return g.get(GregorianCalendar.DAY_OF_MONTH) + " " +
               g.get(GregorianCalendar.MONTH)        + " " +
               g.get(GregorianCalendar.YEAR)         + " ";
    }

    public static GregorianCalendar dataAgora(){
        return new GregorianCalendar();
    }

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
    
    public static int strToInt( String str ){
    int i = 0;
    int num = 0;
    boolean isNeg = false;

    //Check for negative sign; if it's there, set the isNeg flag
    if (str.charAt(0) == '-') {
        isNeg = true;
        i = 1;
    }

    //Process each character of the string;
    while( i < str.length()) {
        num *= 10;
        num += str.charAt(i++) - '0'; //Minus the ASCII code of '0' to get the value of the charAt(i++).
    }

    if (isNeg)
        num = -num;
    return num;
    }
}
