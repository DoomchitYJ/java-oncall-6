package oncall.domain;

import static oncall.domain.Holiday.*;

import java.util.List;

public enum Month {

    JAN(1, List.of(NEWYEAR)),
    FEB(2, List.of()),
    MAR(3, List.of(MOVEMENT)),
    APR(4, List.of()),
    MAY(5, List.of(CHILDREN)),
    JUN(6, List.of(MEMORIAL)),
    JUL(7, List.of()),
    AUG(8, List.of(LIBERATION)),
    SEP(9, List.of()),
    OCT(10, List.of(FOUNDATION, KOREAN)),
    NOV(11, List.of()),
    DEC(12, List.of(CHRISTMAS));

    private final int month;
    private final List<Holiday> holidays;

    Month(int month, List<Holiday> holidays) {
        this.month = month;
        this.holidays = holidays;
    }

    public int getMonth() {
        return month;
    }
    public List<Holiday> getHolidays() {
        return holidays;
    }
}
