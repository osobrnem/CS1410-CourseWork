package coursework;

import java.io.BufferedReader;

/**
 * @author Matthew Osborne
 *
 */

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

import coursework.model.ConveyorBelt;
import coursework.model.Entity;
import coursework.model.Flag;
import coursework.model.Gear;
import coursework.model.Pit;
import coursework.model.Robot;

/**
 * @author Matthew Osborne
 *
 */
public class ReadBoard {

	private static ArrayList<String> boardArray;
	private ArrayList<Robot> playerRobots;

	private static int row;
	private static int col;
	private int numberOfPlayers;

	public ReadBoard(int NOP) throws Exception {
		numberOfPlayers = NOP;
	}

	/************************************************************************************/

	/**
	 * Returns the player array
	 *
	 * @returnplayerRobots
	 */
	public ArrayList<Robot> getPlayerRobots() {
		return playerRobots;
	}

	/**
	 * @throws Exception
	 **********************************************************************************/

	/**
	 *
	 * Returns the board of Entity's without players
	 *
	 * @param file
	 * @return Entity[][]
	 * @throws Exception
	 */
	public Entity[][] getBoardEntity(String file) throws Exception {
		return generateBoardEntity(file);
	}

	/************************************************************************************/

	/**
	 *
	 * Returns the board of Entity's with only players
	 *
	 * @param file
	 * @return Entity[][] (Robot)
	 * @throws Exception
	 */

	public Entity[][] getBoardPlayer(String file) throws Exception {
		return generateBoardPlayers(file);
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

	/************************************************************************************/

	/**
	 *
	 * Generate the board from other functions
	 *
	 * @param boardFile
	 * @return board
	 * @throws Exception
	 */
	private Entity[][] generateBoardEntity(String boardFile) throws Exception {
		checkFileType(boardFile);
		generateArraylist(boardFile);
		return arrayToBoardEntityArray(boardArray);
	}

	/************************************************************************************/
	/**
	 *
	 * Get the file type of a file
	 *
	 * @param fileName
	 * @return fileType returns the type of file it is
	 */
	protected void checkFileType(String fileName) {
		String fileType = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			fileType = fileName.substring(i + 1);
		}

		if (!fileType.equalsIgnoreCase("BRD")) {
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
	private void generateArraylist(String boardFile) throws Exception {
		ArrayList<String> Array = new ArrayList<>();

		try (FileReader fr = new FileReader(boardFile); BufferedReader br = new BufferedReader(fr)) {
			String s;
			while ((s = br.readLine()) != null) {
				Array.add(s);
			}
		}
		Array.remove("format 1");

		checkColAndRowNumbers(Array);

		boardArray = Array;
	}

	/************************************************************************************/

	/**
	 *
	 * Check if the array has the correct dimensions
	 *
	 * @param board
	 * @return boolean
	 */
	private boolean checkColAndRowNumbers(ArrayList<String> board) {
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

	private Entity[][] arrayToBoardEntityArray(ArrayList<String> boardArray) throws Exception {

		row = getNumberOfRows();
		col = getNumberOfColumns();

		String[] players = new String[] { "A", "B", "C", "D" };
		String[] flags = new String[] { "1", "2", "3", "4" };

		Entity[][] boardEntity = new Entity[row][col];

		for (int i = 0; i < row; i++) {
			for (int str = 0; str < col; str++) {
				if (boardArray.get(i).charAt(str) == '.') {
					boardEntity[i][str] = null;
				} else if (Arrays.stream(flags).anyMatch(Character.toString(boardArray.get(i).charAt(str))::equals)) {
					boardEntity[i][str] = new Flag(Character.getNumericValue(boardArray.get(i).charAt(str)));
				} else if (Arrays.stream(players).anyMatch(Character.toString(boardArray.get(i).charAt(str))::equals)) {
					boardEntity[i][str] = null;
				} else if ("x".equalsIgnoreCase(Character.toString(boardArray.get(i).charAt(str)))) {
					boardEntity[i][str] = new Pit();
				} else if ("+".equalsIgnoreCase(Character.toString(boardArray.get(i).charAt(str)))) {
					boardEntity[i][str] = new Gear("clockwise");
				} else if ("-".equalsIgnoreCase(Character.toString(boardArray.get(i).charAt(str)))) {
					boardEntity[i][str] = new Gear("anticlockwise");
				} else if ("^".equalsIgnoreCase(Character.toString(boardArray.get(i).charAt(str)))) {
					boardEntity[i][str] = new ConveyorBelt("North");
				} else if ("<".equalsIgnoreCase(Character.toString(boardArray.get(i).charAt(str)))) {
					boardEntity[i][str] = new ConveyorBelt("West");
				} else if (">".equalsIgnoreCase(Character.toString(boardArray.get(i).charAt(str)))) {
					boardEntity[i][str] = new ConveyorBelt("East");
				} else if ("v".equalsIgnoreCase(Character.toString(boardArray.get(i).charAt(str)))) {
					boardEntity[i][str] = new ConveyorBelt("South");
				} else {
					throw new IllegalArgumentException("BOARD HAS INVALID CHARACTER");
				}
			}
		}

		return boardEntity;
	}

	/**
	 *
	 * Generates the player array
	 *
	 * <p>
	 * Player array is used so that it is easier to check if the adjacent places
	 * are empty for the robot to move
	 * </p>
	 *
	 * @param boardFile
	 * @return playerArray
	 */

	private Robot[][] generateBoardPlayers(String boardFile) {
		String[] players = null;
		String[] boardEntities = new String[] { "1", "2", "3", "4", "." };

		Robot[][] playerArray = new Robot[row][col];
		Robot[] tempPlayerArray = new Robot[4];

		if (numberOfPlayers == 1) {
			players = new String[] { "A" };
			// players = player;
		} else if (numberOfPlayers == 2) {
			players = new String[] { "A", "B" };
		} else if (numberOfPlayers == 3) {
			players = new String[] { "A", "B", "C" };
		} else if (numberOfPlayers == 4) {
			players = new String[] { "A", "B", "C", "D" };
		}

		for (int i = 0; i < row; i++) {
			for (int str = 0; str < col; str++) {
				if (Arrays.stream(boardEntities).anyMatch(Character.toString(boardArray.get(i).charAt(str))::equals)) {
					playerArray[i][str] = null;
				} else if (Arrays.stream(players).anyMatch(Character.toString(boardArray.get(i).charAt(str))::equals)) {
					playerArray[i][str] = new Robot(boardArray.get(i).charAt(str), i, str);
					if (playerArray[i][str].getID().equalsIgnoreCase("A") && numberOfPlayers >= 1) {
						tempPlayerArray[0] = playerArray[i][str];
					} else if (playerArray[i][str].getID().equalsIgnoreCase("B") && numberOfPlayers >= 2) {
						tempPlayerArray[1] = playerArray[i][str];
					} else if (playerArray[i][str].getID().equalsIgnoreCase("C") && numberOfPlayers >= 3) {
						tempPlayerArray[2] = playerArray[i][str];
					} else if (playerArray[i][str].getID().equalsIgnoreCase("D") && numberOfPlayers >= 4) {
						tempPlayerArray[3] = playerArray[i][str];
					}
				}
			}
		}
		playerRobots = new ArrayList<Robot>(Arrays.asList(tempPlayerArray));
		return playerArray;
	}

}
