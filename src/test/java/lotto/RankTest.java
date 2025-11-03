package lotto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {

    @Test
    void 일치_개수와_보너스_여부로_등수_판정() {
        assertThat(Rank.of(6, false)).isEqualTo(Rank.FIRST);
        assertThat(Rank.of(5, true)).isEqualTo(Rank.SECOND);
        assertThat(Rank.of(5, false)).isEqualTo(Rank.THIRD);
        assertThat(Rank.of(4, false)).isEqualTo(Rank.FOURTH);
        assertThat(Rank.of(3, false)).isEqualTo(Rank.FIFTH);
        assertThat(Rank.of(2, false)).isEqualTo(Rank.MISS);
        assertThat(Rank.of(0, false)).isEqualTo(Rank.MISS);
    }

    @Test
    // getter 확인
    void 등수별_상금_확인() {
        assertThat(Rank.FIRST.prize()).isGreaterThan(Rank.SECOND.prize());
        assertThat(Rank.SECOND.prize()).isGreaterThan(Rank.THIRD.prize());
        assertThat(Rank.FIFTH.prize()).isGreaterThan(0);
        assertThat(Rank.MISS.prize()).isZero();
    }
}
