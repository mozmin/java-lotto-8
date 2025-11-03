package lotto;

public class WinningNumbers {

    private static final int MIN = 1;
    private static final int MAX = 45;

    private final Lotto winning; // 당첨 6개 (검증은 Lotto가 수행)
    private final int bonus;

    public WinningNumbers(Lotto winning, int bonus) {
        if (winning == null) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호가 없습니다.");
        }
        validateBonus(bonus, winning);
        this.winning = winning;
        this.bonus = bonus;
    }

    private void validateBonus(int bonus, Lotto winning) {
        if (bonus < MIN || bonus > MAX) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1~45 범위여야 합니다.");
        }
        if (winning.checkBonus(bonus)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호가 당첨 번호와 중복입니다.");
        }
    }

    public Rank rankOf(Lotto lotto) {
        int match = lotto.countMatch(winning);
        boolean bonusMatched = (match == 5) && lotto.checkBonus(bonus);
        return Rank.of(match, bonusMatched);
    }
}
