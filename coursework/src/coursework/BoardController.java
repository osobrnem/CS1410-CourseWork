package coursework;

import board.Board;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BoardController {

	@FXML
	private TextField FileInput;
	@FXML
	private TextArea BoardOut;

	public void initialize() {
		BoardOut.setEditable(false);
	}

	/************************************************************************************/

	@FXML
	public void submitButton() throws Exception{

		new Game(FileInput.getText());

		setBoard();
	}

	/************************************************************************************/

	@FXML
	public void setBoard() {
		BoardOut.setText(Board.arrayToString());
	}

}
