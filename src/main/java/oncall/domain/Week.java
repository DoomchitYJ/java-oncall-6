package oncall.domain;

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
}
