package lotto;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        Input in = new Input();
        Output out = new Output();

        try {
            // 1) 금액 입력 → 장수 계산
            int amount = in.readPurchaseAmount();
            Calculator calc = new Calculator(amount);
            int count = calc.count();

            // 2) 발행
            LottoGenerator generator = new LottoGenerator(new RandomNumbersPick());
            List<Lotto> tickets = generator.generate(count);
            out.printPurchased(tickets);

            // 3) 당첨/보너스 입력
            List<Integer> winningNums = in.readWinningNumbers();
            Lotto winning = new Lotto(winningNums);
            int bonus = in.readBonusNumber();
            WinningNumbers wn = new WinningNumbers(winning, bonus);

            // 4) 집계/수익률
            Result result = new Result(tickets, wn, amount);

            // 5) 출력
            out.printResult(result);

        } catch (IllegalArgumentException e) {
            // 과제 정책상 [ERROR]로 시작하는 메시지 후 종료(또는 재입력 루프)
            // 재입력 루프 요구 시, 각 입력 단계별 while로 분리해 반복 처리하세요.
            out.printError(e.getMessage());
        }
    }
}

