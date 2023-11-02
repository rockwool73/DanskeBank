package dk.danskebank.output;

import java.io.IOException;

import dk.danskebank.AggregatedInputFile;
import dk.danskebank.LetterCounter;

public class OutputToConsole implements Output {
	
	@Override
	public void execute(AggregatedInputFile aggregatedInputFile) throws IOException {
		for (Character letter : aggregatedInputFile.getLetters()) {
			System.out.println("------------------------------------------------------");
			System.out.println("Letter : " + letter);
			LetterCounter letterCounter = aggregatedInputFile.getLetterCounter(letter);
			for (String word : letterCounter.getWords()) {
				System.out.println(" -- " + word + " :: " + letterCounter.getWordCount(word));
			}
		}		
	}
	

}
