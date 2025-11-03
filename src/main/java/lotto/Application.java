package lotto;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        new Application().run();
    }

    public void run(){
        Input in = new Input();
        Output out = new Output();

        int amount = inputAmount(in, out);
        int count = new Calculator(amount).count();

        List<Lotto> tickets = pickLotto(count);
        out.printPurchased(tickets);

        Lotto winning = inputWinningNumbers(in, out);
        WinningNumbers wn = inputBonusNumber(in, out, winning);

        Result result = new Result(tickets, wn, amount);
        out.printResult(result);
    }

    private List<Lotto> pickLotto(int count) {
        LottoGenerator generator = new LottoGenerator(new RandomNumbersPick());
        return generator.generate(count);
    }

    private int inputAmount(Input in, Output out) {
        while (true) {
            try {
                int amount = in.readPurchaseAmount();
                new Calculator(amount); // 검증
                return amount;
            } catch (IllegalArgumentException e) {
                out.printError(e.getMessage());
            }
        }
    }

    private Lotto inputWinningNumbers(Input in, Output out) {
        while (true) {
            try {
                return new Lotto(in.readWinningNumbers());
            } catch (IllegalArgumentException e) {
                out.printError(e.getMessage());
            }
        }
    }

    private WinningNumbers inputBonusNumber(Input in, Output out, Lotto winning) {
        while (true) {
            try {
                int bonus = in.readBonusNumber();
                return new WinningNumbers(winning, bonus);
            } catch (IllegalArgumentException e) {
                out.printError(e.getMessage());
            }
        }
    }
}

