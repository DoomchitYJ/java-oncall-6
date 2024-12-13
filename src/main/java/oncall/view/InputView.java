package oncall.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String MONTH_DAY_VIEW = "비상 근무를 배정할 월과 시작 요일을 입력하세요>";

    public static String readMonthDay() {
        System.out.println(MONTH_DAY_VIEW);
        return Console.readLine().trim();
    }
}
