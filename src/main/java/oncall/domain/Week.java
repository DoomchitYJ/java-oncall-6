package oncall.domain;

public enum Week {

    MON("월"),
    TUE("화"),
    WED("수"),
    THR("목"),
    FRI("금"),
    SAT("토"),
    SUN("일");

    private final String name;

    Week(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
