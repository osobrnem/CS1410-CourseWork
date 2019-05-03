/**
 *
 */
package coursework;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

import coursework.model.ConveyorBelt;
import coursework.model.Flag;
import coursework.model.Gear;
import coursework.model.Pit;
import coursework.model.Robot;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * Handles most of the operations for the Game.
 *
 * @author Matthew Osborne
 *
 */

public class Game {

	private Board board;
	private static int numberOfPlayers;
	private ArrayList<Robot> playerRobots;
	private ArrayDeque<String>[] allPlayerMoves;
	private ArrayDeque<Integer> playerOrder;
	private String lastMove = "";

	private int currentPlayer = 0;


	/**
	 *
	 * Sets up the game
	 *
	 * <p>
	 * Sets up the game using the parameters pass from the user. Creates a new
	 * board using the board file. Sets up a ArrayDeque for player for each of
	 * the players.
	 * </p>
	 *
	 * @param file
	 *            Board file
	 * @param gs
	 *            GameSetup
	 * @param playerNumber
	 *            Number of players in the game
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Game(String file, int playerNumber) throws Exception {
		board = new Board(file, playerNumber);
		playerRobots = board.getPlayerRobots();
		new Move(board);

		numberOfPlayers = playerNumber;

		allPlayerMoves = new ArrayDeque[numberOfPlayers];
		playerOrder = new ArrayDeque<Integer>();

		for (int i = 0; i < numberOfPlayers; i++) {
			ArrayDeque<String> moves = new ArrayDeque<String>();
			allPlayerMoves[i] = moves;
			playerOrder.add(i);
		}

	}

	/**********************************************************************************/

	public ArrayDeque<String>[] getAllPlayerMoves() {
		return allPlayerMoves;
	}

	/**********************************************************************************/

	public ArrayDeque<Integer> getPlayerOrder() {
		return playerOrder;
	}

	/**********************************************************************************/

	public ArrayList<Robot> getPlayerRobots() {
		return playerRobots;
	}


	/**********************************************************************************/

	/**
	 *
	 * Sets the number of players playing on the board
	 *
	 * @param NOP
	 *            Number of players
	 */
	public void setNumberOfPlayers(int NOP) {
		numberOfPlayers = NOP;
	}

	/**********************************************************************************/

	/**
	 *
	 * Takes a movement and executes it
	 *
	 * <p>
	 * Receives a movement through an instance variable and does the
	 * corresponding move
	 *
	 * F = Forward B = Backward L = Left R = Right U = U-Turn W = Do nothing
	 *
	 *
	 * </p>
	 *
	 * @param m
	 *            Move
	 */
	private void moves(String m) {
		if (playerRobots.get(playerOrder.peek()).getIsAlive()) {
			if (m.equalsIgnoreCase("f")) {
				Move.forward(playerRobots.get(playerOrder.peek()));
			} else if (m.equalsIgnoreCase("b")) {
				Move.backward(playerRobots.get(playerOrder.peek()));
			} else if (m.equalsIgnoreCase("l")) {
				Move.left(playerRobots.get(playerOrder.peek()));
			} else if (m.equalsIgnoreCase("r")) {
				Move.right(playerRobots.get(playerOrder.peek()));
			} else if (m.equalsIgnoreCase("u")) {
				Move.uTurn(playerRobots.get(playerOrder.peek()));
			} else if (m.equalsIgnoreCase("w")) {
				Move.doNothing();
			}
		}
	}

	/**********************************************************************************/

	/**
	 *
	 * Turns the robots details into a string
	 *
	 * <p>
	 * Used to print the robots details to the GUI. The results printed are
	 * needed for players to get details robot.
	 * </p>
	 *
	 * @return String
	 */
	public String getRobotDetailstoString() {
		String s;

		s = "ID - " + playerRobots.get(currentPlayer).getID() + "\n";
		s = s + "Direction - " + playerRobots.get(currentPlayer).getDirection() + "\n";
		s = s + "Moves - " + Arrays.toString(allPlayerMoves[currentPlayer].toArray(new String[0])) + "\n";
		s = s + "Flags - " + playerRobots.get(currentPlayer).toString();
		return s;
	}

	/**********************************************************************************/

	/**
	 *
	 * Sets the robot's ID for the given player
	 *
	 * ID taken from user input on GameSetup
	 *
	 */
	public void setPlayerID() {
		for (int i = 0; i < numberOfPlayers; i++)
			if (playerRobots.get(i).getID().equalsIgnoreCase("A")) {
				playerRobots.get(i).setID(GameSetup.getPlayerID()[0]);
			} else if (playerRobots.get(i).getID().equalsIgnoreCase("B")) {
				playerRobots.get(i).setID(GameSetup.getPlayerID()[1]);
			} else if (playerRobots.get(i).getID().equalsIgnoreCase("C")) {
				playerRobots.get(i).setID(GameSetup.getPlayerID()[2]);
			} else if (playerRobots.get(i).getID().equalsIgnoreCase("D")) {
				playerRobots.get(i).setID(GameSetup.getPlayerID()[3]);
			}

	}

	/**********************************************************************************/

	/**
	 *
	 * Adds the selected move to the move ArrayDeque
	 *
	 * @param m
	 *            Move
	 */
	public void setPlayerMoves(String m) {
		if (lastMove.equals(m)) {
			throw new IllegalArgumentException("Can not do the same move twice");
		} else {
			if (allPlayerMoves[currentPlayer].size() < 4) {
				allPlayerMoves[currentPlayer].add(m);
				lastMove = m;
			} else {
				allPlayerMoves[currentPlayer].add(m);
				lastMove = m;
				if (currentPlayer != numberOfPlayers - 1) {
					currentPlayer++;
					lastMove = "";
				} else {
					excecuteMoves();
				}
			}

		}
	}

