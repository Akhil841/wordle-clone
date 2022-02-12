WordleTools.class: WordleTools.java
	javac WordleTools.java

Wordle.class: Wordle.java WordleTools.java
	javac Wordle.java WordleTools.java

Wordle.jar: Wordle.class WordleTools.class
	jar cfm Wordle.jar META-INF\manifest.txt Wordle.class WordleTools.class dictionary.txt

exec: Wordle.jar
	java -jar Wordle.jar