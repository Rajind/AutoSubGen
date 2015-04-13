package jave;

import app.FileAdderModel;
import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import poi.TranscriptExtractorException;

/**
 *  A Class to extract audio as WAV from video file
 */
public class AudioExtractor {
    
    private AudioExtractor(){

    }
    /**
     * Extracts audio from given video file
     * @param inFile java.io.file that gives input video file
     * @param audioFile java.io.file that gives output location for WAV
     * @throws AudioExtractorException while reading inFile
     */
    public static void extract(File inFile, File audioFile, FileAdderModel m){
        if (!inFile.exists()) {
            throw new AudioExtractorException(inFile.getAbsolutePath() 
                    + " does not exist");
        }
        if (!inFile.isFile()) {
            throw new AudioExtractorException(inFile.getAbsolutePath() 
                    + " is not a regular file");
        }
        
        try {
            File source = inFile;
            File target = audioFile;
            AudioAttributes audio = new AudioAttributes();
            
            //pcm_s161e is the codec that gives 16 bit 16khz signed mono file
            audio.setCodec("pcm_s16le");
            audio.setBitRate(256000);
            audio.setChannels(1);
            audio.setSamplingRate(16000);
            EncodingAttributes attrs = new EncodingAttributes();
            
            
            if(m.getStart_time() != FileAdderModel.AUTO){
                System.out.println("AudioExtractor: start limit set");
                attrs.setOffset(new Float(m.getStart_time()/1000)); 
                if(m.getEnd_time() != FileAdderModel.AUTO){
                    System.out.println("AudioExtractor: end limit set when start limit is available");
                    attrs.setDuration(new Float((m.getEnd_time() - m.getStart_time())/1000));
                }else{
                    //no need to set if no duration limit is there
                }
            }else{
                if(m.getEnd_time() != FileAdderModel.AUTO){
                    attrs.setDuration(new Float(m.getEnd_time()/1000));
                    System.out.println("AudioExtractor: end limit set");
                }else{
                    //no need to set if no duration limit is there
                }
            }
            //below values are in seconds
            //attrs.setOffset(new Float(40));
            //attrs.setDuration(new Float(34));
            attrs.setFormat("wav");
            attrs.setAudioAttributes(audio);
            Encoder encoder = new Encoder(); 
            
            //Prints the list of codecs available
            /*String arr[] = encoder.getAudioDecoders();
            for(int i=0; i<arr.length; i++)
                System.out.println(arr[i]);
            */
            
            encoder.encode(source, target, attrs);
        } catch (IllegalArgumentException | InputFormatException ex) {
            ex.printStackTrace();
            Logger.getLogger(AudioExtractor.class.getName()).log(Level.SEVERE, null, ex);
            throw new TranscriptExtractorException(ex);
        } catch (EncoderException ex) {
            ex.printStackTrace();
            Logger.getLogger(AudioExtractor.class.getName()).log(Level.SEVERE, null, ex);
            throw new TranscriptExtractorException(ex);
        }
    }    

    public static void main(String Args[]){
        
    }    
}
