package poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 *This class is to extract transcript text from a .doc , .docx , .txt file
 */
public class TranscriptExtractor {
    private TranscriptExtractor(){    
    }
    
    /**
     * Reads text from a given transcript file returning a String
     * 
     * @param   transcriptFile  the transcriptFile (.doc .docx .txt )
     * @return  string with transcript text
     * @throws TranscriptExtractorException thrown while reading transcript file
    */
    public static String extract(File transcriptFile){
        if (!transcriptFile.exists()) {
            throw new TranscriptExtractorException(transcriptFile.getAbsolutePath() 
                    + " does not exist");
        }
        if (!transcriptFile.isFile()) {
            throw new TranscriptExtractorException(transcriptFile.getAbsolutePath() 
                    + " is not a regular file");
        }
        
        String filename = transcriptFile.getName();
        String extension = filename.substring(filename.lastIndexOf(".") + 1,
                filename.length());
        String transcriptText = "";
                
        switch (extension.toLowerCase()){
            case "docx":
                try {
                    FileInputStream fis;
                    XWPFWordExtractor wordExtractor;

                    fis = new FileInputStream(transcriptFile);
                    XWPFDocument document = new XWPFDocument(fis);          
                    wordExtractor = new XWPFWordExtractor(document);

                    transcriptText = wordExtractor.getText();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(TranscriptExtractor.class.getName()).log(Level.SEVERE, null, ex);
                    throw new TranscriptExtractorException(ex);
                }
                break;
                
            case "doc":
                try {
                    FileInputStream fis;
                    POIFSFileSystem fileSystem;
                    WordExtractor wordExtractor;

                    fis = new FileInputStream(transcriptFile);
                    fileSystem = new POIFSFileSystem(fis);          

                    wordExtractor = new WordExtractor(fileSystem);
                    String[] paragraphText = wordExtractor.getParagraphText();
                    for (String paragraph : paragraphText) {
                        transcriptText = transcriptText + paragraph;
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(TranscriptExtractor.class.getName()).log(Level.SEVERE, null, ex);
                    throw new TranscriptExtractorException(ex);
                }
                break;
                
            case "txt":
                System.out.println(extension);
                try {
                    Scanner scanner;  
                    scanner = new Scanner(transcriptFile);
                    scanner.useDelimiter("\\z"); 
                    transcriptText = scanner.next();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(TranscriptExtractor.class.getName()).log(Level.SEVERE, null, ex);
                    throw new TranscriptExtractorException(transcriptFile.getAbsolutePath() 
                    + " does not exist");
                }
                break;
                
            default:
                throw new TranscriptExtractorException(transcriptFile.getAbsolutePath() 
                        + " is not a text, docx or doc file");
        }
        
        if(transcriptText.equals("")){
            throw new TranscriptExtractorException(transcriptFile.getAbsolutePath() 
                        + " file is empty or file is not read properly");
        }
        return transcriptText.trim();
    }
    
    public static void main(String Args[]){
        System.out.println(TranscriptExtractor.extract(new File("F:\\Legand of Korra\\k.txt")));
    }
}
