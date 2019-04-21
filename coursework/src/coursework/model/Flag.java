package coursework.model;
/**
 * This class represents the flag, the flag is
 * something for the user to collect to determine
 * the winner of the game
 * @author Akeel Saleem
 * @version 18/04/19
 */
public class Flag extends Entity {
	private int ID;

	public Flag(int i){
		super();
		ID = i;
	}
	//Get the ID
	public int getID() {
		return ID;
	}
	//Set the ID
//	public static void setID(int iD) {
//		ID = iD;
//	}

}