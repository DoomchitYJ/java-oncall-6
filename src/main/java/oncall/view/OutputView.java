package oncall.view;

import java.util.List;

public class OutputView {

    public static void showResult(List<String> results) {
        for (String result : results) {
            System.out.println(result);
        }
    }
}
