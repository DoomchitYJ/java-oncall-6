package oncall.controller;

import static oncall.exception.ExceptionMessage.INVALID_INPUT;
import static oncall.exception.ExceptionMessage.MAX_TRY_ERROR;
import static oncall.view.ErrorPrinter.printError;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import oncall.domain.Month;
import oncall.domain.Week;
import oncall.domain.WorkerPlacer;
import oncall.exception.OncallException;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Controller {

    private static final int MAX_TRY = 5;

    private static final int MONTH_INDEX = 0;
    private static final int DAY_INDEX = 1;

    private static final int WEEKDAY_WORKERS_INDEX = 0;
    private static final int WEEKEND_WORKERS_INDEX = 1;

    private static final int NAME_LENGTH_MIN = 1;
    private static final int NAME_LENGTH_MAX = 5;

    private static final int NAME_COUNT_MIN = 5;
    private static final int NAME_COUNT_MAX = 35;

    private static final String DELIMITER = ",";

    public void run() {

        List<String> monthDayInput = readMonthDay();
        Month month = Month.fromName(monthDayInput.get(MONTH_INDEX));
        Week day = Week.fromName(monthDayInput.get(DAY_INDEX));

        List<List<String>> workers = readWorkers();
        List<String> weekdayWorkers = workers.get(WEEKDAY_WORKERS_INDEX);
        List<String> weekendWorkers = workers.get(WEEKEND_WORKERS_INDEX);

        WorkerPlacer workerPlacer = new WorkerPlacer(month, day, weekdayWorkers, weekendWorkers);
        List<String> result = workerPlacer.place();
        OutputView.showResult(result);
    }

    private static List<String> readMonthDay() {
        for (int i=1; i<=MAX_TRY; i++) {
            try {
                List<String> input = Arrays.stream(InputView.readMonthDay()
                        .split(DELIMITER))
                        .map(String::trim)
                        .toList();
                validateMonthDay(input);
                return input;
            } catch (IllegalArgumentException e) {
                printError(e.getMessage());
            }
        }
        throw new OncallException(MAX_TRY_ERROR);
    }

    private static void validateMonthDay(List<String> input) {
        validateMonth(input.get(MONTH_INDEX));
        validateDay(input.get(DAY_INDEX));
    }

    private static void validateMonth(String monthInString) {
        Month month = Month.fromName(monthInString);
    }

    private static void validateDay(String dayInString) {
        Week day = Week.fromName(dayInString);
    }

    private static List<List<String>> readWorkers() {
        for (int i=1; i<=MAX_TRY; i++) {
            try {
                List<String> input = InputView.readWorkers();
                List<String> weekdayWorkers = Arrays.stream(input.get(WEEKDAY_WORKERS_INDEX)
                        .split(DELIMITER))
                        .map(String::trim)
                        .toList();
                validateWorkers(weekdayWorkers);
                List<String> weekendWorkers = Arrays.stream(input.get(WEEKEND_WORKERS_INDEX)
                                .split(DELIMITER))
                        .map(String::trim)
                        .toList();
                validateWorkers(weekendWorkers);
                return Arrays.asList(weekdayWorkers, weekendWorkers);
            } catch (IllegalArgumentException e) {
                printError(e.getMessage());
            }
        }
        throw new OncallException(MAX_TRY_ERROR);
    }

    private static void validateWorkers(List<String> workers) {
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
