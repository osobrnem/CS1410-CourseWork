package coursework;

import java.util.ArrayList;

import coursework.model.ConveyorBelt;
import coursework.model.Entity;
import coursework.model.Flag;
import coursework.model.Gear;
import coursework.model.Pit;
import coursework.model.Robot;

/**
 *
 * Stores the boards. Handles most of the functions which change the board.
 *
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
	 * Generates a new ReadBoard class and sends it the board file. The board
	 * entity array is then returned and set as the board. The board player
	 * array is used to keep the positions of the Robots on the board. The rows
	 * and columns are set using the size of the board.
	 * </p>
	 *
	 * @param file
	 *            Board file from input
	 * @param NOP
	 *            Number of players
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
	 * Returns the player Robots from the ReadBoard class
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
	 * <p>
	 * Mainly used when moving robots. Allows Robots to be added or removed from
	 * the array at a specified point.
	 * </p>
	 *
	 * @param row
	 *            Row
	 * @param col
	 *            column
	 * @param r
	 *            Robot
	 */
	public static void setPlayerLocation(int row, int col, Robot r) {
		playerLocations[row][col] = r;

		if (r != null) {
			r.setRow(row);
			r.setCol(col);
		}
	}

	/************************************************************************************/

	/**
	 * Turns the dual Entity array into a duel string array so it can added to
	 * the board
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
					} else if (boardEntity[i][str] instanceof ConveyorBelt) {
						if (((ConveyorBelt) boardEntity[i][str]).getDirection().equals("North")) {
							s[i][str] = "^^^";
						} else if (((ConveyorBelt) boardEntity[i][str]).getDirection().equals("East")) {
							s[i][str] = ">>>";
						} else if (((ConveyorBelt) boardEntity[i][str]).getDirection().equals("South")) {
							s[i][str] = "vvv";
						} else if (((ConveyorBelt) boardEntity[i][str]).getDirection().equals("West")) {
							s[i][str] = "<<<";
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

	public static Entity checkAdjacentSpaceForward(int r, int c, String d) {
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

	public static Entity checkAdjacentSpaceBackward(int r, int c, String d) {
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

	public static Boolean checkOutsideBoardForward(int r, int c, String d) {
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
	 *            Robot
	 * @return Entity or null
	 */

	public Entity sameEntityLocation(Robot r) {
		if (boardEntity[r.getRow()][r.getCol()] != null) {
			return boardEntity[r.getRow()][r.getCol()];
		}
		return null;
	}

	/************************************************************************************/

	/**
	 *
	 * Gets the number of flags on the board
	 *
	 * @return f Flags
	 */
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

	/**
	 *
	 * Respawns a robot to the board
	 *
	 * <p>
	 * Checks if the original starting location is free. If it isn't it checks
	 * each direction around it until it finds a free one.
	 * </p>
	 *
	 * @param r
	 *            Robot
	 */
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

	/************************************************************************************/

	/**
	 *
	 * Removes a robot from the board
	 *
	 * @param r
	 *            Robot
	 */
	public static void removeRobotFromBoard(Robot r) {
		setPlayerLocation(r.getRow(), r.getCol(), null);
	}
}
