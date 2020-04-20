package posProjectForTuring.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {
    private static Date date = new Date();
    
    public static Timestamp getCurrentTime(){
        return new Timestamp(DateTimeUtils.date.getTime());
    }
    
    public static String getStartDateString(Date date){
        return (""
                + (date.getYear()+1900)
                + "-"
                + (date.getMonth()+1)
                + "-"
                + date.getDate()
                + " 00:00:00"
        );
    }
    
    public static String getEndDateString(Date date){
        return (""
                + (date.getYear()+1900)
                + "-"
                + (date.getMonth()+1)
                + "-"
                + date.getDate()
                + " 23:59:59"
        );
    }
    
    public static String getStartMonthString(int month){
        return (""
                +DateTimeUtils.getCurrentYear()
                +"-"
                +(month+1)
                +"-1 00:00:00"
        );
    }
    
    public static String getEndMonthString(int month){
        if(DateTimeUtils.isLeapYear(DateTimeUtils.getCurrentYear())){
            return (""
                +DateTimeUtils.getCurrentYear()
                + "-" 
                +(month+1)
                + "-"
                + LAST_DAY_OF_MONTH_LEAP[(month+1)]
                + " 23:59:59"
            );
        }
        else{
            return (""
                +DateTimeUtils.getCurrentYear()
                + "-" 
                +(month+1)
                + "-"
                + LAST_DAY_OF_MONTH[(month+1)]
                + " 23:59:59"
            );
        }
    }
    
    public static String getStartYearString(int year){
        return (""
            +year
            + "-01-31 00:00:00" 
        );
    }
    
    public static String getEndYearString(int year){
        return (""
            +year
            + "-12-31 23:59:59" 
        );
    }
    
    public static int getCurrentYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }
    
    public static int getCurrentMonth(){
        return (Calendar.getInstance().get(Calendar.MONTH)+1);
    }
    
    public static boolean isLeapYear(int year){
        if(year % 400 == 0){
            return true;
        }
        else if (year % 100 == 0){
            return false;
        }
        else if(year % 4 == 0){
            return true;
        }
        else{
            return false;
        }
    }
    
    private static final byte[] LAST_DAY_OF_MONTH = {
        0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };
    private static final byte[] LAST_DAY_OF_MONTH_LEAP = {
        0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };
    
    public static String getCurrentMonthStart(){
        return (""
                 +
                DateTimeUtils.getCurrentYear()
                + "-" +
                DateTimeUtils.getCurrentMonth()
                + "-1 00:00:00"
        );
    }
    
    public static String getCurrentMonthEnd(){
        if(DateTimeUtils.isLeapYear(DateTimeUtils.getCurrentYear())){
            return (""
                +DateTimeUtils.getCurrentYear()
                + "-" 
                +DateTimeUtils.getCurrentMonth()
                + "-"
                + LAST_DAY_OF_MONTH_LEAP[DateTimeUtils.getCurrentMonth()]
                + " 23:59:59"
            );
        }
        else{
            return (""
                +DateTimeUtils.getCurrentYear()
                + "-" 
                +DateTimeUtils.getCurrentMonth()
                + "-"
                + LAST_DAY_OF_MONTH[DateTimeUtils.getCurrentMonth()]
                + " 23:59:59"
            );
        }
    }
}
