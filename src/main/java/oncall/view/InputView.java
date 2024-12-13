package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;

public class InputView {

    private static final String MONTH_DAY_VIEW = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";

    private static final String WEEKDAY_VIEW = "평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";
    private static final String WEEKEND_VIEW = "휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";

    public static String readMonthDay() {
        System.out.print(MONTH_DAY_VIEW);
        return Console.readLine().trim();
    }

    public static List<String> readWorkers() {
        List<String> input = new ArrayList<>();
        System.out.print(WEEKDAY_VIEW);
        input.add(Console.readLine().trim());
        System.out.print(WEEKEND_VIEW);
        input.add(Console.readLine().trim());
        return input;
    }
}
