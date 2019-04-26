package coursework;

/**
 * @author Matthew
 *
 */

import board.Board;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BoardController {

	@FXML
	private TextField FileInput;
	@FXML
	private TextArea BoardOut;

	/**
	 * Set the text area as not editable
	 *
	 * Sets the text field to give a description
	 */
	public void initialize() {
		BoardOut.setEditable(false);
		FileInput.setPromptText("Enter file location");
	}

	/************************************************************************************/




	/**
	 *
	 * Creates a new game using the file directory given
	 *
	 * @throws Exception
	 */


	@FXML
	public void submitButton() throws Exception{

		Game g = new Game(FileInput.getText());

		setBoard();
	}

	/************************************************************************************/

	/**
	 * Shows a text version of the board
	 */
	@FXML
	public void setBoard() {
		BoardOut.setText(Board.arrayToString());
	}

}
