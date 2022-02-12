# wordle-clone
## Introduction

The game [Wordle](https://en.wikipedia.org/wiki/Wordle) has recently become popular, so I've tried my hand at making a clone of it. I also wrote it because I think Makefiles are cool.

## Rules

This game plays the same as regular Wordle. You're trying to guess a word, and you have a limited chances. Enter your guess, and the system will print out your guess. For each letter in the word, if it is the correct letter in the correct position, it will by highlighted green. If it is a correct letter in an incorrect position, it will be highlighted yellow. If the letter is not in the word, it will not be highlighted.<br><br>

What makes this different from regular Wordle, however, is that you get to pick how long the word is. The number of chances you get is modified accordingly. You can select any word length from 2 to 12.

## Platforms

This Wordle clone works for Windows, Mac and Linux.
I cannot guarantee (and in fact, I highly doubt) that it will work on mobile devices.

## Dependencies

All dependencies are required unless stated otherwise.<br>

* `git` ([optional](https://git-scm.com/downloads))<br>
* `make` (if using [Mac](https://stackoverflow.com/a/10265766) or [Linux](https://askubuntu.com/a/1363822))<br>
* [An installation of the JRE](https://www.java.com/en/download)

## Installation

1. Clone this repository by entering<br>
```git clone https://github.com/Akhil841/wordle-clone```<br>
into the command line (requires `git`) or by downloading this repository as a ZIP file (click on the green `Code` button next to the "About" header) and unzipping it.
2. Compile and execute the program by `cd`-ing into the directory where this repository is located on your computer and entering<br>
```make exec```<br>
if on Mac or Linux or<br>
```execute.bat```<br>
if on Windows. If you're a quirky boi and want to use Windows PowerShell to execute this program, you would enter<br>
```.\execute.bat```<br>
3. The program should run, and you should be able to play Wordle in the command line!

## Other notes

* It's better to run this program on Mac or Linux because `make` only compiles the `.jar` file if the source files are updated, but Windows will compile the `.jar` file every time `execute.bat` is run and it will thus consume more memory writes and take longer. The difference is barely noticeable, so don't worry too much about it.
* You can actually change which words are used by the program! Just edit `dictionary.txt` and replace it with all the words you want. Each word should be separated by a newline.
   * Fun fact: The default dictionary is actually a merge of [the University of Michigan's list of all English words](http://www-personal.umich.edu/~jlawler/wordlist) and [the list of all legal Scrabble words](https://github.com/Urmomfarter/WWF-Cheat/blob/master/enable1.txt)!
* If you don't like how many lives you get, you can open `Wordle.java` and edit the variable `LIFE_MULTIPLIER` (default 1.5) or, if you want, you can edit `MAX_LIVES` to force the number of lives to be a specific number, regardless of the word length you pick.
* If you want the class files or just the `JAR` for whatever reason, the Makefile has recipes for `WordleTools.class`, `Wordle.class`, and `Wordle.jar`. If you're on Windows, just open the Makefile with a text editor and you'll see the commands used to make each of these files.
