using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Wordle
{
    internal class WordleTools
    {
        public static string DeNull(string? nullString)
        {
            if (nullString == null) return "";
            else return nullString.ToString();
        }
        public static List<string> AllToUpperCase(List<string> list)
        {
            List<string> result = new List<string>();
            foreach (string str in list) result.Add(str.ToUpper());
            return result;
        }

        public static string PickRandom(List<string> list)
        {
            Random rng = new Random();
            return list[rng.Next(list.Count)];
        }

        public static List<string> GetNLetterWords(List<string> list, int n)
        {
            List<string> result = new List<string>();
            foreach (string str in list) if (str.Length == n) result.Add(str);
            return result;
        }

        public static List<string> ReadFile(string fileName)
        {
            return File.ReadAllLines(fileName).ToList();
        }

        public static void InitYellow()
        {
            Console.BackgroundColor = ConsoleColor.Yellow;
            Console.ForegroundColor = ConsoleColor.Black;
        }

        public static void InitGreen()
        {
            Console.BackgroundColor = ConsoleColor.Green;
            Console.ForegroundColor = ConsoleColor.Black;
        }

        public static void ResetColor()
        {
            Console.BackgroundColor = ConsoleColor.Black;
            Console.ForegroundColor = ConsoleColor.White;
        }
    }
}
