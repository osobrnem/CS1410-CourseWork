package coursework.model;

import coursework.Move;

/**
 * This class represents the gear for the robot,
 * to determine which direction the robot is facing
 * @authors Akeel Saleem , Lewis Miller
 * @version 18/04/19
 */
public class Gear extends Entity{
	private String rotation;
	
	
	public String getRotation() {
		return rotation;
	}

	/*
	 * Constructor for the Gear class
	 */
	public Gear(String r) {
		super();
		rotation = r;
	}
	
	public void act(Robot robot) {
		if(rotation.equals("clockwise")){
			Move.right(robot);
		} else if (rotation.equals("anticlockwise")) {
			Move.left(robot);
		}
	}
}
