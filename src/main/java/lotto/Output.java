package lotto;

import java.math.BigDecimal;
import java.util.List;

public class Output {

    public void printPurchased(List<Lotto> tickets) {
        System.out.println(tickets.size() + "개를 구매했습니다.");
        for (Lotto t : tickets) {
            System.out.println(t.getNumbers());
        }
    }

    public void printResult(Result result) {
        System.out.println("당첨 통계");
        System.out.println("---");

        Rank[] order = { Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST };
        for (Rank r : order) {
            printLine(r, result.countOf(r));
        }
        System.out.printf("총 수익률은 %s%%입니다.%n", formatPercent(result.yieldPercent()));
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

    // 소수점 뒤에 0제거하는 헬퍼 메서드
    private String formatPercent(BigDecimal percent) {
        // 250.00 -> 250 / 62.50 -> 62.5 / 100 -> 100.0 (최소 1자리 유지)
        BigDecimal s = percent.stripTrailingZeros();
        if (s.scale() < 1) {
            s = s.setScale(1); // 정수면 1자리 소수 강제
        }
        return s.toPlainString();
    }
}
