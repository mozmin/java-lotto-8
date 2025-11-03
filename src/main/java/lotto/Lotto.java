package lotto;

import java.util.*;

public class Lotto {

    private static final int SIZE = 6;
    private static final int MIN = 1;
    private static final int MAX = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream().sorted().toList();
    }

    private void validate(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("[ERROR] 로또 번호 목록이 null 입니다.");
        }
        if (numbers.size() != SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        if (numbers.stream().anyMatch(n -> n < MIN || n > MAX)) {
            throw new IllegalArgumentException("[ERROR] 숫자는 1~45 사이여야 합니다.");
        }
        Set<Integer> unique = new HashSet<>(numbers);
        if (unique.size() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 중복 숫자는 허용되지 않습니다.");
        }
    }

    public List<Integer> getNumbers() {
        return numbers; // 불변 리스트 그대로 반환
    }

    public boolean checkBonus(int n) {
        return numbers.contains(n);
    }

    public int countMatch(Lotto other) {
        Set<Integer> set = new HashSet<>(numbers);
        return (int) other.numbers.stream().filter(set::contains).count();
    }
}
