namespace Wordle;

public class Wordle
{
    public static void Main(string[] args)
    {
        WordleTools.ResetColor();
        Console.WriteLine("How long of a word do you want to guess? Press enter to set to 5 letters.");
        string wordNum = WordleTools.DeNull(Console.ReadLine());
        int WORD_LENGTH = 5;
        if (wordNum != "continue")
        {
            if (!wordNum.Equals(""))
            {
                try
                {
                    WORD_LENGTH = Convert.ToInt32(wordNum);
                    if (WORD_LENGTH < 2)
                    {
                        Console.WriteLine("Invalid word length. Setting word length to default 5.");
                        WORD_LENGTH = 5;
                    }
                    if (WORD_LENGTH > 12)
                    {
                        Console.WriteLine("There aren't many words that long. Setting word length to default 5.");
                        WORD_LENGTH = 5;
                    }
                }
                catch (FormatException)
                {
                    Console.WriteLine("Not a number. Setting word length to default 5.");
                }
            }
        }
        List<string> words = new List<string>();
        try
        {
            words = WordleTools.AllToUpperCase(WordleTools.GetNLetterWords(WordleTools.ReadFile("dictionary.txt"), WORD_LENGTH));
        }
        catch (DirectoryNotFoundException)
        {
            Console.WriteLine("FATAL: Missing dictionary");
            Environment.Exit(1);
        }
        bool found = false;
        const double LIFE_MULTIPLIER = 1.5;
        int MAX_LIVES = (int)Math.Floor(WORD_LENGTH * LIFE_MULTIPLIER);
        int lives = MAX_LIVES;
        string correct = WordleTools.PickRandom(words).ToUpper();
        while (!found && lives > 0)
        {
            Console.WriteLine("Enter your guess. Lives remaining: " + lives);
            string guess = WordleTools.DeNull(Console.ReadLine()).ToUpper();
            if (guess == "EXITGAME")
            {
                Console.WriteLine("Build success.");
                Environment.Exit(0);
            }
            if (guess.Length != WORD_LENGTH)
            {
                Console.WriteLine("Please enter a {0}-letter word", WORD_LENGTH);
                continue;
            }
            if (!words.Contains(guess))
            {
                Console.WriteLine("Word was not in list");
                continue;
            }
            if (guess == correct)
            {
                WordleTools.InitGreen();
                Console.WriteLine(correct);
                found = true;
                WordleTools.ResetColor();
                continue;
            }
            char[] guessCharArr = guess.ToCharArray();
            char[] correctCharArr = correct.ToCharArray();
            for (int i = 0; i < guessCharArr.Length; i++)
            {
                if (guessCharArr[i] != correctCharArr[i] && !correct.Contains(guessCharArr[i]))
                {
                    Console.Write(guessCharArr[i]);
                }
                if (guessCharArr[i] != correctCharArr[i] && correct.Contains(guessCharArr[i]))
                {
                    WordleTools.InitYellow();
                    Console.Write(guessCharArr[i]);
                    WordleTools.ResetColor();
                }
                if (guessCharArr[i] == correctCharArr[i])
                {
                    WordleTools.InitGreen();
                    Console.Write(guessCharArr[i]);
                    WordleTools.ResetColor();
                }
            }
            Console.WriteLine();
            lives--;
        }
        if (found)
        {
            Console.WriteLine("Congratulations! Game over.");
        }
        else
        {
            Console.WriteLine("The word was {0}.", correct);
        }
        WordleTools.ResetColor();
        Console.WriteLine("Press enter to quit.");
        Console.ReadLine();
    }
}
