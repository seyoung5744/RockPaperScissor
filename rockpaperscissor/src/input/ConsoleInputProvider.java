package input;

import java.util.Scanner;

public class ConsoleInputProvider implements InputProvider {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String readInput() {
        return scanner.nextLine();
    }
}
