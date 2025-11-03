package lotto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CalculatorTest {

    @Test
    void 금액이_0이나_음수면_예외_발생() {
        assertThatThrownBy(() -> new Calculator(0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Calculator(-1000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 금액이_1000원_단위가_아니면_예외_발생() {
        assertThatThrownBy(() -> new Calculator(1500))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Calculator(999))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 구매_장수_계산() {
        Calculator calc1 = new Calculator(8000);
        assertThat(calc1.count()).isEqualTo(8);

        Calculator calc2 = new Calculator(1000);
        assertThat(calc2.count()).isEqualTo(1);
    }
}
