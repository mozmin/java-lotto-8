package lotto;

import java.util.List;

public class Output {

    public void printPurchased(List<Lotto> tickets) {
        System.out.println(tickets.size() + "개를 구매했습니다.");
        for (Lotto t : tickets) {
            System.out.println(t.getNumbers()); // [1, 2, 3, 4, 5, 6] 형태
        }
    }

    public void printResult(Result result) {
        System.out.println("당첨 통계");
        System.out.println("---");
        for (Rank r : Rank.values()) {
            if (r == Rank.MISS) continue; // MISS는 표에서 제외
            printLine(r, result.countOf(r));
        }
        System.out.printf("총 수익률은 %s%%입니다.%n", result.yieldPercent().toPlainString());
    }

    private void printLine(Rank r, int count) {
        String bonusPart = r.getLabel();
        if (!bonusPart.isEmpty()) bonusPart = ", " + bonusPart;
        System.out.printf("%d개 일치%s (%,d원) - %d개%n",
                r.getMatchedCount(), bonusPart, r.getPrize(), count);
    }

    public void printError(String message) {
        System.out.println(message);
    }
}
