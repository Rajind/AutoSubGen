package jsrt;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

/**
 * This class is responsible for writing an SRT file.
 */
public class SRTWriter {
    public SRTWriter() {
    }
    
    /**
     * Writes an SRT file from an SRT object.
     * 
     * @param srtFile the SRT file
     * @param srtInfo the SRTInfo object
     * @throws SRTWriterException thrown while writing an SRT file
     */
    public static void write(File srtFile, SRTInfo srtInfo) throws SRTWriterException, ParseException {
        try (PrintWriter pw = new PrintWriter(srtFile)) {
            for (SRT srt : srtInfo) {
                pw.println(srt.number);
                pw.println(
                    SRTTimeFormat.SRTTimeToString(srt.startTime) +
                    SRTTimeFormat.TIME_DELIMITER +
                    SRTTimeFormat.SRTTimeToString(srt.endTime));
                for (String text : srt.text) {
                    pw.println(text);
                }
                // Add an empty line at the end
                pw.println();
            }
        } catch (IOException e) {
            throw new SRTWriterException(e);
        }
    }
}
