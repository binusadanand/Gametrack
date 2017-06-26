package com.igt.binu.gametrack.Utils;

import android.text.TextUtils;
import org.ocpsoft.prettytime.PrettyTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by binusadanand on 25/06/2017.
 */

public class DateConverter {

    private static final String SUPPORTED_TIME_FORMAT_1 = "dd/MM/YYYY'T'HH:mm";
    private static final String SUPPORTED_TIME_FORMAT_2 = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final String FRIENDLY_TIME_FORMAT = "dd-MMM-yyyy h:mm a";

    public static String PrettyFromStr(String  aTimeStr) {

        Date aDate = null;
        SimpleDateFormat format = new SimpleDateFormat(SUPPORTED_TIME_FORMAT_1, Locale.ENGLISH);
        try {
            aDate = format.parse(aTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (aDate != null) {
            PrettyTime p = new PrettyTime();
            return (p.format(aDate));
        } else {
            return aTimeStr;
        }
    }

    public static String FriendlyFromStr(String aTimeStr) {
        SimpleDateFormat inputFormat = new SimpleDateFormat(SUPPORTED_TIME_FORMAT_2, Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat(FRIENDLY_TIME_FORMAT, Locale.ENGLISH);

        Date aDate;
        String aStr = null;

        try {
            aDate = inputFormat.parse(aTimeStr);
            aStr = outputFormat.format(aDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(!TextUtils.isEmpty(aStr)) {
            return aStr;
        } else {
            return aTimeStr;
        }
    }

}
