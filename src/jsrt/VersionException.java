package jsrt;

/**
 * Any exception related to version.
 */
public class VersionException extends SRTException {
    private static final long serialVersionUID = 1L;

    /**
     * @param message the exception message
     * @param cause the cause
     */
    public VersionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message the exception message
     */
    public VersionException(String message) {
        super(message);
    }

    /**
     * @param cause the cause
     */
    public VersionException(Throwable cause) {
        super(cause);
    }
}
