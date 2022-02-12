import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Wordle {
    public static void main(String[] args) {
        final int WORD_LENGTH = 5;
        ArrayList<String> words = new ArrayList<>();
        try {
            words = WordleTools.allToUpperCase(WordleTools.getNLetterWords(WordleTools.readFile("dictionary.txt"), WORD_LENGTH));
        }
        catch (IOException e) {
            System.out.println("FATAL: Missing dictionary");
            System.exit(1);
        }
        boolean found = false;
        final int MAX_LIVES = 6;
        int lives = MAX_LIVES;
        Scanner input = new Scanner(System.in);
        String correct = WordleTools.pickRandom(words).toUpperCase();
        while (!found && lives != 0) {
            System.out.println("Enter your guess. Lives remaining: " + lives);
            String guess = input.next().toUpperCase();
            if (guess.length() != 5) {
                System.out.println("Please enter a 5-letter word");
                continue;
            }
            if (!words.contains(guess)) {
                System.out.println("Word was not in list");
                continue;
            }
            final String ANSI_RESET = "\u001B[0m";
            final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
            final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
            final String ANSI_BLACK_FOREGROUND = "\u001B[30m";
            if (guess.equals(correct)) {
                System.out.println(ANSI_GREEN_BACKGROUND + ANSI_BLACK_FOREGROUND + correct + ANSI_RESET);
                found = true;
                continue;
            }
            char[] guessCharArr = guess.toCharArray();
            char[] correctCharArr = correct.toCharArray();
            for (int i = 0; i < WORD_LENGTH; i++) {
                if (guessCharArr[i] != correctCharArr[i] && !correct.contains("" + guessCharArr[i])) {
                    System.out.print(guessCharArr[i]);
                }
                if (guessCharArr[i] != correctCharArr[i] && correct.contains("" + guessCharArr[i])) {
                    System.out.print(ANSI_YELLOW_BACKGROUND + ANSI_BLACK_FOREGROUND + guessCharArr[i] + ANSI_RESET);
                }
                if (guessCharArr[i] == correctCharArr[i]) {
                    System.out.print(ANSI_GREEN_BACKGROUND + ANSI_BLACK_FOREGROUND + guessCharArr[i] + ANSI_RESET);
                }
            }
            System.out.println();
            lives--;
        }
        input.close();
        if (found) {
            System.out.println("Congrats, you won! Game over.");
        }
        else {
            System.out.println("The word was: " + correct);
        }
    }
}