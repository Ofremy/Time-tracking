package com.buselorest.util;

public class TimeParser {
    /**
     * Parse incoming time in ms to String format hh:mm:ss
     * @param time - time in ms
     * @return return ms time in String format hh:mm:ss
     */
    public static String parseLongToTimeString(long time){
        long seconds = time / 1000;
        long minutes = 0;
        long hours = 0;
        String result = "";
        while (seconds >= 60){
            seconds-=60;
            minutes++;
        }
        while (minutes >= 60){
            minutes-=60;
            hours++;
        }
        if ((hours+"").length()==1){
            result += "0"+hours +":";
        } else result += hours+":";
        if ((minutes+"").length()==1){
            result += "0"+minutes+":";
        }else result += minutes+":";
        if ((seconds+"").length()==1){
            result += "0"+seconds;
        }else result += seconds+"";
        return result;
    }

    /**
     * Parse incoming time in String to ms
     * @param time - time in String format hh:mm:ss
     * @return return time in ms
     */
    public static long parseStringTimeToLong(String time) throws IllegalStateException{
        if (time!= null) {
            String[] split = time.split(":");
            long result;
            result = Long.parseLong(split[0]) * 60 * 60;
            result += Long.parseLong(split[1]) * 60;
            result += Long.parseLong(split[2]);
            return result*1000;
        }
        return 0;
    }

    public static boolean timeMatchesRegex(String time){
        return time.matches("[0-9]{2}:[0-9]{2}:[0-9]{2}");
    }
}
