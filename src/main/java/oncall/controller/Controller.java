package oncall.controller;

import static oncall.exception.ExceptionMessage.INVALID_INPUT;
import static oncall.exception.ExceptionMessage.MAX_TRY_ERROR;
import static oncall.view.ErrorPrinter.printError;

import java.util.Arrays;
import java.util.List;
import oncall.domain.Month;
import oncall.domain.Week;
import oncall.exception.OncallException;
import oncall.view.InputView;

public class Controller {

    private static final int MAX_TRY = 5;

    private static final int MONTH_INDEX = 0;
    private static final int DAY_INDEX = 1;

    private static final String DELIMITER = ",";

    public void run() {

        List<String> monthDayInput = readMonthDay();
        Month month = Month.fromName(monthDayInput.get(MONTH_INDEX));
        Week day = Week.fromName(monthDayInput.get(DAY_INDEX));

    }

    private static List<String> readMonthDay() {
        for (int i=1; i<=MAX_TRY; i++) {
            try {
                List<String> input = Arrays.stream(InputView.readMonthDay()
                        .split(DELIMITER))
                        .map(String::trim)
                        .toList();
                validateInput(input);
                return input;
            } catch (IllegalArgumentException e) {
                printError(e.getMessage());
            }
        }
        throw new OncallException(MAX_TRY_ERROR);
    }

    private static void validateInput(List<String> input) {
        validateMonth(input.get(MONTH_INDEX));
        validateDay(input.get(DAY_INDEX));
    }

    private static void validateMonth(String monthInString) {
        Month month = Month.fromName(monthInString);
    }

    private static void validateDay(String dayInString) {
        Week day = Week.fromName(dayInString);
    }
}
