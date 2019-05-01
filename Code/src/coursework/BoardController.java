package coursework;

/**
 * @author Matthew
 *
 */

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class BoardController {

	@FXML
	private TextField FileInput;
	@FXML
	private TextField NumberInput;
	@FXML
	private MenuButton boardMenu;

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
		if (!(FileInput.getText().equals("")) && Integer.parseInt(NumberInput.getText()) <= 4
				&& Integer.parseInt(NumberInput.getText()) > 0) {
			GameSetup.setBoardLocation(FileInput.getText());
			GameSetup.setNumberOfPlayers(Integer.parseInt(NumberInput.getText()));
			FileInput.getScene().getWindow().hide();
		}
	}

	/************************************************************************************/

	@FXML
	public void menuBig() {
		FileInput.setText("src//boards//big.brd");
	}

	@FXML
	public void menuMassive() {
		FileInput.setText("src//boards//Massive.brd");
	}

	@FXML
	public void menuSmall() {
		FileInput.setText("src//boards//small.brd");
	}

	@FXML
	public void menuImpossible() {
		FileInput.setText("src//boards//impossible.brd");
	}

}
