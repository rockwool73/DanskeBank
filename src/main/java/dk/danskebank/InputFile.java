package dk.danskebank;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Stream;

public class InputFile {

	private File file;
	private List<String> words = new ArrayList<String>();
	private List<String> wordsToBeExcluded;
	private boolean keepWords;
	private Hashtable<String, Integer> excludedWordHash = new Hashtable<String, Integer>();

	public InputFile(File file) {
		this(file, new ArrayList<String>(),true);
	}

	public InputFile(File file, List<String> excludeWords, boolean keepWords) {
		this.file = file;
		this.wordsToBeExcluded = excludeWords;
		this.keepWords = keepWords;
	}

	public File getFile() {
		return file;
	}
	public boolean isKeepWords() {
		return keepWords;
	}
	
	public void analyse() throws IOException {
		System.out.println("Analysing File [" + getFile().getAbsolutePath() + "] ---------------------------");
		try (Stream<String> stream = Files.lines(getFile().toPath())) {
			stream.forEach(line -> parseLine(line));
		} catch (IOException e) {
			throw e;
		}
	}
	
	
	private void parseLine(String line) {
		System.out.println(line);
		String[] lineWords = line.split(" ");
		if (isKeepWords()) {
			words.addAll(Arrays.asList(lineWords));
		}
		for (String word : lineWords) {
			word = word.toLowerCase().trim();
			for (String wordToBeExcluded : getWordsToBeExcluded()) {
				if (word.equalsIgnoreCase(wordToBeExcluded)) {
					Integer currentCount = excludedWordHash.get(word);
					if (currentCount == null) {
						excludedWordHash.put(word, 1);
					} else {
						excludedWordHash.put(word, currentCount + 1);
					}
				}
			}
		}
	}

	public List<String> getWords() {
		return words;
	}

	public List<String> getWordsToBeExcluded() {
		return wordsToBeExcluded;
	}

	public Hashtable<String, Integer> getExludedWordHash() {
		return excludedWordHash;
	}
	
	
	/*
	public void analyse2() throws IOException {
		System.out.println("Analysing File [" + getFile().getAbsolutePath() + "] ---------------------------");
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(getFile()));
			String line = reader.readLine();
			while (line != null) {
				parseLine(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	*/
}
