package lotto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningNumbersTest {

    @Test
    void 보너스번호_범위_벗어날시_예외_발생() {
        Lotto winning = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThatThrownBy(() -> new WinningNumbers(winning, 0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new WinningNumbers(winning, 46))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스번호가_당첨번호_6개와_중복일시_예외_발생() {
        Lotto winning = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThatThrownBy(() -> new WinningNumbers(winning, 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로토_결과_판정() {
        WinningNumbers wn = new WinningNumbers(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);

        // 6개 일치 → 1등
        assertThat(wn.rankOf(new Lotto(List.of(1, 2, 3, 4, 5, 6)))).isEqualTo(Rank.FIRST);
        // 5개 + 보너스 → 2등
        assertThat(wn.rankOf(new Lotto(List.of(1, 2, 3, 4, 5, 7)))).isEqualTo(Rank.SECOND);
        // 5개 → 3등
        assertThat(wn.rankOf(new Lotto(List.of(1, 2, 3, 4, 5, 8)))).isEqualTo(Rank.THIRD);
        // 4개 → 4등
        assertThat(wn.rankOf(new Lotto(List.of(1, 2, 3, 4, 10, 11)))).isEqualTo(Rank.FOURTH);
        // 3개 → 5등
        assertThat(wn.rankOf(new Lotto(List.of(1, 2, 3, 10, 11, 12)))).isEqualTo(Rank.FIFTH);
        // 그 외 → 꽝
        assertThat(wn.rankOf(new Lotto(List.of(1, 2, 10, 11, 12, 13)))).isEqualTo(Rank.MISS);
    }
}
