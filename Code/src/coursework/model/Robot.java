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

	/**
	 * Set robot as dead
	 */
	public void setDead() {
		isAlive = false;
	}

	/**
	 * Set robot as alive
	 */
	public void setAlive() {
		isAlive = true;
		direction = "North";
	}

	/**
	 *
	 * Get if the robot is alive
	 *
	 * @return isAlive
	 */
	public Boolean getIsAlive() {
		return isAlive;
	}

	/**
	 *
	 * Get the row the robot started on
	 *
	 * @return startingRow
	 */
	public int getStartingRow() {
		return startingRow;
	}

	/**
	 *
	 * Get the column the robot started on
	 *
	 * @return startingCol
	 */

	public int getStartingCol() {
		return startingCol;
	}

	/**
	 *
	 * Set the direction of the robot
	 *
	 * @param newDirection New direction to set robot
	 */
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

	/**
	 *
	 * Get the direction of the robot
	 *
	 * @return direction
	 */
	public String getDirection() {
		return direction;
	}


	/**
	 *
	 * Get the ID of the robot
	 *
	 * @return ID
	 */
	public String getID() {
		return ID;
	}

	/**
	 *
	 * Set the ID of the robot
	 *
	 * @param id New ID
	 */
	public void setID(String id) {
		ID = id;
	}

	/**
	 *
	 * Return the collected flags ArrayList
	 *
	 * @return flags
	 */
	public ArrayList<Integer> getFlags() {
		return flags;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 *
	 * Make the flags ArrayList into a string
	 */
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
