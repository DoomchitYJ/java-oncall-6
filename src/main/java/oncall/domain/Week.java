package oncall.domain;

import static oncall.exception.ExceptionMessage.INVALID_INPUT;

import oncall.exception.OncallException;

public enum Week {

    MON("월", false),
    TUE("화", false),
    WED("수", false),
    THR("목", false),
    FRI("금", false),
    SAT("토", true),
    SUN("일", true);

    private final String name;
    private final boolean isWeekend;

    Week(String name, boolean isWeekend) {
        this.name = name;
        this.isWeekend = isWeekend;
    }

    public String getName() {
        return name;
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
}
