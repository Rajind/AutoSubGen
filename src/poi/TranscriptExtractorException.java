package poi;

/**
 *  Any exceptions related to TranscriptExtractor
 */
public class TranscriptExtractorException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    /**
     * @param message the exception message
     * @param cause the cause
     */
    public TranscriptExtractorException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message the exception message
     */
    public TranscriptExtractorException(String message) {
        super(message);
    }

    /**
     * @param cause the cause
     */
    public TranscriptExtractorException(Throwable cause) {
        super(cause);
    }    
}
