package jave;

/**
 *  Any exceptions related to AudioExtractor
 */
public class AudioExtractorException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    /**
     * @param message the exception message
     * @param cause the cause
     */
    public AudioExtractorException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message the exception message
     */
    public AudioExtractorException(String message) {
        super(message);
    }

    /**
     * @param cause the cause
     */
    public AudioExtractorException(Throwable cause) {
        super(cause);
    }    
}

