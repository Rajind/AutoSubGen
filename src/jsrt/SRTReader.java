package jsrt;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for reading an SRT file.
 */
public class SRTReader {
    public SRTReader() {
    }
    
    /**
     * Reads an SRT file and transforming it into SRT object.
     * 
     * @param srtFile SRT file
     * @return the SRTInfo object
     * @throws InvalidSRTException thrown when the SRT file is invalid
     * @throws SRTReaderException thrown while reading SRT file
     */
    public static SRTInfo read(File srtFile) throws InvalidSRTException, SRTReaderException {
        if (!srtFile.exists()) {
            throw new SRTReaderException(srtFile.getAbsolutePath() + " does not exist");
        }
        if (!srtFile.isFile()) {
            throw new SRTReaderException(srtFile.getAbsolutePath() + " is not a regular file");
        }
        
        SRTInfo srtInfo = new SRTInfo();
        try (BufferedReader br = new BufferedReader(new FileReader(srtFile))) {
            while (true) {
                srtInfo.add(parse(br));
            }
        } catch (EOFException e) {
            // Do nothing
        } catch (IOException e) {
            e.printStackTrace();
            throw new SRTReaderException(e);
            
        }
        return srtInfo;
    }
    
    public static String readText(File srtFile){
        String transcript = "";
        SRTInfo info = read(srtFile);
        for (SRT s : info) {
            for (String line : s.text) {
                transcript = transcript + " " + line;
            }
        }
        return transcript;
    }
    
    public static String getText(SRTInfo info){
        String transcript = "";
        for (SRT s : info) {
            for (String line : s.text) {
                transcript = transcript + " " + line;
            }
        }
        return transcript;
    }
    
        public static void print(SRTInfo info) throws ParseException {
        for (SRT s : info) {
            System.out.println("Number: " + s.number);
            System.out.println("Start time: " + SRTTimeFormat.SRTTimeToString(s.startTime));
            System.out.println("End time: " + SRTTimeFormat.SRTTimeToString(s.endTime));
            System.out.println("Texts:");
            for (String line : s.text) {
                System.out.println("    " + line);
            }
            System.out.println();
        }
    }
        
    private static SRT parse(BufferedReader br) throws IOException, EOFException {
        String nString = br.readLine();
        if (nString == null) {
            throw new EOFException();
        }
        
        int subtitleNumber = -1;
        try {
            subtitleNumber = Integer.parseInt(nString);
        } catch (NumberFormatException e) {
            throw new InvalidSRTException(
                nString + " has an invalid subtitle number");
        }
        
        String tString = br.readLine();
        if (tString == null) {
            throw new InvalidSRTException(
                "Start time and end time information is not present");
        }
        String[] times = tString.split(SRTTimeFormat.TIME_DELIMITER);
        if (times.length != 2) {
            throw new InvalidSRTException(
                tString + " needs to be seperated with " + SRTTimeFormat.TIME_DELIMITER);
        }
        SRTTime startTime = null;
        try {
            startTime = SRTTimeFormat.parse(times[0]);
        } catch (ParseException e) {
            throw new InvalidSRTException(
                times[0] + " has an invalid time format");
        }
        
        SRTTime endTime = null;
        try {
            endTime = SRTTimeFormat.parse(times[1]);
        } catch (ParseException e) {
            throw new InvalidSRTException(
                times[1] + " has an invalid time format");
        }
        
        List<String> subtitleLines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) {
                break;
            }
            subtitleLines.add(line);
        }
        
        if (subtitleLines.isEmpty()) {
            throw new InvalidSRTException("Missing subtitle text information");
        }
        
        return new SRT(subtitleNumber, startTime, endTime, subtitleLines);
    }
}
