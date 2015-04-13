package poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * This class is used to write transcript to .docx file
 */
public class TranscriptWriter {
    private TranscriptWriter(){
    }
    
    /**
     * This method writes transcript text to a .docx file
     * 
     * @param transcriptFile output .docx file
     * @param text String which contains transcript text
     * @throws TranscriptWriterException thrown while writing .docx file
     */
    public static void write(File transcriptFile, String text){
        try {
            XWPFDocument document = new XWPFDocument();
            XWPFParagraph tmpParagraph = document.createParagraph();
            XWPFRun tmpRun = tmpParagraph.createRun();
            tmpRun.setText(text);
            tmpRun.setFontSize(12);
            document.write(new FileOutputStream(transcriptFile));
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(TranscriptWriter.class.getName()).log(Level.SEVERE, null, ex);
            throw new TranscriptWriterException(ex);
        }
    }
    
    public static void main(String Args[]){
        TranscriptWriter.write(new File("F:\\hii.docx"), "yes this time this will work");
    }
}
