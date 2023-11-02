package dk.danskebank.output;

import java.io.IOException;

import dk.danskebank.AggregatedInputFile;

public interface Output {
	
	public void execute(AggregatedInputFile aggregatedInputFile) throws IOException;

}
