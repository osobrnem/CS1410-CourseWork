package board;


/**
 * @author Matthew
 *
 */

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import coursework.model.Entity;
import coursework.model.Flag;
import coursework.model.Robot;

public class ReadAndGenerateBoard {

	private static String[][] boardString;
	private static Entity[][] boardEntity;
	private static ArrayList<String> boardArray;
	private static int row;
	private static int col;

	public ReadAndGenerateBoard() throws Exception {
	}

	/************************************************************************************/

	/**
	 *
	 * Generate the board from other functions
	 *
	 * @param boardFile
	 * @return board
	 * @throws Exception
	 */
	public String[][] generateBoardString(String boardFile) throws Exception {

		checkFileType(boardFile);

		generateArraylist(boardFile);
		boardString = arrayToBoardStringArray(boardArray);
		return boardString;
	}

	/************************************************************************************/

	/**
	 *
	 * Generate the board from other functions
	 *
	 * @param boardFile
	 * @return board
	 * @throws Exception
	 */
	public Entity[][] generateBoardEntity(String boardFile) throws Exception {

		checkFileType(boardFile);

		generateArraylist(boardFile);
		boardEntity = arrayToBoardEntityArray(boardArray);
		return boardEntity;
	}

	/************************************************************************************/
	/**
	 *
	 * Get the file type of a file
	 *
	 * @param fileName
	 * @return fileType returns the type of file it is
	 */
	public void checkFileType(String fileName) {
		String fileType = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			fileType = fileName.substring(i + 1);
		}

		if (!fileType.equals("BRD")) {
			throw new IllegalArgumentException("File type is wrong");
		}

	}

	/************************************************************************************/

	/**
	 *
	 * Generate ArrayList
	 *
	 * <p>
	 * Generate an ArrayList from the scanned file. This make it easier to edit
	 * the file. It also make it easier to turn into a two dimensional array.
	 * </p>
	 *
	 * @param boardFile
	 * @throws Exception
	 */
	public void generateArraylist(String boardFile) throws Exception {
		ArrayList<String> Array = new ArrayList<>();

		try (Scanner s = new Scanner(new FileReader(boardFile))) {
			while (s.hasNext()) {
				Array.add(s.nextLine());

			}
		} catch (Exception e) {
		}
		Array.remove("format 1");

		checkColAndRowNumbers(Array);

		boardArray = Array;
	}

	/************************************************************************************/

	/**
	 *
	 * Print the ArrayList
	 *
	 * <p>
	 * Print the output of the ArrayList to console. This should match what is
	 * in the .BRD file except it should not have the format 1
	 * </p>
	 *
	 *
	 */
	public void printArrayList() {

		for (int i = 0; i < boardArray.size(); i++) {
			for (int str = 0; str < boardArray.get(i).length(); str++) {
				System.out.print(boardArray.get(i).charAt(str));
			}
			System.out.println("");
		}

	}

	/************************************************************************************/

	/**
	 *
	 * Check if the array has the correct dimensions
	 *
	 * @param board
	 * @return boolean
	 */
	public boolean checkColAndRowNumbers(ArrayList<String> board) {
		for (int i = 0; i < board.size(); i++) {
			if (board.get(0).length() != board.get(i).length()) {
				System.out.println("NUMBER OF SPACES IN EACH ROW ARE NOT EQUAL!");
				return false;
			}
		}

		return true;

	}

	/************************************************************************************/

	/**
	 *
	 * Turn the ArrayList into a two dimensional array
	 *
	 * <p>
	 * Checks the file for know characters and creates a String that correspond
	 * to it
	 * </p>
	 *
	 * @param boardArray
	 * @return board two dimensional array (String)
	 * @throws Exception
	 */
	public String[][] arrayToBoardStringArray(ArrayList<String> boardArray) throws Exception {

		row = getNumberOfRows();
		col = getNumberOfColumns();

		boardString = new String[row][col];

		for (int i = 0; i < row; i++) {
			for (int str = 0; str < col; str++) {
				if (boardArray.get(i).charAt(str) == '.') {
					boardString[i][str] = "|  Empty  |";
				} else if (boardArray.get(i).charAt(str) == '1') {
					boardString[i][str] = "|  Flag1  |";
				} else if (boardArray.get(i).charAt(str) == '2') {
					boardString[i][str] = "|  Flag2  |";
				} else if (boardArray.get(i).charAt(str) == '3') {
					boardString[i][str] = "|  Flag3  |";
				} else if (boardArray.get(i).charAt(str) == '4') {
					boardString[i][str] = "|  Flag4  |";
				} else if (boardArray.get(i).charAt(str) == 'A') {
					boardString[i][str] = "| Player1 |";
				} else if (boardArray.get(i).charAt(str) == 'B') {
					boardString[i][str] = "| Player2 |";
				} else if (boardArray.get(i).charAt(str) == 'C') {
					boardString[i][str] = "| Player3 |";
				} else if (boardArray.get(i).charAt(str) == 'D') {
					boardString[i][str] = "| Player4 |";
				} else {
					boardString[i][str] = "|Not empty|";
					System.out.println("BOARD HAS INVALID CHARACTER");
				}
			}
		}

		return boardString;
	}

	/************************************************************************************/

	/**
	 *
	 * Turn the ArrayList into a two dimensional Entity array
	 *
	 * <p>
	 * Checks the file for know characters and creates the entities that
	 * correspond to it
	 * </p>
	 *
	 * @param boardArray
	 * @return board two dimensional array (Entity)
	 * @throws Exception
	 */

	public Entity[][] arrayToBoardEntityArray(ArrayList<String> boardArray) throws Exception {

		row = getNumberOfRows();
		col = getNumberOfColumns();

		String[] flags = new String[] { "1", "2", "3", "4" };
		String[] players = new String[] { "A", "B", "C", "D" };

		boardEntity = new Entity[row][col];

		for (int i = 0; i < row; i++) {
			for (int str = 0; str < col; str++) {
				if (boardArray.get(i).charAt(str) == '.') {
					boardEntity[i][str] = null;
				} else if (Arrays.stream(flags).anyMatch(Character.toString(boardArray.get(i).charAt(str))::equals)) {
					boardEntity[i][str] = new Flag(boardArray.get(i).charAt(str));
				} else if (Arrays.stream(players).anyMatch(Character.toString(boardArray.get(i).charAt(str))::equals)) {
					boardEntity[i][str] = new Robot(boardArray.get(i).charAt(str));
				} else {
					throw new IllegalArgumentException("BOARD HAS INVALID CHARACTER");
				}
			}
		}

		return boardEntity;
	}

	/************************************************************************************/

	/**
	 *
	 * Get number of rows
	 *
	 * @return rows
	 */
	public int getNumberOfRows() {
		return boardArray.size();
	}

	/************************************************************************************/

	/**
	 *
	 * Get number of Columns
	 *
	 * @return Columns
	 */
	public int getNumberOfColumns() {
		return boardArray.get(1).length();
	}

}
