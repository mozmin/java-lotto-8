package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoGenerator {

    private final LottoPickSystem picker;

    public LottoGenerator(LottoPickSystem picker) {
        this.picker = picker;
    }

    /** n장의 로또를 발행한다 */
    public List<Lotto> generate(int n) {
        if (n <= 0) throw new IllegalArgumentException("[ERROR] 발행 장수는 1장 이상이어야 합니다.");
        List<Lotto> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new Lotto(picker.pick()));
        }
        return list;
    }
}
