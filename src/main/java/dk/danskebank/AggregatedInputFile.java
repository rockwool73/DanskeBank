package dk.danskebank;

import java.util.Hashtable;
import java.util.Set;

public class AggregatedInputFile {
	
	private Hashtable<Character,LetterCounter> hash = new Hashtable<Character,LetterCounter>();	
	
	public void addFile(InputFile inputFile) {
		for (String word : inputFile.getExludedWordHash().keySet()) {
			Character letter = word.trim().toUpperCase().charAt(0);
			LetterCounter letterCounter = hash.get(letter);
			if (letterCounter == null) {
				letterCounter = new LetterCounter(letter);
				hash.put(letter, letterCounter);
			}
			letterCounter.addWord(word, inputFile.getExludedWordHash().get(word));
		}
	}
	
	public Hashtable<Character, LetterCounter> getHash() {
		return hash;
	}
	public Set<Character> getLetters() {
		return hash.keySet();
	}
	public LetterCounter getLetterCounter(Character letter) {
		return hash.get(letter);
	}

}
