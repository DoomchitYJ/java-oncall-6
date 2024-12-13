package oncall.validator;

import static oncall.exception.ExceptionMessage.INVALID_INPUT;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import oncall.exception.OncallException;

public class NameValidator {

    private static final int NAME_LENGTH_MIN = 1;
    private static final int NAME_LENGTH_MAX = 5;

    private static final int NAME_COUNT_MIN = 5;
    private static final int NAME_COUNT_MAX = 35;

    public static void validate(List<String> workers) {
        validateNamesLength(workers);
        validateNamesDuplicate(workers);
        validateNamesCount(workers);
    }

    private static void validateNamesLength(List<String> workers) {
        for (String worker : workers) {
            if (worker.length() < NAME_LENGTH_MIN || worker.length() > NAME_LENGTH_MAX) {
                throw new OncallException(INVALID_INPUT);
            }
        }
    }

    private static void validateNamesDuplicate(List<String> workers) {
        Set<String> workersSet = new HashSet<>(workers);
        if (workersSet.size() != workers.size()) {
            throw new OncallException(INVALID_INPUT);
        }
    }

    private static void validateNamesCount(List<String> workers) {
        if (workers.size() < NAME_COUNT_MIN || workers.size() > NAME_COUNT_MAX) {
            throw new OncallException(INVALID_INPUT);
        }
    }
}
