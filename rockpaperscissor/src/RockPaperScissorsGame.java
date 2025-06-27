import input.ConsoleInputProvider;
import output.ConsoleOutputRenderer;

public class RockPaperScissorsGame {

    public static void main(String[] args) {
        Game game = new Game(
                new ConsoleInputProvider(),
                new ConsoleOutputRenderer()
        );
        game.start();
    }
}
