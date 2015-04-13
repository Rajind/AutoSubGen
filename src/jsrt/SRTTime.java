package jsrt;

/**
 *
 */
public class SRTTime {
    public final int hour;
    public final int minute;
    public final int second;
    public final int millisecond;

    public SRTTime(int hour, int minute, int second, int millisecond) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.millisecond = millisecond;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public int getMillisecond() {
        return millisecond;
    }
    
}
