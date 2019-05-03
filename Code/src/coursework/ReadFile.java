package coursework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Reads a file at a given location
 *
 * @author Matthew Osborne
 *
 */
public class ReadFile {

	private ArrayList<String> fileArray;
	private String fileLocation;
	private String fileType;

	public ReadFile() throws Exception {
	}

	/************************************************************************************/

	/**
	 *
	 * Read the file passed and generate an ArrayList from the file.
	 *
	 * @param fileLocation
	 *            Location of file passed
	 * @param numberOfPlayers
	 *            Number of players on the Board
	 * @throws Exception
	 *             File Not found Exception
	 */
	public void read(String fileLocation, int numberOfPlayers) throws Exception {
		this.fileLocation = fileLocation;
		checkFileType();
		generateArraylist();
		if (fileType.equalsIgnoreCase("BRD")) {
			new GenerateBoard(fileArray, numberOfPlayers);
		} else {
			new GenerateProgramme(fileArray);
		}
	}

	/************************************************************************************/

	/**
	 *
	 * Generate ArrayList
	 *
	 * <p>
	 * Generate an ArrayList from the file at file location. This make it easier to edit
	 * the file. It also make it easier to turn into a two dimensional array.
	 * </p>
	 *

	 * @throws Exception File Not found Exception
	 */
	private void generateArraylist() throws Exception {
		ArrayList<String> Array = new ArrayList<>();

		try (FileReader fr = new FileReader(fileLocation); BufferedReader br = new BufferedReader(fr)) {
			String s;
			while ((s = br.readLine()) != null) {
				Array.add(s);
			}
		}
		Array.remove("format 1");

		if(fileType.equalsIgnoreCase("BRD")){
		checkColAndRowNumbers(Array);
		}

		fileArray = Array;
	}

	/************************************************************************************/

	/**
	 *
	 * Check if the array has the correct dimensions
	 *
	 * @param board
	 *            The string ArrayList generated from the board file
	 */
	private void checkColAndRowNumbers(ArrayList<String> board) {
		for (int i = 0; i < board.size(); i++) {
			if (board.get(0).length() != board.get(i).length()) {
				throw new IllegalArgumentException("File format wrong");
			}
		}

	}

	/**
	 *
	 * Get the file type of a file
	 *
	 */
	protected void checkFileType() {
		fileType = "";

		int i = fileLocation.lastIndexOf('.');
		if (i > 0) {
			fileType = fileLocation.substring(i + 1);
		}

		if (!fileType.equalsIgnoreCase("PRG") && !fileType.equalsIgnoreCase("BRD")) {
			throw new IllegalArgumentException("File type is wrong! File Type:" + fileType);
		}

	}

}
