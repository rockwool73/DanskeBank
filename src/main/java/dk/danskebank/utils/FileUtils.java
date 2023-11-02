package dk.danskebank.utils;

import java.io.File;
import java.io.IOException;

public class FileUtils {
	
	public static final void deleteDirectoryContense(File directory) throws IOException {
		if (!directory.exists()) {
			throw new IOException("File do not exists. ["+directory.getAbsolutePath()+"].");
		} else if (!directory.isDirectory()) {
			throw new IOException("File is not a directory. ["+directory.getAbsolutePath()+"].");
		} else {
			for (File file : directory.listFiles()) {
				if (file.isFile()) {
					file.delete();
				} else if (file.isDirectory()) {
					deleteDirectoryContense(file);
					file.delete();
				}
			}
		}
	}
	

}
