package coursework;

import java.util.ArrayList;
import java.util.Arrays;

import coursework.model.ConveyorBelt;
import coursework.model.Entity;
import coursework.model.Flag;
import coursework.model.Gear;
import coursework.model.Pit;
import coursework.model.Robot;

/**
 *
 * Generates Entity Boards
 *
 * @author Matthew Osborne
 *
 */
public class GenerateBoard {

	private static ArrayList<String> boardArray;
	private static ArrayList<Robot> playerRobots;

	private static int row;
	private static int col;
	private static int numberOfPlayers;

	/**
	 *
	 * Sets up parameters
	 *
	 * @param fileArray
	 *            Array generated from ReadFile
	 * @param NOP
	 *            Number of players
	 */
	public GenerateBoard(ArrayList<String> fileArray, int NOP) {
		numberOfPlayers = NOP;
		boardArray = fileArray;
	}

	/************************************************************************************/

	/**
	 * Returns the player array
	 *
	 * @return playerRobots
	 */
	public static ArrayList<Robot> getPlayerRobots() {
		return playerRobots;
	}

	/**
	 *
	 **********************************************************************************/

	/**
	 *
	 * Returns the board of Entity's without players
	 *
	 * @return Entity[][] Dual array of Entities
	 *
	 */
	public static Entity[][] getBoardEntity() {
		return generateBoardEntity(boardArray);
	}

	/************************************************************************************/

	/**
	 *
	 * Returns the board of Entity's with only players
	 *
	 * @return Entity[][] Dual array of Entities(Robot)
	 *
	 */

	public static Entity[][] getBoardPlayer() {
		return generateBoardPlayers();
	}

	/************************************************************************************/

	/**
	 *
	 * Get number of rows
	 *
	 * @return boardArray.size();
	 */
	public static int getNumberOfRows() {
		return boardArray.size();
	}

	/************************************************************************************/

	/**
	 *
	 * Get number of Columns
	 *
	 * @return boardArray.get(1).length();
	 */
	public static int getNumberOfColumns() {
		return boardArray.get(1).length();
	}

	/************************************************************************************/

	/**
	 *
	 * Turn the ArrayList into a two dimensional Entity array
	 *
	 * <p>
	 * Checks the array for known characters and creates the entities that
	 * correspond to it
	 * </p>
	 *
	 * @param boardArray
	 *            The string ArrayList generated from the board file
	 * @return board Two dimensional array (Entity)
	 *
	 */

	private static Entity[][] generateBoardEntity(ArrayList<String> boardArray) {

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
	 * are empty for the robot to move.
	 * </p>
	 *
	 * @return playerArray
	 */

	private static Robot[][] generateBoardPlayers() {
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
