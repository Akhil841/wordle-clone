import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class WordleTools {

    public static ArrayList<String> allToUpperCase(ArrayList<String> list) {
        ArrayList<String> out = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            out.add(list.get(i).toUpperCase());
        }
        return out;
    }
    public static String pickRandom(ArrayList<String> set) {
        Random rng = new Random();
        return set.get(rng.nextInt(set.size()));
    }

    public static ArrayList<String> getNLetterWords(ArrayList<String> list, int n) {
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() == n) output.add(list.get(i));
        }
        return output;
    }

    public static ArrayList<String> readFile(String fileName) throws IOException {
        Path name = Paths.get(fileName);
        ArrayList<String> contents = (ArrayList<String>) Files.readAllLines(name);
        return contents;
    }
}
