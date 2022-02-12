import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** 
 * 
 * The main, executable portion of the Wordle clone. This is the entrypoint used by Wordle.jar.
 * 
 * Written by Akhil Pillai on 11 February 2022
*/
public class Wordle {
    public static void main(String[] args) {
        //Scanner for taking user input
        Scanner input = new Scanner(System.in);
        //Ask for word length
        System.out.println("How long of a word do you want to guess? Press enter to set to 5 letters.");
        String wordNum = input.nextLine();
        //The length of the words we want to guess
        int WORD_LENGTH = 5;
        if (!wordNum.equals("")) {
            try {
                //Try to parse inputted number
                WORD_LENGTH = Integer.parseInt(wordNum);
                //Notify player if word is too short
                if (WORD_LENGTH < 2) {
                    System.out.println("Invalid word length. Setting word length to default 5.");
                    WORD_LENGTH = 5;
                }
                //Notify player if word is too long
                if (WORD_LENGTH > 12) {
                    System.out.println("There aren't many words that long. Setting word length to default 5.");
                    WORD_LENGTH = 5;
                }
            }
            //If the user enters an improper string
            catch (Exception e) {
                System.out.println("Not a number. Setting word length to default 5.");
                WORD_LENGTH = 5;
            }
        }
        //Store all possible words
        ArrayList<String> words = new ArrayList<>();
        //This is in a try-catch since you have to handle IOExceptions
        //if you try to read a file using Files.readAllLines()
        try {
            //Get all the words in the dictionary, 
            //filter out the words of length WORD_LENGTH, 
            //and convert them all to uppercase
            //to simplify verification.
            words = WordleTools.allToUpperCase(WordleTools.getNLetterWords(WordleTools.readFile("dictionary.txt"), WORD_LENGTH));
        }
        //If the dictionary is missing from the current directory
        catch (IOException e) {
            //Notify the user
            System.out.println("FATAL: Missing dictionary");
            //Terminate the program with an exit code of 1
            //since the program was unable to run properly
            System.exit(1);
        }
        //store whether the user found the correct word here
        boolean found = false;
        final double LIFE_MULTIPLIER = 1.5;
        //The number of lives the user starts with
        final int MAX_LIVES = (int) Math.floor(WORD_LENGTH * LIFE_MULTIPLIER);
        //Initialize life counter with the maximum number
        //of lives they can have
        int lives = MAX_LIVES;
        //Pick a random word from the list of words and convert it to uppercase.
        String correct = WordleTools.pickRandom(words).toUpperCase();
        //While the user hasn't found the word and still has lives
        while (!found && lives != 0) {
            //Ask for user's guess
            System.out.println("Enter your guess. Lives remaining: " + lives);
            //Store user guess
            String guess = "";
            //If the user enters an invalid string, ask if they'd like to quit.
            try {
                //Convert to uppercase to simplify verification
                guess = input.nextLine().toUpperCase();
            }
            catch (NoSuchElementException e) {
                System.out.println("Game paused");
                if (input.nextLine() == "q") System.exit(0);
            }
            finally {
                //User must enter a word equal in length to input word length
                if (guess.length() != WORD_LENGTH) {
                    System.out.println("Please enter a " + WORD_LENGTH + "-letter word");
                    continue;
                }
                //User must enter a known word
                if (!words.contains(guess)) {
                    System.out.println("Word was not in list");
                    continue;
                }
                //Constants for coloring text
                final String ANSI_RESET = "\u001B[0m";
                final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
                final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
                final String ANSI_BLACK_FOREGROUND = "\u001B[30m";
                //If the user has guessed the entire word
                if (guess.equals(correct)) {
                    //Highlight the entire word green and print it
                    System.out.println(ANSI_GREEN_BACKGROUND + ANSI_BLACK_FOREGROUND + correct + ANSI_RESET);
                    //Set found to true and exit the while loop
                    found = true;
                    continue;
                }
                //convert guess and correct answer for proper character verification
                char[] guessCharArr = guess.toCharArray();
                char[] correctCharArr = correct.toCharArray();
                //check each letter to see if it is correct and/or in the correct palce
                for (int i = 0; i < WORD_LENGTH; i++) {
                    //if the letter is not in the correct answer
                    if (guessCharArr[i] != correctCharArr[i] && !correct.contains("" + guessCharArr[i])) {
                        //print the letter with no highlighting
                        System.out.print(guessCharArr[i]);
                    }
                    //if the letter is in the correct answer but not in the correct position
                    if (guessCharArr[i] != correctCharArr[i] && correct.contains("" + guessCharArr[i])) {
                        //print the letter in black, highlighted yellow
                        System.out.print(ANSI_YELLOW_BACKGROUND + ANSI_BLACK_FOREGROUND + guessCharArr[i] + ANSI_RESET);
                    }
                    //if the letter is in the correct answer and in the correct position
                    if (guessCharArr[i] == correctCharArr[i]) {
                        //print the letter in black, highlighted green
                        System.out.print(ANSI_GREEN_BACKGROUND + ANSI_BLACK_FOREGROUND + guessCharArr[i] + ANSI_RESET);
                    }
                }
                //at the end of each iteration, make a newline
                //and deduct 1 life.
                System.out.println();
                lives--;
            }
        }
        //When the loop is terminated, 
        //close the scanner to prevent memory leakage.
        input.close();
        //If the user found the correct word
        if (found) {
            //Congratulate them and end the game.
            System.out.println("Congrats, you won! Game over.");
        }
        //If not,
        else {
            //Tell them what the word was.
            System.out.println("The word was: " + correct);
        }
    }
}