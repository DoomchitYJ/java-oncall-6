package oncall.domain;

public enum Holiday {

    NEWYEAR("신정", 1, 1),
    MOVEMENT("삼일절", 3, 1),
    CHILDREN("어린이날", 5, 5),
    MEMORIAL("현충일", 6, 6),
    LIBERATION("광복절", 8, 15),
    FOUNDATION("개천절", 10, 3),
    KOREAN("한글날", 10, 9),
    CHRISTMAS("성탄절", 12, 25);

    private final String name;
    private final int month;
    private final int day;

    Holiday(String name, int month, int day) {
        this.name = name;
        this.month = month;
        this.day = day;
    }

    public String getName() {
        return name;
    }
    public int getMonth() {
        return month;
    }
    public int getDay() {
        return day;
    }
}
