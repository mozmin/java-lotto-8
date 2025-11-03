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
            if (r == Rank.MISS) continue;
            System.out.printf("%d개 일치%s (%,d원) - %d개%n",
                    matchCountOf(r),
                    r == Rank.SECOND ? ", 보너스 볼 일치" : "",
                    r.prize(),
                    result.countOf(r)
            );
        }
        System.out.printf("총 수익률은 %s%%입니다.%n", result.yieldPercent().toPlainString());
    }

    private int matchCountOf(Rank r) {
        return switch (r) {
            case FIRST -> 6;
            case SECOND, THIRD -> 5;
            case FOURTH -> 4;
            case FIFTH -> 3;
            default -> 0;
        };
    }

    public void printError(String message) {
        System.out.println(message);
    }
}
