import input.InputProvider;
import output.OutputReader;

import java.util.Map;

public class Game {

    private static final Map<Integer, String> gifts = Map.of(
            0, "꽝",
            1, "곰돌이 인형",
            2, "스파르타 랜드 입장권",
            3, "스파르타 캐니언 항공 투어권",
            4, "호텔 스파르타 숙박권",
            5, "스파르테이트 항공권"
    );

    private final InputProvider input;
    private final OutputReader output;

    private int gameTimes = 5;
    private int winTimes = 0;

    private GameStatus status = GameStatus.IN_PROGRESS;

    public Game(InputProvider input, OutputReader output) {
        this.input = input;
        this.output = output;
    }

    public void start() {

        while (status == GameStatus.IN_PROGRESS) {
            output.println("가위 바위 보 중 하나를 입력해주세요!");
            String userInput = input.readInput();

            if (!RockPaperScissor.isValid(userInput)) {
                output.println("잘못된 입력입니다!!");
                continue;
            }

            RockPaperScissor user = RockPaperScissor.fromDescription(userInput);
            RockPaperScissor computer = RockPaperScissor.random();

            GameResult gameResult = user.compareWith(computer);

            output.print("당신의 " + user.getDescription() + " 그리고 상대의 " + computer.getDescription() + " 으로");
            switch (gameResult) {
                case WIN:
                    output.println(" 당신이 이겼습니다!");
                    winTimes++;
                    break;
                case LOSE:
                    output.println(" 당신이 졌습니다..");
                    break;
                case DRAW:
                    output.println(" 서로 비겼습니다.");
                    break;
            }
            gameTimes--;

            if (gameTimes == 0) {
                status = GameStatus.FINISH;
            }
        }

        output.println("축하합니다! 총 " + winTimes + " 회 승리하여 경품으로 [" + gifts.get(winTimes) + "] 을 획득하셨습니다!");
    }

}
