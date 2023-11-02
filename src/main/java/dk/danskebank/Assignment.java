package dk.danskebank;

import java.io.File;
import java.io.IOException;

import dk.danskebank.output.OutputToConsole;
import dk.danskebank.output.OutputToFile;

public class Assignment {

	private File rootDirectory;

	public Assignment(File rootDirectory) throws IOException {
		if (!rootDirectory.isDirectory()) {
			throw new IOException("Directory [" + rootDirectory.getAbsolutePath() + "] is not valid directory.");
		} else if (!rootDirectory.exists()) {
			throw new IOException("Directory [" + rootDirectory.getAbsolutePath() + "] do not exists.");
		} else {
			this.rootDirectory = rootDirectory;
		}
	}

	public File getRootDirectory() {
		return rootDirectory;
	}

	public File getExludeFile() {
		return new File(getRootDirectory(), "exclude.txt");
	}

	public File getInputFilesDirectory() {
		return new File(getRootDirectory(), "inputfiles");
	}

	public File getOutputFilesDirectory() {
		return new File(getRootDirectory(), "outputfiles");
	}

	public File[] getInputFiles() {
		return getInputFilesDirectory().listFiles(new InputFileFilter());
	}

	public void validate() throws IOException {
		if (!getExludeFile().exists()) {
			throw new IOException("exclude.txt file do not exists, path [" + getExludeFile().getAbsolutePath() + "].");
		}
		if (!getInputFilesDirectory().isDirectory()) {
			throw new IOException("Input files directory [" + getInputFilesDirectory().getAbsolutePath()+ "] is not valid directory.");
		}
		if (!getInputFilesDirectory().exists()) {
			throw new IOException("Input files directory do not exists [" + getInputFilesDirectory().getAbsolutePath() + "].");
		}
		if (getInputFiles().length < 4) {
			throw new IOException("To few input files [" + getInputFiles().length + "], need at least 4. Path ["+ getInputFilesDirectory().getAbsolutePath() + "].");
		}
	}

	public void runAssignment() throws IOException {
		InputFile excludes = new InputFile(getExludeFile());
		excludes.analyse();

		AggregatedInputFile aggregatedInputFile = new AggregatedInputFile();
		for (File file : getInputFiles()) {
			InputFile inputFile = new InputFile(file, excludes.getWords(),false);
			inputFile.analyse();
			aggregatedInputFile.addFile(inputFile);
		}
		new OutputToConsole().execute(aggregatedInputFile);
		new OutputToFile(getOutputFilesDirectory()).execute(aggregatedInputFile);
	}





	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("No arguments provided, needs directory as first argument");
		} else {
			File directory = new File(args[0]);

			try {
				Assignment assignment = new Assignment(directory);
				assignment.validate();
				assignment.runAssignment();
				System.out.println("-- Done --");
			} catch (IOException e) {
				System.err.println("Error running assignment [" + e.getMessage() + "]");
				e.printStackTrace();
			}
		}
	}

}
