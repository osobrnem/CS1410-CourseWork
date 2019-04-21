package coursework.model;
/**
 * This class represents the gear for the robot,
 * to determine which direction the robot is facing
 * @authors Akeel Saleem , Lewis Miller
 * @version 18/04/19
 */
public class Gear extends Entity{
	private String rotation;
	/*
	 * Constructor for the Gear class
	 */
	public Gear(String r) {
		super();
		r = rotation;
		
	}
	
	public void act(Robot robot) {
		if(rotation.equals("clockwise")){
			//robot.right();
		} else if (rotation.equals("anticlockwise")) {
			//robot.left();
		}
	}
}
