package board;

import java.util.ArrayList;

import coursework.model.Entity;
import coursework.model.Flag;
import coursework.model.Robot;

/**
 * @author Matthew Osbornev
 *
 */

public class Board {

	private static ReadAndGenerateBoard br;
	private static String[][] boardString;
	private static Entity[][] boardEntity;
	private static Robot[][] playerLocations;
	private static String boardFile;

	private static int row;
	private static int col;

	/**
	 * Gets a board
	 *
	 * <p>
	 * Generates a new ReadAndGenerateBoard class and sends it the board file.
	 * The board entity array is then returned and set as the board. The board
	 * is also created as a string array
	 * </p>
	 *
	 * @param file
	 *            Board file from input
	 * @throws Exception
	 */

	public Board(String file, int NOP) throws Exception {

		boardFile = file;
		br = new ReadAndGenerateBoard(NOP);

		Board.boardString = br.generateBoardString(boardFile);
		Board.boardEntity = br.generateBoardEntity(boardFile);
		Board.playerLocations = br.generateBoardPlayers(boardFile);

		row = boardString.length;
		col = boardString[1].length;

		// br.printArrayList();

		//printBoard();
		//printPlayerBoard();

	}

	/************************************************************************************/

	/**
	 *
	 * Returns the player robots from the ReadAndGenerateBoard class
	 *
	 * @return br.getPlayerRobots()
	 */
	public ArrayList<Robot> getPlayerRobots() {
		return br.getPlayerRobots();
	}

	/**
	 *
	 * Used to set Robots in the player array
	 *
	 * @param r Row
	 * @param c column
	 * @param value Robot or null
	 */
	public void setPlayerLocation(int r, int c, Robot value) {
		playerLocations[r][c] = value;
	}

	/************************************************************************************/

	/**
	 * Prints the board to console
	 */

	private static void printBoard() {
		for (int i = 0; i < row; i++) {
			for (int str = 0; str < col; str++) {
				System.out.print(boardString[i][str] + "");
			}
			System.out.println("");
		}
	}

	/************************************************************************************/


	/**
	 * Prints the player board to console
	 */


	public void printPlayerBoard() {
		for (int i = 0; i < row; i++) {
			for (int str = 0; str < col; str++) {
				if (playerLocations[i][str] == null) {
					System.out.print("|Empty|");
				} else {
					System.out.print("|ROBOT|");
				}
			}
			System.out.println("");
		}
		System.out.println("");
	}

	/************************************************************************************/

	/**
	 * Turns the dual string array into a single string so it can be printed
	 * more easily
	 *
	 * @return String version of the array
	 */

	public static String arrayToString() {
		String s = "";

		for (int i = 0; i < row; i++) {
			for (int str = 0; str < col; str++) {
				if (playerLocations[i][str] == null) {
					s = s + "|   EMPTY   |";
				} else {
					s = s + "| " + playerLocations[i][str].getID() + " |";
				}
			}
			s = s + "\n";
		}

		return s;
	}

	/************************************************************************************/

	/**
	 *
	 * Return the board array
	 *
	 * @return board array
	 */

	public static String[][] getBoardString() {
		return boardString;
	}

	public static Entity[][] getBoardEntity() {
		return boardEntity;
	}

	public static Entity[][] getBoardPlayers() {
		return playerLocations;
	}

	public Boolean checkPlayerEmpty(int r, int c) {
		if (!(playerLocations[r][c] == null)) {
			return false;
		}
		return true;
	}

	/************************************************************************************/

	/**
	 *
	 * Checks what is in adjacent spaces when moving forward
	 *
	 *
	 * <p> Takes the position the robot is currently in and checks the directions around it.
	 * If it is not null it returns the robot in the space as the robot can not move into it.
	 * This is used in the pushRobotForward function to check if there is a robot. </p>
	 *
	 * @param r Row
	 * @param c Column
	 * @param d Direction
	 * @return Robot or null
	 */

