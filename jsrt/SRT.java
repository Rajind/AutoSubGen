package jsrt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * A class to store SRT information.
 * 
 */
public class SRT implements Comparable<SRT> {
    public final int number;
    public final SRTTime startTime;
    public final SRTTime endTime;
    public final List<String> text;
    
    /**
     * Creates a new instance of SRT.
     * 
     * @param number the subtitle number
     * @param startTime the start time
     * @param endTime the end time
     * @param text the subtitle text
     */
    public SRT(int number, SRTTime startTime, SRTTime endTime, String... text) {
        this.number = number;
        this.startTime = startTime;
        this.endTime = endTime;
        this.text = new ArrayList<>(Arrays.asList(text));
    }
    
    /**
     * Creates a new instance of SRT.
     * 
     * @param number the subtitle number
     * @param startTime the start time
     * @param endTime the end time
     * @param text the subtitle text
     */
    public SRT(int number, SRTTime startTime, SRTTime endTime, List<String> text) {
        this.number = number;
        this.startTime = startTime;
        this.endTime = endTime;
        this.text = new ArrayList<>(text);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (number ^ (number >>> 32));
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SRT other = (SRT) obj;
        if (number != other.number)
            return false;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(SRT o) {
        return new Integer(number).compareTo(o.number);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SRT [number=").append(number).append(", startTime=")
            .append(startTime).append(", endTime=").append(endTime).append(", text=")
            .append(text).append("]");
        return builder.toString();
    }
}
