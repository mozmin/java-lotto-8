package lotto;

public enum Rank {

    FIRST (6, false, 2_000_000_000),
    SECOND(5, true ,    30_000_000),
    THIRD (5, false,     1_500_000),
    FOURTH(4, false,        50_000),
    FIFTH (3, false,         5_000),
    MISS  (0, false,             0);

    private final int matchCount;
    private final boolean requiresBonus;
    private final int prize;

    Rank(int matchCount, boolean requiresBonus, int prize) {
        this.matchCount = matchCount;
        this.requiresBonus = requiresBonus;
        this.prize = prize;
    }

    public int prize() {
        return prize;
    }

    /**
     * 일치 개수와 보너스 여부로 등수 판정
     */
    public static Rank of(int match, boolean bonusMatched) {
        if (match == 6) return FIRST;
        if (match == 5 && bonusMatched) return SECOND;
        if (match == 5) return THIRD;
        if (match == 4) return FOURTH;
        if (match == 3) return FIFTH;
        return MISS;
    }
}