	/**********************************************************************************/

	/**
	 * Calls the function to execute the move at the front of the ArrayDeque
	 *
	 * <p>
	 * Goes through each player and then changes who the first player is by one.
	 * Does all 5 moves. Respawns any robots if they went off the board or into
	 * a pit during moving.
	 * </p>
	 */
	private void excecuteMoves() {
		for (int m = 0; m < 5; m++) {
			for (currentPlayer = 0; currentPlayer < numberOfPlayers; currentPlayer++) {
				// Execute the first move of each robot staring with player one
				moves(allPlayerMoves[playerOrder.peek()].poll());
				// Set the first player to last so it does through the list
				playerOrder.offerLast(playerOrder.poll());

			}
			// Set the next player as the first player (player one goes to the
			// back on the first run)
			playerOrder.offerLast(playerOrder.poll());
			activateBoard();
			GameView.setBoard();
		}
		for (int e = 0; e < numberOfPlayers; e++) {
			allPlayerMoves[e].clear();
		}
		for (int i = 0; i < numberOfPlayers; i++) {
			if (!(playerRobots.get(i).getIsAlive())) {
				board.respawnRobotsToBoard(playerRobots.get(i));
				playerRobots.get(i).setAlive();
				GameView.setBoard();
			}
		}
		currentPlayer = 0;
		lastMove = "";
	}

	/**********************************************************************************/

	/**
	 *
	 * Check if the robot is in the same space as an entity and check if it has
	 * won.
	 *
	 * <p>
	 * Used when the board is activated. A robot is passed from
	 * {@link activateBoard()}. When the robot is checked it is tested against
	 * each possible Entity type it could be on. If it is on an Entity it calls
	 * the corresponding function.
	 *
	 * If the player Robot is on a Flag type Entity it will check what Flags
	 * have already been collected. Since a player needs to collect Flags in
	 * ascending order. It will only allow a Flag to be collected if the
	 * required Flags have been collected, it does this by checking the Flag
	 * array in the Robot class.
	 *
	 * The game over function is checked to see if it is Null. If it isn't null,
	 * meaning the game has been won, it opens a new GUI scene saying "Game
	 * Over". The player details is updated to the player who has won. Closing
	 * this scene closes the entire game.
	 * </p>
	 *
	 * @param r
	 *            Robot
	 */
	protected void checkRobot(Robot r) {
		if (board.sameEntityLocation(r) instanceof Flag) {
			if (!r.getFlags().contains(1)) {
				if (((Flag) board.sameEntityLocation(r)).getID() == 1) {
					r.collectFlag((Flag) board.sameEntityLocation(r));
				}
			} else if (!r.getFlags().contains("2")) {
				if (((Flag) board.sameEntityLocation(r)).getID() == 2) {
					r.collectFlag((Flag) board.sameEntityLocation(r));
				}
			} else if (!r.getFlags().contains("3")) {
				if (((Flag) board.sameEntityLocation(r)).getID() == 3) {
					r.collectFlag((Flag) board.sameEntityLocation(r));
				}
			} else if (!r.getFlags().contains("4")) {
				if (((Flag) board.sameEntityLocation(r)).getID() == 4) {
					r.collectFlag((Flag) board.sameEntityLocation(r));
				}
			}
		}
		if (board.sameEntityLocation(r) instanceof Pit) {
			r.setDead();
			Board.removeRobotFromBoard(r);
		}
		if (board.sameEntityLocation(r) instanceof Gear) {
			((Gear) board.sameEntityLocation(r)).act(r);
		}
		if (board.sameEntityLocation(r) instanceof ConveyorBelt) {
			((ConveyorBelt) board.sameEntityLocation(r)).moveRobot(r);
		}
		if (checkGameOver() != null) {

			currentPlayer = playerRobots.indexOf(checkGameOver());
			GameView.updatePlayerStats();

			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("GameOver.fxml"));
			try {
				final Parent parent = (Parent) loader.load();

				final Stage gameOver = new Stage();
				gameOver.initModality(Modality.APPLICATION_MODAL);
				gameOver.setScene(new Scene(parent, Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE));
				gameOver.showAndWait();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
			System.exit(0);
		}
	}

	/**********************************************************************************/

	/**
	 * Remove the last chosen move
	 */
	public void removeLastMove() {
		if (!(allPlayerMoves[currentPlayer].isEmpty())) {
			allPlayerMoves[currentPlayer].removeLast();
			lastMove = allPlayerMoves[currentPlayer].getLast();
		}
	}

	/**********************************************************************************/

	/**
	 *
	 * Tests each player Robot to see if it has collected all the Flags
	 *
	 * @return r Robot
	 */
	private Robot checkGameOver() {
		int test = board.getNumberOfFlags();
		for (Robot r : playerRobots) {
			if (!(r == null)) {
				if (r.getFlags().size() == test) {
					return r;
				}
			}
		}
		return null;
	}

	/**********************************************************************************/

	/**
	 *
	 * Activates the board
	 *
	 * <p>
	 * Goes through the board and checks each robot from top left to bottom
	 * right.
	 * </p>
	 *
	 */
	private void activateBoard() {
		for (int i = 0; i < Board.getRow(); i++) {
			for (int str = 0; str < Board.getCol(); str++) {
				Robot[][] players = (Robot[][]) board.getBoardPlayers();
				if (players[i][str] != null) {
					checkRobot(players[i][str]);
				}
			}
		}

	}

}
