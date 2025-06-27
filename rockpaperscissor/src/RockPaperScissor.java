import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public enum RockPaperScissor {

    ROCK("바위"),
    PAPER("보"),
    SCISSOR("가위");

    private final String description;

    private static final Random RANDOM = new Random();
    private static final Map<String, RockPaperScissor> DESCRIPTION_TO_ENUM = Arrays.stream(RockPaperScissor.values())
            .collect(Collectors.toMap(
                    RockPaperScissor::getDescription,
                    RockPaperScissor -> RockPaperScissor
            ));

    RockPaperScissor(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public GameResult compareWith(RockPaperScissor opponent) {
        if (this == opponent) {
            return GameResult.DRAW;
        }

        return switch (this) {
            case ROCK -> (opponent == SCISSOR) ? GameResult.WIN : GameResult.LOSE;
            case PAPER -> (opponent == ROCK) ? GameResult.WIN : GameResult.LOSE;
            case SCISSOR -> (opponent == PAPER) ? GameResult.WIN : GameResult.LOSE;
        };
    }

    public static RockPaperScissor fromDescription(String desc) {
        RockPaperScissor rps = DESCRIPTION_TO_ENUM.get(desc);
        if (rps == null) {
            throw new IllegalArgumentException("유효하지 않은 입력: " + desc);
        }
        return rps;
    }

    public static boolean isValid(String user) {
        return DESCRIPTION_TO_ENUM.containsKey(user);
    }


    public static RockPaperScissor random() {
        RockPaperScissor[] values = RockPaperScissor.values();
        return values[RANDOM.nextInt(values.length)];
    }
}
