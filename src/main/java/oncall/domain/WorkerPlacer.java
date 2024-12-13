package oncall.domain;

import static oncall.domain.Week.SAT;
import static oncall.domain.Week.SUN;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class WorkerPlacer {

    private static final int DAYSOFWEEK = 7;

    private static final String MONTH = "월 ";
    private static final String DAY = "일 ";
    private static final String HOLIDAY = "(휴일)";

    private static final List<Integer> WEEKEND_ORDER = List.of(SAT.getOrder(), SUN.getOrder());

    private final Month month;
    private final Week day;
    private final Deque<String> weekdayWorkers;
    private final Deque<String> weekendWorkers;

    public WorkerPlacer(final Month month, final Week day,
                        final List<String> weekdayWorkers, final List<String> weekendWorkers) {
        this.month = month;
        this.day = day;
        this.weekdayWorkers = new LinkedList<>(weekdayWorkers);
        this.weekendWorkers = new LinkedList<>(weekendWorkers);
    }

    public List<String> place() {
        List<String> result = new ArrayList<>();
        List<String> resultNames = new ArrayList<>();

        String currenMonth = month.getName();
        int currentDay = day.getOrder();

        for (int i=1; i<=month.getEndDay(); i++) {
            String todayResult = currenMonth+MONTH;
            String yesterdayWorker = getYesterdayWorker(resultNames);

            // 평일
            if (!isWeekend(currentDay)) {
                // 공휴일이 아닌 평일
                if (!isHoliday(i)) {
                    String todayWorker = weekdayWorkers.pollFirst();
                    weekdayWorkers.addLast(todayWorker);
                    if (todayWorker.equals(yesterdayWorker)) {
                        String nextWorker = weekdayWorkers.pollFirst();
                        weekdayWorkers.addFirst(todayWorker);
                        todayWorker = nextWorker;
                    }
                    resultNames.add(todayWorker);
                    todayResult = makeTodayResult(todayResult, i, currentDay, resultNames.getLast());
                }

                // 공휴일
                if (isHoliday(i)) {
                    String todayWorker = weekendWorkers.pollFirst();
                    weekendWorkers.addLast(todayWorker);
                    if (todayWorker.equals(yesterdayWorker)) {
                        String nextWorker = weekendWorkers.pollFirst();
                        weekendWorkers.addFirst(todayWorker);
                        todayWorker = nextWorker;
                    }
                    resultNames.add(todayWorker);
                    todayResult = todayResult + i + DAY + Week.getNameByOrder(currentDay)+HOLIDAY+ " " + resultNames.getLast();
                }
            }

            // 주말
            if (isWeekend(currentDay)) {
                String todayWorker = weekendWorkers.pollFirst();
                weekendWorkers.addLast(todayWorker);
                if (todayWorker.equals(yesterdayWorker)) {
                    String nextWorker = weekendWorkers.pollFirst();
                    weekendWorkers.addFirst(todayWorker);
                    todayWorker = nextWorker;
                }
                resultNames.add(todayWorker);
                todayResult = todayResult + i + DAY + Week.getNameByOrder(currentDay)+ " " + resultNames.getLast();
            }

            currentDay = updateCurrentDay(currentDay);
            result.add(todayResult);
        }
        return result;
    }

    private boolean isWeekend(int currentDay) {
        return WEEKEND_ORDER.contains(currentDay);
    }

    private boolean isHoliday(int day) {
        List<Integer> currentHoliday = getHolidays();
        return currentHoliday.contains(day);
    }

    private List<Integer> getHolidays() {
        List<Holiday> holidays = month.getHolidays();
        return holidays.stream()
                .map(Holiday::getDay)
                .toList();
    }

    private String getYesterdayWorker(List<String> resultNames) {
        if (!resultNames.isEmpty()) {
            return resultNames.getLast();
        }
        return "";
    }

    private int updateCurrentDay(int currentDay) {
        return (currentDay+1) % DAYSOFWEEK;
    }

    private String makeTodayResult(String todayResult, int i, int currentDay, String name) {
        return todayResult + i + DAY + Week.getNameByOrder(currentDay)+ " " + name;
    }
}
