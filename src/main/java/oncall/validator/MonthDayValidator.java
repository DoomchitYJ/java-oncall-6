package oncall.validator;

import static oncall.exception.ExceptionMessage.INVALID_INPUT;

import java.util.List;
import oncall.domain.Month;
import oncall.domain.Week;
import oncall.exception.OncallException;

public class MonthDayValidator {

    private static final int MONTH_INDEX = 0;
    private static final int DAY_INDEX = 1;

    public static void validate(List<String> input) {
        validateMonth(input.get(MONTH_INDEX));
        validateDay(input.get(DAY_INDEX));
    }

    private static void validateMonth(String month) {
        if (!Month.isValid(month)) {
            throw new OncallException(INVALID_INPUT);
        }
    }

    private static void validateDay(String day) {
        if (!Week.isValid(day)) {
            throw new OncallException(INVALID_INPUT);
        }
    }

}
