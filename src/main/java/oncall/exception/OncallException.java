package oncall.exception;

public class OncallException extends IllegalArgumentException {

    public OncallException(final ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getMessage());
    }
}
