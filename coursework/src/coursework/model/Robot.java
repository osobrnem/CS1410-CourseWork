package coursework.model;

import java.util.ArrayList;

import board.Board;
import main.Move;

/**
 *
 * @author Muhammad Khan (180196294) & Matthew Osborne (osbornem)
 * @version 18/04/2019
 */
public class Robot extends Entity {
	private static int startingRow;
	private static int startingCol;
	private String ID;
	private boolean isAlive;
	private String direction;
	private ArrayList<Integer> flags;

	private Board br;

	/**
	 *
	 * Sets up the robot with its parameters from the board
	 *
	 * <p>
	 * Also sets up ArrayList to store flags which have been collected
	 * </p>
	 *
	 * @param id
	 * @param startRow
	 * @param startCol
	 */
	public Robot(char id, int startRow, int startCol) {
		super();
		ID = Character.toString(id);

		startingRow = startRow;
		startingCol = startCol;

		row = startRow;
		col = startCol;

		direction = "North";

		isAlive = true;

		flags = new ArrayList<Integer>();
	}

	/************************************************************************************/

	// Getters and setters

	public void setDead() {
		isAlive = false;
	}

	public void setAlive() {
		isAlive = true;
	}

	private int getStartingRow() {
		return startingCol;
	}

	private int getStartingCol() {
		return startingCol;
	}

	public void setDirection(String newDirection) {
		if (newDirection.equals("North")) {
			direction = "North";
		} else if (newDirection.equals("East")) {
			direction = "East";
		} else if (newDirection.equals("South")) {
			direction = "South";
		} else if (newDirection.equals("West")) {
			direction = "West";
		} else {
			throw new IllegalArgumentException("Invalid Direction");
		}
	}

	public String getDirection() {
		return direction;
	}

	public void setBoard(Board br) {
		this.br = br;
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	/************************************************************************************/

	private void respawn() {
		// if dead move back to starting position
		// if thats occupied check NESW systematically then move there
		// test is robot is dead
		if (isAlive == false) {
			// If original starting position is occupied
			if (br.checkPlayerEmpty(startingRow, startingCol)) {
				setLocation(startingRow, startingCol);
			}
			// Try positions NESW
			else if (br.checkPlayerEmpty(startingRow - 1, startingCol)) {
				setLocation(startingRow - 1, startingCol);
			} else if (br.checkPlayerEmpty(startingRow, startingCol + 1)) {
				setLocation(startingRow, startingCol + 1);
			} else if (br.checkPlayerEmpty(startingRow + 1, startingCol)) {
				setLocation(startingRow + 1, startingCol);
			} else {
				setLocation(startingRow, startingCol - 1);
			}

		}
	}

	/************************************************************************************/

	private void collectFlag() {
		if (!(br.sameEntityLocation(row, col) == null)) {
			Flag f = br.sameEntityLocation(row, col);
			if (flags.contains(f)) {
				System.out.println("Flag already collected");
			} else {
				flags.add(f.getID());
			}
		}
	}

}
