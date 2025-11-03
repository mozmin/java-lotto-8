package lotto;

public class Calculator {

    private static final int UNIT = 1000;

    private final int amount;

    public Calculator(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 0보다 커야 합니다.");
        }
        if (amount % UNIT != 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 1000원 단위여야 합니다.");
        }
    }

    /**
     * 구매 장수 계산
     */
    public int count() {
        return amount / UNIT;
    }

    // getter
    public int getAmount() {
        return amount;
    }
}
