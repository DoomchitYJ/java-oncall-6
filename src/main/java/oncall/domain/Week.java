package oncall.domain;

import static oncall.exception.ExceptionMessage.INVALID_INPUT;

import oncall.exception.OncallException;

public enum Week {

    MON("월", 0, false),
    TUE("화", 1, false),
    WED("수", 2, false),
    THR("목", 3, false),
    FRI("금", 4,false),
    SAT("토", 5,true),
    SUN("일", 6,true);

    private final String name;
    private final int order;
    private final boolean isWeekend;

    Week(String name, int order, boolean isWeekend) {
        this.name = name;
        this.order = order;
        this.isWeekend = isWeekend;
    }

    public String getName() {
        return name;
    }
    public int getOrder() {
        return order;
    }
    public boolean isWeekend() {
        return isWeekend;
    }

    public static Week fromName(String name) {
        for (Week week : values()) {
            if (week.getName().equals(name)) {
                return week;
            }
        }
        throw new OncallException(INVALID_INPUT);
    }

    public static String getNameByOrder(int order) {
        for (Week week : values()) {
            if (week.getOrder() == order) {
                return week.getName();
            }
        }
        throw new OncallException(INVALID_INPUT);
    }
}
