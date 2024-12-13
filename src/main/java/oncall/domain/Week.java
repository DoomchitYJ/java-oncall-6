package oncall.domain;

import static oncall.exception.ExceptionMessage.INVALID_INPUT;

import oncall.exception.OncallException;

public enum Week {

    MON("월", 0),
    TUE("화", 1),
    WED("수", 2),
    THR("목", 3),
    FRI("금", 4),
    SAT("토", 5),
    SUN("일", 6);

    private final String name;
    private final int order;

    Week(String name, int order) {
        this.name = name;
        this.order = order;
    }

    public String getName() {
        return name;
    }
    public int getOrder() {
        return order;
    }

    public static boolean isValid(String name) {
        for (Week week : values()) {
            if (week.getName().equals(name)) {
                return true;
            }
        }
        return false;
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
