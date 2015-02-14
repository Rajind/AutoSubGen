package poi;

/**
 *  Any exceptions related to TranscriptWriter
 */
public class TranscriptWriterException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    /**
     * @param message the exception message
     * @param cause the cause
     */
    public TranscriptWriterException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message the exception message
     */
    public TranscriptWriterException(String message) {
        super(message);
    }

    /**
     * @param cause the cause
     */
    public TranscriptWriterException(Throwable cause) {
        super(cause);
    }    
}
