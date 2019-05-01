/**
 *
 */
package coursework;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

import coursework.model.Flag;
import coursework.model.Gear;
import coursework.model.Pit;
import coursework.model.Robot;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.GameView;

/**
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
	private GameSetup gs;

	private int currentPlayer = 0;

	private Move move;

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
	 *            GameController
	 * @param playerNumber
	 *            Number of players in the game
	 * @throws Exception
	 */
	public Game(String file, GameSetup gs, int playerNumber) throws Exception {
		board = new Board(file, playerNumber);
		playerRobots = board.getPlayerRobots();
		passBoard();
		move = new Move(board);

		this.gs = gs;
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

	public void setNumberOfPlayers(int NOP) {
		numberOfPlayers = NOP;
	}

	/**********************************************************************************/

	private void passBoard() {
		for (int i = 0; i < numberOfPlayers; i++) {
			playerRobots.get(i).setBoard(board);
		}
	}

	/**********************************************************************************/

	/**
	 *
	 * Takes a movement and executes it
	 *
	 * @param m
	 *            Move
	 */
	private void moves(String m) {
		if (playerRobots.get(playerOrder.peek()).getIsAlive()) {
			if (m.equalsIgnoreCase("f")) {
				move.forward(playerRobots.get(playerOrder.peek()));
			} else if (m.equalsIgnoreCase("b")) {
				move.backward(playerRobots.get(playerOrder.peek()));
			} else if (m.equalsIgnoreCase("l")) {
				Move.left(playerRobots.get(playerOrder.peek()));
			} else if (m.equalsIgnoreCase("r")) {
				Move.right(playerRobots.get(playerOrder.peek()));
			} else if (m.equalsIgnoreCase("u")) {
				move.uTurn(playerRobots.get(playerOrder.peek()));
			} else if (m.equalsIgnoreCase("w")) {
				move.doNothing();
			}
		}
	}

	/**********************************************************************************/

	/**
	 *
	 * Turns the robots details into a string
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
	 */
	public void setPlayerID() {
		for (int i = 0; i < numberOfPlayers; i++)
			if (playerRobots.get(i).getID().equalsIgnoreCase("A")) {
				playerRobots.get(i).setID(gs.getPlayerID()[0]);
			} else if (playerRobots.get(i).getID().equalsIgnoreCase("B")) {
				playerRobots.get(i).setID(gs.getPlayerID()[1]);
			} else if (playerRobots.get(i).getID().equalsIgnoreCase("C")) {
				playerRobots.get(i).setID(gs.getPlayerID()[2]);
			} else if (playerRobots.get(i).getID().equalsIgnoreCase("D")) {
				playerRobots.get(i).setID(gs.getPlayerID()[3]);
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
	 */
	private void excecuteMoves() {
		for (int m = 0; m < 5; m++) {
			for (currentPlayer = 0; currentPlayer < numberOfPlayers; currentPlayer++) {
				moves(allPlayerMoves[playerOrder.peek()].poll());
				playerOrder.offerLast(playerOrder.poll());



			}
			playerOrder.offerLast(playerOrder.poll());
			activateBoard();
			GameView.setBoard();
		}
		for (int e = 0; e < numberOfPlayers; e++) {
			allPlayerMoves[e].clear();
		}
		currentPlayer = 0;
		lastMove = "";
		checkGameOver();
	}

	/**********************************************************************************/

	private void checkRobot(Robot r) {
		for (int i = 0; i < numberOfPlayers; i++) {
			if (board.sameEntityLocation(r) instanceof Flag) {
				r.collectFlag((Flag) board.sameEntityLocation(r));
			}
			if (board.sameEntityLocation(r) instanceof Pit) {
				r.setDead();
				board.removeRobotFromBoard(r);
			}
			if (board.sameEntityLocation(r) instanceof Gear) {
				((Gear) board.sameEntityLocation(r)).act(r);
			}

			if (r.getIsAlive() == false) {
				board.respawnRobotsToBoard(r);
				r.setAlive();
			}
		}
		if (checkGameOver() == true) {

			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("GameOver.fxml"));
			try {
				final Parent parent = (Parent) loader.load();

				final Stage GameOver = new Stage();
				GameOver.initModality(Modality.APPLICATION_MODAL);
				GameOver.setScene(new Scene(parent, 500, 100));
				GameOver.showAndWait();

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

	private Boolean checkGameOver() {
		int test = board.getNumberOfFlags();
		for (Robot r : playerRobots) {
			if (!(r == null)) {
				if (r.getFlags().size() == test) {
					return true;
				}
			}
		}
		return false;
	}

	/**********************************************************************************/

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
