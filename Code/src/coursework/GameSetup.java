
package coursework;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * Sets up basic game through a users input into a GUI.
 *
 * @author Matthew Osborne
 *
 */
public class GameSetup {

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

	public GameSetup() throws Exception {
		// BoardOut.setEditable(false);

		final FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("SelectBoardfile.fxml"));
		final BoardController controller = new BoardController();
		loader.setController(controller);
		try {
			final Parent parent = (Parent) loader.load();

			final Stage BoardStage = new Stage();
			BoardStage.initModality(Modality.APPLICATION_MODAL);
			BoardStage.setScene(new Scene(parent, Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE));
			BoardStage.showAndWait();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		g = new Game(file, playerNumber);

		final FXMLLoader loader1 = new FXMLLoader();
		loader1.setLocation(getClass().getResource("SetPlayerID.fxml"));
		final playerIDController IDcontroller = new playerIDController();
		loader1.setController(IDcontroller);
		try {
			final Parent parent = (Parent) loader1.load();

			final Stage IDStage = new Stage();
			IDStage.initModality(Modality.APPLICATION_MODAL);
			IDStage.setScene(new Scene(parent, Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE));
			IDStage.showAndWait();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		g.setPlayerID();
		// robotStats.setText(g.getRobotDetailstoString());

	}

	public Game getGame() {
		return g;
	}

	/************************************************************************************/

	/**
	 *
	 * Sets the player IDs from the playerIDController
	 *
	 * @param a
	 *            Array of player IDs
	 */
	public static void setPlayerID(String[] a) {
		playerIDs = new String[4];
		playerIDs = a;
	}

	/************************************************************************************/

	/**
	 * @return playerIDs
	 */
	public static String[] getPlayerID() {
		return playerIDs;
	}

	/************************************************************************************/

	/**
	 *
	 * Sets the file
	 *
	 * @param f
	 *            File location
	 */
	public static void setBoardLocation(String f) {
		file = f;
	}

	/************************************************************************************/

	/**
	 * @param NOP
	 *            Number of players
	 */
	public static void setNumberOfPlayers(int NOP) {
		playerNumber = NOP;
	}

}
