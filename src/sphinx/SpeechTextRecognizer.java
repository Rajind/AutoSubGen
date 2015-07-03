package sphinx;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import edu.cmu.sphinx.result.WordResult;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is to recognize speech when a WAV file is given  
 */
public class SpeechTextRecognizer {
    
    private static StreamSpeechRecognizer recognizer;
    private static Configuration configuration;
    private static SpeechResult result;
    private static String transcript = "";
    
    private static final String ACOUSTIC_MODEL_PATH = 
            TextAligner.class.getResource("/resources/models/acoustic/wsj").toString();
    private static final String DICTIONARY_PATH = 
            TextAligner.class.getResource("/resources/models/dict/cmudict.0.6d").toString();
    private static final String LAUGUAGE_MODEL_PATH = 
            TextAligner.class.getResource("/resources/models/language/wsj20k.dmp").toString();
    
    /**
     * This method will output recognized text for a given WAV file
     * 
     * @param audioPath path of the WAV file
     * @return String containing recognized text (transcript)
     * @throws IOException 
     */
    public static String Recognize(String audioPath) throws IOException{
        //Generic model requires autoCepstrum component in the frontend.
                
        //private static URL url;
        //private static ConfigurationManager cm;
        
        //need to improve configurations
        //need to be able change configurations manually
        
        //url = new URL("file:src\\resources\\hub4_config.xml");
        //cm = new ConfigurationManager(url);
    
        try {
            configuration = new Configuration();
            configuration.setAcousticModelPath(ACOUSTIC_MODEL_PATH);
            configuration.setDictionaryPath(DICTIONARY_PATH);
            configuration.setLanguageModelPath(LAUGUAGE_MODEL_PATH);
            
            try {
                recognizer = new StreamSpeechRecognizer(configuration);
            } catch (IOException ex) {
                ex.printStackTrace();
                Logger.getLogger(SpeechTextRecognizer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            recognizer.startRecognition(new URL(audioPath).openStream());
            
            while ((result = recognizer.getResult()) != null) {
                System.out.format("Hypothesis: %s\n",result.getHypothesis());
                transcript = transcript +" "+ result.getHypothesis();
                System.out.println("List of recognized words and their times:");

                for (WordResult r : result.getWords()) {
                    System.out.println(r);
                }

                System.out.println("Best 3 hypothesis:");            
                for (String s : result.getNbest(3))
                    System.out.println(s);

                System.out.println("Lattice contains " + result.getLattice().getNodes().size() + " nodes");
            }
        recognizer.stopRecognition();
            
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            Logger.getLogger(SpeechTextRecognizer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transcript;
    }
    
    public static void main(String Args[]) throws IOException{
        System.out.println(SpeechTextRecognizer.Recognize("file:src\\resources\\align_test.wav"));
    }
}
