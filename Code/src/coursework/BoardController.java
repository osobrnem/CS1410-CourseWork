package coursework;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

/**
 * Controls the SelectBoardfile GUI
 *
 * @author Matthew Osborne
 *
 */

public class BoardController {

	@FXML
	private TextField FileInput, ProgrammeInput;
	@FXML
	private TextField NumberInput;
	@FXML
	private MenuButton boardMenu;

	/**
	 * Sets the text field to give a description
	 */
	public void initialize() {

		FileInput.setPromptText("Enter file location");
		ProgrammeInput.setPromptText("Enter programme location or leave blank");
	}

	/************************************************************************************/

	/**
	 *
	 * Sets Board locations and number of players.
	 *
	 * <p>
	 * Sets the parameters for the file directory given and number of players.
	 * Doesn't work if board file input is empty.
	 * </p>
	 *
	 */

	@FXML
	public void submitButton() {
		if (!(FileInput.getText().equals("")) && Integer.parseInt(NumberInput.getText()) <= 4
				&& Integer.parseInt(NumberInput.getText()) > 0) {

			GameSetup.setBoardLocation(FileInput.getText(), ProgrammeInput.getText());
			GameSetup.setNumberOfPlayers(Integer.parseInt(NumberInput.getText()));
			FileInput.getScene().getWindow().hide();
		}
	}

	/************************************************************************************/

	/**
	 * Sets the board input to "src//boards//big.brd"
	 */
	@FXML
	public void menuBig() {
		FileInput.setText("src//boards//big.brd");
	}

	/**
	 * Sets the board input to "src//boards//Massive.brd"
	 */

	@FXML
	public void menuMassive() {
		FileInput.setText("src//boards//Massive.brd");
	}

	/**
	 * Sets the board input to "src//boards//small.brd"
	 */

	@FXML
	public void menuSmall() {
		FileInput.setText("src//boards//small.brd");
	}

	/**
	 * Sets the board input to "src//boards//impossible.brd"
	 */

	@FXML
	public void menuImpossible() {
		FileInput.setText("src//boards//impossible.brd");
	}

}
