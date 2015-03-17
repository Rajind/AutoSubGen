package jsrt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class contains utility methods for SRT time format related stuff.
 */
public class SRTTimeFormat {
    public static final String TIME_DELIMITER = " --> ";
    public static final String TIME_FORMAT = "HH:mm:ss,SSS";
    public static final String HOUR_FORMAT = "HH";
    public static final String MINUTE_FORMAT = "mm";
    public static final String SECOND_FORMAT = "ss";
    public static final String MILLISECOND_FORMAT = "SSS";
    private static final SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
    
    public enum Type {
        HOUR,
        MINUTE,
        SECOND,
        MILLISECOND
    }
    
    private SRTTimeFormat() {
    }
    
    /**
     * Formats the date into SRT time format.
     * @param time the SRTTime time
     * @return the SRT time format
     */
    public static String format(SRTTime time) {
        //00:01:15,120
        return time.hour+":"+time.minute+":"+time.second+","+time.millisecond;
    }
    
    /**
     * Parses the SRT time format into date.
     * @param srtTime the SRT time format
     * @return the date
     * @throws ParseException
     */
    public static SRTTime parse(String srtTime) throws ParseException {
        String [] hs = srtTime.split(":");
        String [] mm = hs[2].split(",");
        return new SRTTime(Integer.parseInt(hs[0]), Integer.parseInt(hs[1]),
                Integer.parseInt(mm[0]),Integer.parseInt(mm[1]));
    }
    
    /**
     * Converts Date to SRTTime.
     * 
     * @param date the date
     * @return the SRTTime
     */
    public static SRTTime toSRTTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return new SRTTime(
            cal.get(Calendar.HOUR),
            cal.get(Calendar.MINUTE),
            cal.get(Calendar.SECOND),
            cal.get(Calendar.MILLISECOND));
    }
    
    /**
     * Converts SRTTime to Date.
     * 
     * @param srtTime the SRTTime
     * @return the Date
     * @throws ParseException
     */
    public static String SRTTimeToString(SRTTime srtTime) throws ParseException {
        StringBuilder timeStr = new StringBuilder();
        if (srtTime.hour < 10) {
            timeStr.append("0");
        }
        timeStr.append(Integer.toString(srtTime.hour));
        timeStr.append(":");
        
        if (srtTime.minute < 10) {
            timeStr.append("0");
        }
        timeStr.append(Integer.toString(srtTime.minute));
        timeStr.append(":");
        
        if (srtTime.second < 10) {
            timeStr.append("0");
        }
        timeStr.append(Integer.toString(srtTime.second));
        timeStr.append(",");
        
        if (srtTime.millisecond < 10) {
            timeStr.append("00");
        } else if (srtTime.millisecond < 100) {
            timeStr.append("0");
        }
        timeStr.append(Integer.toString(srtTime.millisecond));
        
        return timeStr.toString();
    }
    
    public static SRTTime longToSRTTime(long time){
        long t;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MILLISECOND, (int) (time%1000));
        t = time/1000;
        int []arr = new int[3];
        for(int i=0; i< 3; i++){
            arr[i] = (int) (t%60);
            t = t/60;
        }
        cal.set(Calendar.SECOND,arr[0]);
        cal.set(Calendar.MINUTE,arr[1]);
        cal.set(Calendar.HOUR,arr[2]);
        return new SRTTime(cal.get(Calendar.HOUR),cal.get(Calendar.MINUTE),
                cal.get(Calendar.SECOND), cal.get(Calendar.MILLISECOND));
    }
    
    public static void main(String Args[]) throws ParseException{
        /*SRTTime time = SRTTimeFormat.longToSRTTime(160078);
        System.out.println(time.getHour() + ":"+time.getMinute()+
                ":"+time.getSecond()+":"+time.getMillisecond());
        */
        
        SRTTime time = SRTTimeFormat.parse("00:01:15,120");
        System.out.println(time.getHour() + ":"+time.getMinute()+
                ":"+time.getSecond()+","+time.getMillisecond());
        
        
        //System.out.println(SRTTimeFormat.format(new SRTTime(01,23,45,900)));
        
        
    }
}
