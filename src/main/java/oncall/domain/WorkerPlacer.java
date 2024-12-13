package oncall.domain;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class WorkerPlacer {

    private final Month month;
    private final Week day;
    private final Deque<String> weekdayWorkers;
    private final Deque<String> weekendWorkers;

    private static final String MONTH = "월 ";
    private static final String DAY = "일 ";
    private static final String HOLIDAY = "(휴일)";

    public WorkerPlacer(final Month month, final Week day, final List<String> weekdayWorkers, final List<String> weekendWorkers) {
        this.month = month;
        this.day = day;
        this.weekdayWorkers = new LinkedList<>(weekdayWorkers);
        this.weekendWorkers = new LinkedList<>(weekendWorkers);
    }

    private List<Integer> getHolidays() {
        List<Holiday> holidays = month.getHolidays();
        return holidays.stream()
                .map(Holiday::getDay)
                .toList();
    }

    public List<String> place() {
        String currenMonth = month.getName();
        int currentDay = day.getOrder();
        List<Integer> currentHoliday = getHolidays();

        List<String> resultNames = new ArrayList<>();
        List<String> result = new ArrayList<>();

        for (int i=1; i<=month.getEndDay(); i++) {
            String todayResult = currenMonth+MONTH;

            String yesterdayWorker = "";
            if (!resultNames.isEmpty()) {
                yesterdayWorker = resultNames.getLast();
            }

            // 평일
            if (!currentHoliday.contains(i) && currentDay != 5 && currentDay != 6) {
                String todayWorker = weekdayWorkers.pollFirst();
                weekdayWorkers.addLast(todayWorker);
                if (todayWorker.equals(yesterdayWorker)) {
                    resultNames.add(weekdayWorkers.pollFirst());
                    weekdayWorkers.addFirst(todayWorker);
                }
                else {
                    resultNames.add(todayWorker);
                }
                todayResult = todayResult + i + DAY + Week.getNameByOrder(currentDay)+ " " + resultNames.getLast();
            }

            // 공휴일
            if (currentHoliday.contains(i)) {
                String todayWorker = weekendWorkers.pollFirst();
                weekendWorkers.addLast(todayWorker);
                if (todayWorker.equals(yesterdayWorker)) {
                    resultNames.add(weekendWorkers.pollFirst());
                    weekendWorkers.addFirst(todayWorker);
                }
                else {
                    resultNames.add(todayWorker);
                }
                todayResult = todayResult + i + DAY + Week.getNameByOrder(currentDay)+HOLIDAY+ " " + resultNames.getLast();
            }

            // 주말
            if (currentDay == 5 || currentDay == 6) {
                String todayWorker = weekendWorkers.pollFirst();
                weekendWorkers.addLast(todayWorker);
                if (todayWorker.equals(yesterdayWorker)) {
                    resultNames.add(weekendWorkers.pollFirst());
                    weekendWorkers.addFirst(todayWorker);
                }
                else {
                    resultNames.add(todayWorker);
                }
                todayResult = todayResult + i + DAY + Week.getNameByOrder(currentDay)+ " " + resultNames.getLast();
            }

            currentDay = (currentDay + 1) % 7;
            result.add(todayResult);
        }
        return result;
    }
}
