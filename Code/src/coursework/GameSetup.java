
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

	private static String boardFile;
	private static String programmeFile;
	private static int playerNumber;
	private static String playerIDs[];

	private Game g;

	@FXML
	private TextArea BoardOut;

	@FXML
	private TextArea robotStats;

	@FXML
	private TextField moveInput;

	/**
	 *
	 * Sets up the parameters for the game.
	 *
	 * <p>
	 * Starts by loading the SelectBoardfile GUI. This allows the user to input
	 * the required files and player number. A game is then created using the
	 * data passed from SelectBoardfile. If there is no programme file specified
	 * a select player ID GUI is displayed. This allows the user to input the
	 * player IDs.
	 * </p>
	 *
	 *
	 * @throws Exception
	 *             File Not found Exception
	 */
	public GameSetup() throws Exception {
		// BoardOut.setEditable(false);

		final FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("SelectBoardfile.fxml"));
		final BoardController controller = new BoardController();
		loader.setController(controller);
		try {
			final Parent parent = (Parent) loader.load();

			final Stage BoardStage = new Stage();
			BoardStage.setTitle("Select files");
			BoardStage.initModality(Modality.APPLICATION_MODAL);
			BoardStage.setScene(new Scene(parent, Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE));
			BoardStage.showAndWait();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		g = new Game(boardFile, playerNumber, programmeFile);

		if (programmeFile.equals("")) {
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
		}
	}

	/************************************************************************************/

	/**
	 *
	 * Return the Game class
	 *
	 * @return g Game
	 */
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
	 * @param bf
	 *            Board file location
	 *
	 * @param pf
	 *            Programme file location
	 */
	public static void setBoardLocation(String bf, String pf) {
		boardFile = bf;
		programmeFile = pf;
	}

	/************************************************************************************/

	/**
	 *
	 * Set the number of players playing on the board.
	 *
	 * @param NOP
	 *            Number of players
	 */
	public static void setNumberOfPlayers(int NOP) {
		playerNumber = NOP;
	}

}
