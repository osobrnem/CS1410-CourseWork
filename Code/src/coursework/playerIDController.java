package coursework;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 *
 * Controls the SetPlayerID GUI
 *
 * @author Matthew Osborne
 *
 */
public class playerIDController {

	@FXML
	private TextField player1,player2,player3,player4;

	private String playerIDs[];

	/**
	 * Sets the player IDs to an array and passes it to the GameController
	 */
	public void submitButton(){
			playerIDs = new String[4];
			playerIDs[0] = player1.getText();
			playerIDs[1] = player2.getText();
			playerIDs[2] = player3.getText();
			playerIDs[3] = player4.getText();
			GameSetup.setPlayerID(playerIDs);
			player1.getScene().getWindow().hide();
	}
}
