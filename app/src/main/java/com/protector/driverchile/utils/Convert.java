package com.protector.driverchile.utils;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**

 */
public class Convert {

    public static String epochToDate(String epoch){
        long itemLong = (Long.valueOf(epoch)/1000);
        java.util.Date d = new java.util.Date(itemLong*1000L);
        String itemDateStr = new SimpleDateFormat("dd-MMM-yyyy HH:mm").format(d);
        return itemDateStr;
    }

    public static String metersTokilometers(Double meters){
        Double kilometers= meters/1000;
        return String.valueOf(kilometers);
    }

    public static String millisecondToTime (Double millisecond){
        Double seconds= millisecond*0.0010;
        long longVal = seconds.longValue();
        int hours = (int) longVal / 3600;
        int remainder = (int) longVal - hours * 3600;
        int mins = remainder / 60;
        remainder = remainder - mins * 60;
        int secs = remainder;

        String time="";
        if (secs>0){
            time= secs+" s";
        }else {
            time= seconds+" s";
        }

        if (mins>0){
            time= mins+" min";
            if (secs>0){
                time= mins+" min "+secs+" s";
            }
        }

        if (hours>0){
            time= hours+" h";

            if (mins>0){
                time= hours+"h "+ mins+" min";
                if (secs>0){
                    time=  hours+"h "+mins+" min "+secs+" s";
                }
            }
        }

        return time;
    }
}
