javac Wordle.java WordleTools.java
jar cfm Wordle.jar META-INF/manifest.txt Wordle.class WordleTools.class dictionary.txt
java -jar Wordle.jar