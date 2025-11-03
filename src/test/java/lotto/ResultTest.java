package lotto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {

    @Test
    void N장의_로또_결과_판정() {
        // 당첨: 1~6, 보너스: 7
        WinningNumbers wn = new WinningNumbers(new Lotto(List.of(1,2,3,4,5,6)), 7);

        // 각 등수 1장 + 꽝 1장 (총 6장, 구매금액 6000 가정)
        List<Lotto> tickets = List.of(
                new Lotto(List.of(1,2,3,4,5,6)),   // FIRST
                new Lotto(List.of(1,2,3,4,5,7)),   // SECOND
                new Lotto(List.of(1,2,3,4,5,8)),   // THIRD
                new Lotto(List.of(1,2,3,4,9,10)),  // FOURTH
                new Lotto(List.of(1,2,3,11,12,13)),// FIFTH
                new Lotto(List.of(1,2,10,11,12,13))// MISS
        );

        Result result = new Result(tickets, wn, /*purchase*/ 6_000);

        assertThat(result.countOf(Rank.FIRST)).isEqualTo(1);
        assertThat(result.countOf(Rank.SECOND)).isEqualTo(1);
        assertThat(result.countOf(Rank.THIRD)).isEqualTo(1);
        assertThat(result.countOf(Rank.FOURTH)).isEqualTo(1);
        assertThat(result.countOf(Rank.FIFTH)).isEqualTo(1);
        assertThat(result.countOf(Rank.MISS)).isEqualTo(1);

        long expectedTotal =
                (long) Rank.FIRST.getPrize()
                        + (long) Rank.SECOND.getPrize()
                        + (long) Rank.THIRD.getPrize()
                        + (long) Rank.FOURTH.getPrize()
                        + (long) Rank.FIFTH.getPrize(); // MISS는 0
        assertThat(result.totalPrize()).isEqualTo(expectedTotal);
    }

    @Test
    void 수익률_계산() {
        // 당첨: 1~6, 보너스: 7
        WinningNumbers wn = new WinningNumbers(new Lotto(List.of(1,2,3,4,5,6)), 7);

        // 1장: 3개 일치(FIFTH=5,000), 1장: 꽝 → 총 2장, 구매금액 2,000
        List<Lotto> tickets = List.of(
                new Lotto(List.of(1,2,3,40,41,42)), // FIFTH
                new Lotto(List.of(10,11,12,13,14,15)) // MISS
        );

        Result result = new Result(tickets, wn, /*purchase*/ 2_000);

        // 수익률 = (5,000 / 2,000) * 100 = 250.00 %
        assertThat(result.yieldPercent()).isEqualByComparingTo(new BigDecimal("250.00"));
    }
}
