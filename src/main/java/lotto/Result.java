package lotto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Result {

    private final Map<Rank, Integer> counts = new EnumMap<>(Rank.class);
    private final long totalPrize;
    private final BigDecimal yieldPercent;

    public Result(List<Lotto> lotto, WinningNumbers winningNumbers, int purchaseAmount) {
        if (lotto == null || winningNumbers == null) {
            throw new IllegalArgumentException("[ERROR] 결과 집계 입력이 올바르지 않습니다.");
        }
        for (Rank r : Rank.values()) counts.put(r, 0);

        long prizeSum = 0L;
        for (Lotto l : lotto) {
            Rank rank = winningNumbers.rankOf(l);
            counts.put(rank, counts.get(rank) + 1);
            prizeSum += rank.prize();
        }
        this.totalPrize = prizeSum;
        this.yieldPercent = calcYieldPercent(prizeSum, purchaseAmount);
    }

    private BigDecimal calcYieldPercent(long totalPrize, int purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException("[ERROR] 구매 금액이 0 이하입니다.");
        }
        BigDecimal prize = BigDecimal.valueOf(totalPrize);
        BigDecimal purchase = BigDecimal.valueOf(purchaseAmount);
        // (총상금/구매금액)*100 → 소수점 둘째 자리 반올림
        return prize.divide(purchase, 10, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public int countOf(Rank rank) {
        return counts.get(rank);
    }

    public Map<Rank, Integer> counts() {
        return Map.copyOf(counts);
    }

    public long totalPrize() {
        return totalPrize;
    }

    public BigDecimal yieldPercent() {
        return yieldPercent;
    }
}
