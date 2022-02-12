import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
/**
 * Contains various helper methods used by the main Wordle class.
 * I kept it here to declutter the JAR's entrypoint.
 */
public class WordleTools {
    /**
     * Converts all words in an {@link ArrayList}<{@link String}> into uppercase
     * using Java's {@code String.toUppercase} method.
     * @param list The list to format.
     * @return The inputted list, but formatted so all strings are uppercase. 
     */
    public static ArrayList<String> allToUpperCase(ArrayList<String> list) {
        //Store return value here
        ArrayList<String> out = new ArrayList<>();
        //For each entry in the input list, make it uppercase, and add it to the output
        for (int i = 0; i < list.size(); i++) out.add(list.get(i).toUpperCase());
        //return the output
        return out;
    }
    /**
     * Picks a random value from an {@link ArrayList}<{@link String}>.
     * @param set The list of {@link String} to choose from.
     * @return A random {@link String} chosen from the input set.
     */
    public static String pickRandom(ArrayList<String> set) {
        //Instantiate an instance of a random number generator
        Random rng = new Random();
        //Pick a string with index begin a random value from 0 (inclusive) 
        //to the length of the string. (exclusive)
        return set.get(rng.nextInt(set.size()));
    }

    /**
     * Takes a list of {@link String}s, and returns all the {@link String}s with length {@code n}.
     * @param list The list of words to format.
     * @param n The length of words in the output list
     * @return An {@link ArrayList}<{@link String}> of all words in {@code list} with length {@code n}.
     */
    public static ArrayList<String> getNLetterWords(ArrayList<String> list, int n) {
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) if (list.get(i).length() == n) output.add(list.get(i));
        return output;
    }

    /**
     * Reads a file using {@code java.nio.file.Files.readAllLines}.
     * @param fileName The file to read.
     * @return An {@link ArrayList}<{@link String}> of the contents of the file. It is split on newlines
     * into the {@link ArrayList}<{@link String}>.
     * @throws IOException if the input file does not exist.
     */
    public static ArrayList<String> readFile(String fileName) throws IOException {
        Path name = Paths.get(fileName);
        ArrayList<String> contents = (ArrayList<String>) Files.readAllLines(name);
        return contents;
    }
}
