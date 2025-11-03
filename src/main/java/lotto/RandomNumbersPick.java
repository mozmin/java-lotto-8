package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class RandomNumbersPick implements LottoPickSystem{
    @Override
    public List<Integer> pick() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }
}
