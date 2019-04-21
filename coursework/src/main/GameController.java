
package main;

import java.awt.Insets;
import java.io.IOException;

import board.Board;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Matthew Osborne
 *
 */
public class GameController {

	private static String file;
	private static int playerNumber;
	private static String playerIDs[];

	private Game g;

	@FXML
	private TextArea BoardOut;

	@FXML
	private TextArea robotStats;

	@FXML
	private TextField moveInput;

	public void initialize() throws Exception {
		BoardOut.setEditable(false);

		final FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("SelectBoardfile.fxml"));
		final BoardController controller = new BoardController();
		loader.setController(controller);
		try {
			final Parent parent = (Parent) loader.load();

			final Stage BoardStage = new Stage();
			BoardStage.initModality(Modality.APPLICATION_MODAL);
			BoardStage.setScene(new Scene(parent, 500, 100));
			BoardStage.showAndWait();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		g = new Game(file, this, playerNumber);

		final FXMLLoader loader1 = new FXMLLoader();
		loader1.setLocation(getClass().getResource("SetPlayerID.fxml"));
		final playerIDController IDcontroller = new playerIDController();
		loader1.setController(IDcontroller);
		try {
			final Parent parent = (Parent) loader1.load();

			final Stage IDStage = new Stage();
			IDStage.initModality(Modality.APPLICATION_MODAL);
			IDStage.setScene(new Scene(parent, 500, 300));
			IDStage.showAndWait();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		g.setPlayerID();
		setBoard();
		robotStats.setText(g.getRobotDetailstoString());

	}

	/************************************************************************************/

	/**
	 * Sets the next move as forward
	 */

	@FXML
	public void forwardPressed() {

		// g.moves("f");
		g.setPlayerMoves("F");
		robotStats.setText(g.getRobotDetailstoString());
	}

	/************************************************************************************/

	/**
	 * Sets the next move as backward
	 */

	@FXML
	public void backwardPressed() {

		// g.moves("b");
		g.setPlayerMoves("B");
		robotStats.setText(g.getRobotDetailstoString());
	}

	/************************************************************************************/

	/**
	 * Sets the next move as left
	 */

	@FXML
	public void leftPressed() {

		// g.moves("l");
		g.setPlayerMoves("L");
		robotStats.setText(g.getRobotDetailstoString());
	}

	/************************************************************************************/

	/**
	 * Sets the next move as right
	 */

	@FXML
	public void rightPressed() {

		// g.moves("r");
		g.setPlayerMoves("R");
		robotStats.setText(g.getRobotDetailstoString());
	}

	/************************************************************************************/

	/**
	 * Sets the next move as U-turn
	 */

	@FXML
	public void uTurnPressed() {

		// g.moves("u");
		g.setPlayerMoves("U");
		robotStats.setText(g.getRobotDetailstoString());
	}

	/************************************************************************************/

	/**
	 * Sets the next move as do nothing
	 */

	@FXML
	public void doNothingPressed() {

		// g.moves("w");
		g.setPlayerMoves("W");
		robotStats.setText(g.getRobotDetailstoString());
	}

	/************************************************************************************/

	/**
	 * Calls the function to remove the last move
	 */

	@FXML
	public void removePressed() {
		g.removeLastMove();
		robotStats.setText(g.getRobotDetailstoString());
	}

	/************************************************************************************/

	/**
	 * Shows/updates the board on the GUI
	 */

	public void setBoard() {
		BoardOut.setText(Board.arrayToString());
		BoardOut.setFont(Font.font("monospaced TrueType", 20));
	}

	/************************************************************************************/

	/**
	 * Sets the player IDs from the playerIDController
	 */

	public static void setPlayerID(String[] a) {
		playerIDs = new String[4];
		playerIDs = a;
	}

	/************************************************************************************/

	/**
	 * @return playerIDs
	 */
	public String[] getPlayerID() {
		return playerIDs;
	}

	/************************************************************************************/

	/**
	 * Sets the file
	 */

	public static void setBoardLocation(String f) {
		file = f;
	}

	/************************************************************************************/

	/**
	 * Sets the number of players
	 */

	public static void setNumberOfPlayers(int i) {
		playerNumber = i;
	}

}
