package medicaldoctor.util;

/**
 * Runtime wrapper around encryption exception.
 */
public class EncryptionException extends RuntimeException {

    private final Exception baseException;

    public EncryptionException(Exception baseException) {
        super(baseException.getMessage());
        this.baseException = baseException;
    }

    public Exception getBaseException() {
        return baseException;
    }

}
