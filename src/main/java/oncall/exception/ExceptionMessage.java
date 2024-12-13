package oncall.exception;

public enum ExceptionMessage {

    INVALID_INPUT("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    MAX_TRY_ERROR("최대 입력 가능 횟수를 초과했습니다.");

    private static final String ERROR_PREFIX = "[ERROR] ";

    private final String message;

    ExceptionMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }

}
