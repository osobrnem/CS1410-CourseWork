package coursework;

import coursework.model.Robot;


/**
*
* @author Lewis Miller & Matthew Osborne (osbornem)
* @version 18/04/2019
*/


public class Move {

	private Board br;

	/**
	 *
	 * Sets the board
	 *
	 * @param br
	 */
	public Move(Board br) {
		this.br = br;
	}

	// MOVES

	/**
	 *
	 * Moves the robot forward
	 *
	 *  <p> First checks if the robot is moving outside the board dimensions and if it is destroys it.
	 *  Then checks for if the space next to the robot has another robot in it. If is has it calls the pushRobotForward function.
	 *  If there is no robot next to it the robots location and board location is updated. </p>
	 *
	 *
	 * @param r
	 */

	public void forward(Robot r) {

		int row = r.getRow();
		int col = r.getCol();
		String direction = r.getDirection();

		if (br.checkOutsideBoardForward(row, col, direction) == true) {
			r.setDead();
			br.setPlayerLocation(row, col, null);
		}
		else if (br.checkAdjacentSpaceForward(row, col, direction) == null) {
			if (direction.equals("North")) {
				r.setRow(row - 1);
				br.setPlayerLocation(row, col, null);
				br.setPlayerLocation(row - 1, col, r);
			} else if (direction.equals("East")) {
				r.setCol(col + 1);
				br.setPlayerLocation(row, col, null);
				br.setPlayerLocation(row, col +1 , r);
			} else if (direction.equals("South")) {
				r.setRow(row + 1);
				br.setPlayerLocation(row, col, null);
				br.setPlayerLocation(row + 1, col, r);
			} else if (direction.equals("West")) {
				r.setCol(col - 1);
				br.setPlayerLocation(row, col, null);
				br.setPlayerLocation(row, col - 1, r);
			}
		} else {
			Robot robotPush = (Robot) br.checkAdjacentSpaceForward(row, col, direction);
			pushRobotForward(r, robotPush);
		}
	}

	/************************************************************************************/

	/**
	 *
	 * Moves the robot backward
	 *
	 *  <p> First checks if the robot is moving outside the board dimensions and if it is destroys it.
	 *  Then checks for if the space next to the robot has another robot in it. If is has it calls the pushRobotBackward function.
	 *  If there is no robot next to it the robots location and board location is updated. </p>
	 *
	 *
	 * @param r
	 */

	public void backward(Robot r) {

		int row = r.getRow();
		int col = r.getCol();
		String direction = r.getDirection();

		if (br.checkOutsideBoardBackward(row, col, direction) == true) {
			r.setDead();
			br.setPlayerLocation(row, col, null);
		}
		else if (br.checkAdjacentSpaceBackward(row, col, direction) == null) {
			if (direction.equals("North")) {
				r.setRow(row + 1);
				br.setPlayerLocation(row, col, null);
				br.setPlayerLocation(row + 1, col, r);
			} else if (direction.equals("East")) {
				r.setCol(col - 1);
				br.setPlayerLocation(row, col, null);
				br.setPlayerLocation(row, col - 1, r);
			} else if (direction.equals("South")) {
				r.setRow(row - 1);
				br.setPlayerLocation(row, col, null);
				br.setPlayerLocation(row - 1, col, r);
			} else if (direction.equals("West")) {
				r.setCol(col + 1);
				br.setPlayerLocation(row, col, null);
				br.setPlayerLocation(row, col + 1, r);
			}
		} else {
			Robot robotPush = (Robot) br.checkAdjacentSpaceBackward(row, col, direction);
			pushRobotBackward(r, robotPush);
		}
	}

	/************************************************************************************/

	/**
	 *
	 * Moves the current and the robot in front forwards
	 *
	 * <p> First checks the direction the robot is facing so the robot in front is pushed the right way.
	 * Then calls the corresponding function to move the robot. </p>
	 *
	 * @param r
	 * @param robot
	 */

