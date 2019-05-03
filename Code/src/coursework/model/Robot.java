package coursework.model;

import java.util.ArrayList;

/**
 *
 * The Entity the user interacts with.
 *
 * @author Muhammad Khan (180196294) and Matthew Osborne (osbornem)
 * @version 18/04/2019
 */
public class Robot extends Entity {
	private int startingRow;
	private int startingCol;
	private String ID;
	private boolean isAlive;


	private String direction;
	private ArrayList<Integer> flags;


	/**
	 *
	 * Sets up the robot with its parameters from the board
	 *
	 * <p>
	 * Also sets up ArrayList to store flags which have been collected
	 * </p>
	 *
	 * @param id The name of the players Robot
	 * @param startRow The row the robot starts at
	 * @param startCol The column the robot starts at
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
		direction = "North";
	}

	public Boolean getIsAlive() {
		return isAlive;
	}

	public int getStartingRow() {
		return startingRow;
	}

	public int getStartingCol() {
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


	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public ArrayList<Integer> getFlags() {
		return flags;
	}

	@Override
	public String toString() {
		return "" + flags;
	}


	/************************************************************************************/

	/**
	 * Add the flag to the array if it is not already collected
	 *
	 * @param f Flag
	 */
	public void collectFlag(Flag f) {
		if (flags.contains(f.getID())) {
			System.out.println("Flag already collected");
		} else {
			flags.add(f.getID());
		}
	}


}
