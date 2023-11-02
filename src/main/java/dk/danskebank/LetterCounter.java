package dk.danskebank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class LetterCounter {
	
	private Character letter;
	private Hashtable<String, Integer> hash = new Hashtable<String,Integer>();
	
	
	public LetterCounter(Character letter) {
		this.letter = letter;
	}
	
	public void addWord(String word, int count) {
		Integer c = hash.get(word);
		if (c == null) {
			hash.put(word, count);
		} else {
			hash.replace(word, (c + count));
		}
	}
	
	public Character getLetter() {
		return letter;
	}
	
	public List<String> getWords() {
		List<String> list = new ArrayList<String>(this.hash.keySet());
		Collections.sort(list);
		return list;
	}
	
	public Integer getWordCount(String word) {
		Integer c = hash.get(word);
		if (c == null) {
			return 0;
		} else {
			return c;
		}
	}

}
