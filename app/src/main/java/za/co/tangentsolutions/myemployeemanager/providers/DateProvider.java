package za.co.tangentsolutions.myemployeemanager.providers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateProvider {

    public static String getDate() {
        SimpleDateFormat formatedDate = new SimpleDateFormat("y-MM-dd");
        return formatedDate.format(new Date());
    }

    public static String getDateString() {
        SimpleDateFormat formatedDate = new SimpleDateFormat("y-MM-dd");
        String StringDate = formatedDate.format(new Date());
        return StringDate;
    }
}
