package oncall.domain;

import static oncall.domain.Holiday.*;

import java.util.List;

public enum Month {

    JAN(1, 31, List.of(NEWYEAR)),
    FEB(2, 28, List.of()),
    MAR(3, 31, List.of(MOVEMENT)),
    APR(4, 30, List.of()),
    MAY(5, 31, List.of(CHILDREN)),
    JUN(6, 30, List.of(MEMORIAL)),
    JUL(7, 31, List.of()),
    AUG(8, 31, List.of(LIBERATION)),
    SEP(9, 30, List.of()),
    OCT(10, 31, List.of(FOUNDATION, KOREAN)),
    NOV(11, 30, List.of()),
    DEC(12, 31, List.of(CHRISTMAS));

    private final int month;
    private final int endDay;
    private final List<Holiday> holidays;

    Month(int month, int endDay, List<Holiday> holidays) {
        this.month = month;
        this.endDay = endDay;
        this.holidays = holidays;
    }

    public int getMonth() {
        return month;
    }
    public int getEndDay() {
        return endDay;
    }
    public List<Holiday> getHolidays() {
        return holidays;
    }
}
