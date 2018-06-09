package com.parg.bartian.ui.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ganga_r on 5/29/2017.
 */

public enum  DateUtil {
    INSTANCE;

    public String getDepartureTime(String dateAndTime, int departureMinutesToAdd) {
        try {
            SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a z", Locale.US);
            Calendar departureCalendar = Calendar.getInstance();
            Date departureDate = sdf.parse(dateAndTime);
            departureCalendar.setTime(departureDate);
            departureCalendar.add(Calendar.MINUTE, departureMinutesToAdd);
            return formatDepartureTime(departureCalendar);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String formatDepartureTime(Calendar departureCalendar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm:ss a", Locale.US);
        return simpleDateFormat.format(departureCalendar.getTime());
    }

    public String formatDepartureTime(Date departureDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm:ss a", Locale.US);
        return simpleDateFormat.format(departureDate.getTime());
    }

    public Date getDepartureDate(String dateAndTime, int departureMinutesToAdd) {
        try {
            SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a z", Locale.ENGLISH);
            Calendar departureCalendar = Calendar.getInstance();
            Date departureDate = sdf.parse(dateAndTime);
            departureCalendar.setTime(departureDate);
            departureCalendar.add(Calendar.MINUTE, departureMinutesToAdd);
            return new Date(departureCalendar.getTimeInMillis());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long getRemainingTimeInSec(Date nextTrainDate) {
        Date currentDate = new Date();
        long diff = nextTrainDate.getTime()-currentDate.getTime();
        long diffSeconds = diff/1000;
        return diffSeconds;
    }
}
