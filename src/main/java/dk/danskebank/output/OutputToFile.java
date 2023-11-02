package dk.danskebank.output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import dk.danskebank.AggregatedInputFile;
import dk.danskebank.LetterCounter;
import dk.danskebank.utils.FileUtils;

public class OutputToFile implements Output {
	
	private File outputFilesDirectory;
	
	public OutputToFile(File outputFilesDirectory) {
		this.outputFilesDirectory = outputFilesDirectory;
	}
	public File getOutputFilesDirectory() {
		return outputFilesDirectory;
	}
	
	@Override
	public void execute(AggregatedInputFile aggregatedInputFile) throws IOException {
		FileUtils.deleteDirectoryContense(getOutputFilesDirectory());
		for (Character letter : aggregatedInputFile.getLetters()) {
			//
			String filename = "FILE_" + Character.toUpperCase(letter) + ".txt";
			File outputFile = new File(getOutputFilesDirectory(), filename);
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true));
			//
			try {
				//
				LetterCounter letterCounter = aggregatedInputFile.getLetterCounter(letter);
				for (String word : letterCounter.getWords()) {					
					String line = word + " " + letterCounter.getWordCount(word);
					writer.write(line);
					writer.newLine();
				}
			} finally {
				writer.flush();
				writer.close();
			}
		}
	}
	
}
