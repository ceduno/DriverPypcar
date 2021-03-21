package com.protector.driverchile.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@SuppressLint("SimpleDateFormat")
public class DateUtils {
    private static DateUtils instance = null;

    private static NumberFormat formatter;

    public DateUtils() {
        // Exists only to defeat instantiation.
    }

    public static DateUtils getInstance() {
        if (instance == null) {
            instance = new DateUtils();
        }
        return instance;
    }


    public static String obtenerHoraActual() {
        String formato = "HH:mm";
        return obtenerFechaConFormato(formato);
    }

    @SuppressLint("SimpleDateFormat")
    public static String obtenerFechaConFormato(String formato) {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat(formato);
        return sdf.format(date);
    }

    public static boolean validTimeDarkTheme(){
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int  min = c.get(Calendar.MINUTE);
        int ds = c.get(Calendar.AM_PM);
        String AM_PM;
        if(ds==0)
            AM_PM="am";
        else
            AM_PM="pm";

        //Toast.makeText(this, ""+hour+":"+min+AM_PM, Toast.LENGTH_LONG).show();
        if((hour>17  && AM_PM.equals("pm")) || (hour<6 && AM_PM.equals("am"))  || (hour==24 && AM_PM.equals("am")))
        {
           return true;
            //Toast.makeText(MainActivity.this, "Time is between the range", Toast.LENGTH_SHORT).show();
        }
        else{
            return false;
           //Toast.makeText(MainActivity.this, "Time is not between the range", Toast.LENGTH_SHORT).show();
        }

    }

    public String UTC_DATE_FORMAT = "yyyy-mm-dd hh:MM:ss";


    public Date dateFromString(String dateStr, SimpleDateFormat dateFormat) {

        Date date = null;

        try {

            date = dateFormat.parse(dateStr);

        } catch (ParseException e) {

            e.printStackTrace();
            //Logger.debug("Parsing Faied : " + e);
        }

        return date;
    }




}
