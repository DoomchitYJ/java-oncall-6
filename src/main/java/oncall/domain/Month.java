package oncall.domain;

import static oncall.domain.Holiday.*;
import static oncall.exception.ExceptionMessage.INVALID_INPUT;

import java.util.List;
import oncall.exception.OncallException;

public enum Month {

    JAN("1", 31, List.of(NEWYEAR)),
    FEB("2", 28, List.of()),
    MAR("3", 31, List.of(MOVEMENT)),
    APR("4", 30, List.of()),
    MAY("5", 31, List.of(CHILDREN)),
    JUN("6", 30, List.of(MEMORIAL)),
    JUL("7", 31, List.of()),
    AUG("8", 31, List.of(LIBERATION)),
    SEP("9", 30, List.of()),
    OCT("10", 31, List.of(FOUNDATION, KOREAN)),
    NOV("11", 30, List.of()),
    DEC("12", 31, List.of(CHRISTMAS));

    private final String name;
    private final int endDay;
    private final List<Holiday> holidays;

    Month(String name, int endDay, List<Holiday> holidays) {
        this.name = name;
        this.endDay = endDay;
        this.holidays = holidays;
    }

    public String getName() {
        return name;
    }
    public int getEndDay() {
        return endDay;
    }
    public List<Holiday> getHolidays() {
        return holidays;
    }

    public static Month fromName(String name) {
        for (Month month : values()) {
            if (month.getName().equals(name)) {
                return month;
            }
        }
        throw new OncallException(INVALID_INPUT);
    }
}
