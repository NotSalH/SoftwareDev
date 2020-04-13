package medicaldoctor.util;

public class InitializeException extends RuntimeException {

    private final Exception baseException;

    public InitializeException(Exception baseException) {
        super(baseException.getMessage());
        this.baseException = baseException;
    }

    public Exception getBaseException() {
        return baseException;
    }

}
