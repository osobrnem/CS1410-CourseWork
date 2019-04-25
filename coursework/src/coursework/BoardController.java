package coursework;

/**
 * @author Matthew
 *
 */

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class BoardController {

	@FXML
	private TextField FileInput;
	@FXML
	private TextField NumberInput;

	/**
	 * Set the text area as not editable
	 *
	 * Sets the text field to give a description
	 */
	public void initialize() {

		FileInput.setPromptText("Enter file location");
	}

	/************************************************************************************/

	/**
	 *
	 * Creates a new game
	 *
	 * <p>
	 * Sets the parameters for the file directory given and number of players
	 * </p>
	 *
	 * @throws Exception
	 */

	@FXML
	public void submitButton() throws Exception {
		if (!(FileInput.getText().equals(null)) && Integer.parseInt(NumberInput.getText()) <= 4
				&& Integer.parseInt(NumberInput.getText()) > 0) {
			// GameController.setBoardLocation(FileInput.getText());
			GameController.setBoardLocation("D:\\Desktop\\CS1410 Coursework\\boards\\BIG.BRD");
			GameController.setNumberOfPlayers(Integer.parseInt(NumberInput.getText()));
			FileInput.getScene().getWindow().hide();
		}
	}

	/************************************************************************************/

}