	public void pushRobotForward(Robot r, Robot robotPush) {
		if (r.getDirection().equals("South") && robotPush.getDirection().equals("North")) {
			backward(robotPush);
			forward(r);
		} else if (r.getDirection().equals("West") && robotPush.getDirection().equals("East")) {
			backward(robotPush);
			forward(r);
		} else if(r.getDirection().equals("East") && robotPush.getDirection().equals("North")){
			right(robotPush);
			forward(robotPush);
			left(robotPush);
			forward(r);
		} else if(r.getDirection().equals("East") && robotPush.getDirection().equals("South")){
			left(robotPush);
			forward(robotPush);
			right(robotPush);
			forward(r);
		} else if(r.getDirection().equals("West") && robotPush.getDirection().equals("South")){
			right(robotPush);
			forward(robotPush);
			left(robotPush);
			forward(r);
		} else if(r.getDirection().equals("West") && robotPush.getDirection().equals("North")){
			left(robotPush);
			forward(robotPush);
			right(robotPush);
			forward(r);
		} else {
			forward(robotPush);
			forward(r);
		}
	}


	/************************************************************************************/

	/**
	 *
	 * Moves the current and the robot in front backwards
	 *
	 * <p> First checks the direction the robot is facing so the robot in front is pushed the right way.
	 * Then calls the corresponding function to move the robot. </p>
	 *
	 * @param r
	 * @param robot
	 */

	public void pushRobotBackward(Robot r, Robot robotPush) {
		if (r.getDirection().equals("South") && robotPush.getDirection().equals("North")) {
			forward(robotPush);
			backward(r);
		} else if (r.getDirection().equals("West") && robotPush.getDirection().equals("East")) {
			forward(robotPush);
			backward(r);
		} else if(r.getDirection().equals("East") && robotPush.getDirection().equals("North")){
			left(robotPush);
			forward(robotPush);
			right(robotPush);
			forward(r);
		} else if(r.getDirection().equals("East") && robotPush.getDirection().equals("South")){
			right(robotPush);
			forward(robotPush);
			left(robotPush);
			forward(r);
		} else if(r.getDirection().equals("West") && robotPush.getDirection().equals("South")){
			left(robotPush);
			forward(robotPush);
			right(robotPush);
			forward(r);
		} else if(r.getDirection().equals("West") && robotPush.getDirection().equals("North")){
			right(robotPush);
			forward(robotPush);
			left(robotPush);
			forward(r);
		} else {
			backward(robotPush);
			backward(r);
		}
	}

	/************************************************************************************/

	/**
	 *
	 * Turns the robot left
	 *
	 * <p> Takes the robots current direction and changes it by 90 degrees to the left.</p>
	 *
	 * @param r
	 */

	public static void left(Robot r) {
		String direction = r.getDirection();

		if (direction.equals("North")) {
			r.setDirection("West");
		} else if (direction.equals("East")) {
			r.setDirection("North");
		} else if (direction.equals("South")) {
			r.setDirection("East");
		} else if (direction.equals("West")) {
			r.setDirection("South");
		}
	}

	/************************************************************************************/

	/**
	 *
	 * Turns the robot right
	 *
	 * <p> Takes the robots current direction and changes it by 90 degrees to the right.</p>
	 *
	 * @param r
	 */

	public static void right(Robot r) {
		String direction = r.getDirection();

		if (direction.equals("North")) {
			r.setDirection("East");
		} else if (direction.equals("East")) {
			r.setDirection("South");
		} else if (direction.equals("South")) {
			r.setDirection("West");
		} else if (direction.equals("West")) {
			r.setDirection("North");
		}
	}

	/************************************************************************************/

	/**
	 *
	 * Turns the robot 180 degrees
	 *
	 * @param r
	 */

	public void uTurn(Robot r) {
		String direction = r.getDirection();

		if (direction.equals("North")) {
			r.setDirection("South");
		} else if (direction.equals("East")) {
			r.setDirection("West");
		} else if (direction.equals("South")) {
			r.setDirection("North");
		} else if (direction.equals("West")) {
			r.setDirection("East");
		}
	}

	/************************************************************************************/

	/**
	 * Does nothing
	 */

	public void doNothing() {
	}
}
