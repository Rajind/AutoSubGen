package sphinx;

import app.TempSub;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import edu.cmu.sphinx.alignment.LongTextAligner;
import edu.cmu.sphinx.api.SpeechAligner;
import edu.cmu.sphinx.result.WordResult;
import edu.cmu.sphinx.util.TimeFrame;
import java.io.IOException;
import java.net.MalformedURLException;

/** 
 * This class is to get timecodes for transcript
 * 
 *  Format (16khz, 16bit, mono)
 *  Text should be a clean text in lower case. from
 *  Punctuation marks, numbers and other non-speakable things.
 *  Timecodes Numbers are times in milliseconds.
 *  little-endian signed integer (lpcm)
 */


public class  TextAligner{
    public TextAligner(){     
    }   
    
    private static final String ACOUSTIC_MODEL_PATH = 
            TextAligner.class.getResource("/resources/models/acoustic/en-us").toString();
    private static final String DICTIONARY_PATH = 
            TextAligner.class.getResource("/resources/models/g2p/en_us/cmudict-5prealpha.dict").toString();
    private static final String G2P_PATH = 
            TextAligner.class.getResource("/resources/models/g2p/en_us/model.fst.ser").toString();
            
    //private static final String TEXT = "one zero zero zero one nine oh two "
    //        + "one oh zero one eight zero three";
    
    /* private static final String TEXT = "I was in India after the World Cup when "
            + "my manager called to pass on the message that CMJ was trying to "
            + "get in touch with me to see whether I would like to deliver this "
            + "year's lecture. I was initially hesitant given the fact we would "
            + "be in the midst of the current ODI series, but after some reflection "
            + "I realised that it was an invitation I should not turn down. To be "
            + "the first Sri Lankan to be invited was not only a great honour for me, "
            + "but also for my fellow countrymen.";  */

    /**
     * 
     * @param transcript String that gives the transcript text
     * @param audioFile java.io.file which gives the WAV file
     * @return List<TempSub> a list with text and timecodes
     * @throws MalformedURLException
     * @throws IOException 
     */
    public static List<TempSub> AllignText(String transcript, String audioFile) 
            throws MalformedURLException, IOException{
        
        URL audioUrl;
        audioUrl = new URL(audioFile);
        TimeFrame t = new TimeFrame(0,0);
        String text;
        List<TempSub> temp_sub_list = new ArrayList<>();
        
        SpeechAligner aligner = new SpeechAligner(ACOUSTIC_MODEL_PATH, DICTIONARY_PATH, G2P_PATH);
        
        //Word result: word, time frame, double score, double confidence
        //Word result: string, double confidence
        List<WordResult> results = aligner.align(audioUrl, transcript);
        List<String> stringResults = new ArrayList<>();
        
        //stringResults list will have a list of words in transcript text.
        for (WordResult wr : results) {
            stringResults.add(wr.getWord().getSpelling());
        }
        
        //creates a long text aligner object with tuple size 1
        LongTextAligner textAligner =
                new LongTextAligner(stringResults, 1);
        
        //words list will have a list of Word objects for the transcript
        List<String> words = aligner.getWordExpander().expand(transcript);

        int[] aid = textAligner.align(words);
        int lastId = -1;
               
        for (int i = 0; i < aid.length; ++i) {
        //iterates over the aid array
            
            if (aid[i] == -1) {
                System.out.format("- %s\n", words.get(i));
                
            } else {
                
                if (aid[i] - lastId > 1) {
                    for (WordResult result : results.subList(lastId + 1,
                            aid[i])) {
                        if(result.getTimeFrame() != null){
                            t = result.getTimeFrame();
                        }else{
                            t = new TimeFrame(0,0);
                        }
                        text = result.getWord().getSpelling();
                        System.out.format("+ %-25s [%s]\n", text , t);
                        temp_sub_list.add(new TempSub(text , t.getStart(), t.getEnd()));
                    }
                } 
                
                if(results.get(aid[i]).getTimeFrame() != null){
                    t = results.get(aid[i]).getTimeFrame();
                }else{
                    t = new TimeFrame(0,0);
                }
                text = results.get(aid[i]).getWord().getSpelling();
                System.out.format("  %-25s [%s]\n", text ,t );
                temp_sub_list.add(new TempSub(text , t.getStart(), t.getEnd()));
                lastId = aid[i];
            }
        }

        if (lastId >= 0 && results.size() - lastId > 1) {
            for (WordResult result : results.subList(lastId + 1,results.size())) {
                if(result.getTimeFrame() != null){
                    t = result.getTimeFrame();
                }else{
                    t = new TimeFrame(0,0);
                }
                text = result.getWord().getSpelling();
                System.out.format("+ %-25s [%s]\n", text , t);
                temp_sub_list.add(new TempSub(text , t.getStart(), t.getEnd()));
            }
        }
        
        // + denotes inserted word and – is for missing word. Numbers are times in milliseconds
        return temp_sub_list;
    }

    
    /*public static void test(){
        URL url = TextAligner.class.getResource("/resources/libvlc");
            if (url == null) {
                System.out.println("not found");
        } else {
                System.out.println("found");
                System.out.println(url.toString());
        }
    }*/
    
    public static void main(String args[]) throws Exception {
        //TextAligner.test();
    }    
}
