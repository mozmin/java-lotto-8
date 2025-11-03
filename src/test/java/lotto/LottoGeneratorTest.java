package lotto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoGeneratorTest {

    // 결정적 테스트를 위해 Stub 픽커 사용
    static class StubPicker implements LottoPickSystem {
        private final List<Integer> fixed;
        StubPicker(List<Integer> fixed) { this.fixed = fixed; }
        @Override public List<Integer> pick() { return fixed; }
    }

    @Test
    @DisplayName("요청한 장수만큼 로또를 발행한다")
    void generate_n_tickets() {
        LottoPickSystem picker = new StubPicker(List.of(8,1,3,5,2,4));
        LottoGenerator gen = new LottoGenerator(picker);

        List<Lotto> tickets = gen.generate(3);

        assertThat(tickets).hasSize(3);
        // Lotto 생성자가 정렬하므로 오름차순이 된다
        assertThat(tickets.get(0).getNumbers()).containsExactly(1,2,3,4,5,8);
    }

    @Test
    @DisplayName("장수가 0 이하이면 예외")
    void count_must_be_positive() {
        LottoPickSystem picker = new StubPicker(List.of(1,2,3,4,5,6));
        LottoGenerator gen = new LottoGenerator(picker);
        assertThatThrownBy(() -> gen.generate(0)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> gen.generate(-1)).isInstanceOf(IllegalArgumentException.class);
    }
}
