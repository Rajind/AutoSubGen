package jsrt;

/**
 * An exception while writing an SRT file.
 */
public class SRTWriterException extends SRTException {
    private static final long serialVersionUID = 1L;

    /**
     * @param message the exception message
     * @param cause the cause
     */
    public SRTWriterException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message the exception message
     */
    public SRTWriterException(String message) {
        super(message);
    }

    /**
     * @param cause the cause
     */
    public SRTWriterException(Throwable cause) {
        super(cause);
    }
}
