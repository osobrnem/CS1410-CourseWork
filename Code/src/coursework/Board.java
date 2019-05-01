package coursework;

import java.util.ArrayList;

import coursework.model.Entity;
import coursework.model.Flag;
import coursework.model.Gear;
import coursework.model.Pit;
import coursework.model.Robot;

/**
 * @author Matthew Osborne
 *
 */

public class Board {

	private static ReadBoard br;
	private static Entity[][] boardEntity;
	private static Robot[][] playerLocations;
	private static String boardFile;

	public static int getRow() {
		return row;
	}

	public static int getCol() {
		return col;
	}



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
		br = new ReadBoard(NOP);

		boardEntity = br.getBoardEntity(boardFile);
		playerLocations = (Robot[][]) br.getBoardPlayer(boardFile);

		row = boardEntity.length;
		col = boardEntity[1].length;

		// br.printArrayList();

		// printBoard();
		// printPlayerBoard();

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
	 * @param r
	 *            Row
	 * @param c
	 *            column
	 * @param value
	 *            Robot or null
	 */
	public void setPlayerLocation(int r, int c, Robot value) {
		playerLocations[r][c] = value;
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
	 * Turns the dual Entity array into a duel string array so it can added to the board
	 *
	 * @return String version of the array
	 */

	public static String[][] arrayToStringarray() {
		String[][] s = new String[row][col];

		for (int i = 0; i < row; i++) {
			for (int str = 0; str < col; str++) {
				if (playerLocations[i][str] == null && boardEntity[i][str] == null) {
					s[i][str] = "";
				} else {
					if (playerLocations[i][str] != null && playerLocations[i][str].getIsAlive() == true) {
						s[i][str] = "" + playerLocations[i][str].getID() + "";
					} else if (boardEntity[i][str] instanceof Flag) {
						s[i][str] = "Flag " + ((Flag) boardEntity[i][str]).getID() + "";
					} else if (boardEntity[i][str] instanceof Pit) {
						s[i][str] = "Pit";
					} else if (boardEntity[i][str] instanceof Gear) {
						if (((Gear) boardEntity[i][str]).getRotation().equals("clockwise")) {
							s[i][str] = "Gear +";
						} else if (((Gear) boardEntity[i][str]).getRotation().equals("anticlockwise")) {
							s[i][str] = "Gear -";
						}
					}
				}
			}
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


	public Entity[][] getBoardEntity() {
		return boardEntity;
	}

	public Entity[][] getBoardPlayers() {
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
	 * <p>
	 * Takes the position the robot is currently in and checks the directions
	 * around it. If it is not null it returns the robot in the space as the
	 * robot can not move into it. This is used in the pushRobotForward function
	 * to check if there is a robot.
	 * </p>
	 *
	 * @param r
	 *            Row
	 * @param c
	 *            Column
	 * @param d
	 *            Direction
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
	 * <p>
	 * Takes the position the robot is currently in and checks the directions
	 * around it. If it is not null it returns the robot in the space as the
	 * robot can not move into it. This is used in the pushRobotForward function
	 * to check if there is a robot.
	 * </p>
	 *
	 * @param r
	 *            Row
	 * @param c
	 *            Column
	 * @param d
	 *            Direction
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
	 * <p>
	 * Checks if the robot is moving out of the board forward in the direction
	 * it is facing
	 * </p>
	 *
	 * @param r
	 *            Row
	 * @param c
	 *            Column
	 * @param d
	 *            Direction
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
	 * <p>
	 * Checks if the robot is moving out of the board backward in the direction
	 * it is facing
	 * </p>
	 *
	 * @param r
	 *            Row
	 * @param c
	 *            Column
	 * @param d
	 *            Direction
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
			if (c + 1 > col - 1) {
				return true;
			}
		}

		return false;
	}

	/************************************************************************************/

	/**
	 *
	 * Returns an Entity if there is one in the same location as the robot
	 *
	 * @param r
	 *            Row
	 * @param c
	 *            Column
	 * @return Flag or null
	 */

	public Entity sameEntityLocation(Robot r) {
		if (boardEntity[r.getRow()][r.getCol()] instanceof Flag) {
			return (Flag) boardEntity[r.getRow()][r.getCol()];
		}
		if (boardEntity[r.getRow()][r.getCol()] instanceof Pit) {
			return (Pit) boardEntity[r.getRow()][r.getCol()];
		}
		if (boardEntity[r.getRow()][r.getCol()] instanceof Gear) {
			return (Gear) boardEntity[r.getRow()][r.getCol()];
		}
		return null;
	}

	/************************************************************************************/

	public int getNumberOfFlags() {
		int f = 0;
		for (int i = 0; i < row; i++) {
			for (int str = 0; str < col; str++) {
				if (!(boardEntity[i][str] == null)) {
					if (boardEntity[i][str] instanceof Flag) {
						f++;
					}
				}
			}
		}
		return f;
	}

	/************************************************************************************/

	/************************************************************************************/

	public void respawnRobotsToBoard(Robot r) {
		// If original starting position is occupied
		if (checkPlayerEmpty(r.getStartingRow(), r.getStartingCol())) {
			r.setLocation(r.getStartingRow(), r.getStartingCol());
			setPlayerLocation(r.getStartingRow(), r.getStartingCol(), r);
		}
		// Try positions NESW
		else if (checkPlayerEmpty(r.getStartingRow() - 1, r.getStartingCol())) {
			r.setLocation(r.getStartingRow() - 1, r.getStartingCol());
			setPlayerLocation(r.getStartingRow() - 1, r.getStartingCol(), r);
		} else if (checkPlayerEmpty(r.getStartingRow(), r.getStartingCol() + 1)) {
			r.setLocation(r.getStartingRow(), r.getStartingCol() + 1);
			setPlayerLocation(r.getStartingRow(), r.getStartingCol() + 1, r);
		} else if (checkPlayerEmpty(r.getStartingRow() + 1, r.getStartingCol())) {
			r.setLocation(r.getStartingRow() + 1, r.getStartingCol());
			setPlayerLocation(r.getStartingRow() + 1, r.getStartingCol(), r);
		} else {
			r.setLocation(r.getStartingRow(), r.getStartingCol() - 1);
			setPlayerLocation(r.getStartingRow(), r.getStartingCol() - 1, r);
		}

	}

	public void removeRobotFromBoard(Robot r) {
		setPlayerLocation(r.getRow(), r.getCol(), null);
	}
}