	public Entity checkAdjacentSpaceForward(int r, int c, String d) {
		if (d.equals("North")) {
			if (!(playerLocations[r - 1][c] == null)) {
				return playerLocations[r - 1][c];
			}
		}
		if (d.equals("East")) {
			if (!(playerLocations[r][c + 1] == null)) {
				return playerLocations[r][c + 1];
			}
		}
		if (d.equals("South")) {
			if (!(playerLocations[r + 1][c] == null)) {
				return playerLocations[r + 1][c];
			}
		}
		if (d.equals("West")) {
			if (!(playerLocations[r][c - 1] == null)) {
				return playerLocations[r][c - 1];
			}
		}
		return null;
	}

	/************************************************************************************/

	/**
	 *
	 * Checks what is in adjacent spaces when moving backward
	 *
	 *
	 * <p> Takes the position the robot is currently in and checks the directions around it.
	 * If it is not null it returns the robot in the space as the robot can not move into it.
	 * This is used in the pushRobotForward function to check if there is a robot. </p>
	 *
	 * @param r Row
	 * @param c Column
	 * @param d Direction
	 * @return Robot or null
	 */

	public Entity checkAdjacentSpaceBackward(int r, int c, String d) {
		if (d.equals("North")) {
			if (!(playerLocations[r + 1][c] == null)) {
				return playerLocations[r + 1][c];
			}
		}
		if (d.equals("East")) {
			if (!(playerLocations[r][c - 1] == null)) {
				return playerLocations[r][c - 1];
			}
		}
		if (d.equals("South")) {
			if (!(playerLocations[r - 1][c] == null)) {
				return playerLocations[r - 1][c];
			}
		}
		if (d.equals("West")) {
			if (!(playerLocations[r][c + 1] == null)) {
				return playerLocations[r][c + 1];
			}
		}
		return null;
	}

	/************************************************************************************/

	/**
	 *
	 * Checks if a robot is moving outside of the board
	 *
	 * <p> Checks if the robot is moving out of the board forward in the direction it is facing </p>
	 *
	 * @param r Row
	 * @param c Column
	 * @param d Direction
	 * @return true or false
	 */

	public Boolean checkOutsideBoardForward(int r, int c, String d) {
		if (d.equals("North")) {
			if (r - 1 < 0) {
				return true;
			}
		} else if (d.equals("East")) {
			if (c + 1 > col - 1) {
				return true;
			}
		} else if (d.equals("South")) {
			if (r + 1 > row - 1) {
				return true;
			}
		} else if (d.equals("West")) {
			if (c - 1 < 0) {
				return true;
			}
		}

		return false;
	}

	/************************************************************************************/

	/**
	 *
	 * Checks if a robot is moving outside of the board
	 *
	 * <p> Checks if the robot is moving out of the board backward in the direction it is facing </p>
	 *
	 * @param r Row
	 * @param c Column
	 * @param d Direction
	 * @return true or false
	 */

	public Boolean checkOutsideBoardBackward(int r, int c, String d) {
		if (d.equals("North")) {
			if (r + 1 > row - 1) {
				return true;
			}
		} else if (d.equals("East")) {
			if (c - 1 < 0) {
				return true;
			}
		} else if (d.equals("South")) {
			if (r - 1 < 0) {
				return true;
			}
		} else if (d.equals("West")) {
			if (c + 1 < col - 1) {
				return true;
			}
		}

		return false;
	}

	/************************************************************************************/

	/**
	 *
	 * Returns a Flag if there is one in the same location as the robot
	 *
	 * @param r Row
	 * @param c Column
	 * @return Flag or null
	 */

	public Flag sameEntityLocation(int r, int c) {
		if (boardEntity[r][c].getClass().isInstance(Flag.class)) {
			return (Flag) boardEntity[r][c];
		}
		return null;
	}

	/************************************************************************************/

	public void activate() {
		for (int i = 0; i < row; i++) {
			for (int str = 0; str < col; str++) {
				if (!(playerLocations[i][str] == null) && !(boardEntity[i][str] == null)) {

				}
			}

		}
	}
}
