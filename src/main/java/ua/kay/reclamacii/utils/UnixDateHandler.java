package ua.kay.reclamacii.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class UnixDateHandler {

    private static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static String toUnixTimeStamp(String date) throws ParseException {
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Kyiv"));
        return String.valueOf(dateFormat.parse(date).getTime() / 1000);
    }

    public static String fromUnixTimeStamp(String timeStamp){
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Kyiv"));
        return dateFormat.format(new Date(Long.valueOf(timeStamp) * 1000));
    }

}
