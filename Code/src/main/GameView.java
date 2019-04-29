package main;

import coursework.Board;
import coursework.Game;
import coursework.GameSetup;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Matthew Osborne
 *
 */

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author Matthew Osborne
 *
 */
public class GameView {

	private GameSetup gs;
	private static Game g;

	private static VBox vMain;
	private static HBox hButtons;
	private static HBox hBoard;
	private static GridPane gridPane;
	private static Button forwardButton;
	private static Button backwardButton;
	private static Button leftButton;
	private static Button rightButton;
	private static Button uTurnButton;
	private static Button doNothingButton;
	private static Button removeButton;
	private static TextArea robotStats;
	private static Stage boardView;

	public GameView() throws Exception {

		gs = new GameSetup();

		g = gs.getGame();

		vMain = new VBox();
		hButtons = new HBox();
		hBoard = new HBox();
		gridPane = new GridPane();
		forwardButton = new Button();
		backwardButton = new Button();
		leftButton = new Button();
		rightButton = new Button();
		uTurnButton = new Button();
		doNothingButton = new Button();
		removeButton = new Button();
		robotStats = new TextArea();

		forwardButton.setOnAction(forwardPressed);
		backwardButton.setOnAction(backwardPressed);
		leftButton.setOnAction(leftPressed);
		rightButton.setOnAction(rightPressed);
		uTurnButton.setOnAction(uTurnPressed);
		doNothingButton.setOnAction(doNothingPressed);
		removeButton.setOnAction(removePressed);

		forwardButton.setText("Forward");
		backwardButton.setText("Backward");
		leftButton.setText("Left");
		rightButton.setText("Right");
		uTurnButton.setText("U-Turn");
		doNothingButton.setText("Do Nothing");
		removeButton.setText("Remove Last Move");


		hButtons.setSpacing(5);
		hButtons.setMaxWidth(Double.MAX_VALUE);
		hButtons.setPadding(new Insets(15));
		hButtons.setAlignment(Pos.BASELINE_CENTER);
		hButtons.getChildren().addAll(forwardButton, backwardButton, leftButton, rightButton, uTurnButton,
				doNothingButton, removeButton);

		hBoard.setPadding(new Insets(15));
		hBoard.setAlignment(Pos.BASELINE_CENTER);

		robotStats.setText(g.getRobotDetailstoString());
		robotStats.setEditable(false);
		robotStats.setPrefColumnCount(15);
		robotStats.setPrefRowCount(5);

		vMain.setMaxWidth(Double.MAX_VALUE);

		boardView = new Stage();
		Scene scene = new Scene(vMain, 700, 250);
		boardView.setTitle("Robo Rally");
		boardView.setScene(scene);

		setBoard();

	}

	public static void setBoard() {
		hBoard.getChildren().clear();
		vMain.getChildren().clear();
		gridPane.getChildren().clear();

		gridPane.add(getGrid(), 1, 0, 1, 1);

		hBoard.getChildren().addAll(gridPane, robotStats);
		vMain.getChildren().addAll(hBoard, hButtons);

		boardView.show();
	}

	private static GridPane getGrid() {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(15));
		grid.setHgap(1);
		grid.setVgap(1);
		grid.setGridLinesVisible(true);
		grid.setMinSize(0, 0);

		String board[][] = Board.arrayToStringarray();

		String longestString = "";

		for (int i = 0; i < board.length; i++) {
			for (int c = 0; c < board[1].length; c++) {
				if (board[i][c].length() > longestString.length()) {
					longestString = board[i][c];
				}
			}
		}

		for (

				int i = 0; i < board[1].length; i++) {
			for (int c = 0; c < board.length; c++) {
				Label label = new Label();
				Insets inset = new Insets(5, 5, 5, 5);
				label.setText(board[c][i]);
				label.setPadding(inset);
				label.setFont(Font.font("Comic sans", 20));
				label.setMinWidth(50);
				label.setPrefWidth(15 * longestString.length());
				label.setAlignment(Pos.BASELINE_CENTER);
				// label.setMinWidth(longestString.length());
				GridPane.setFillWidth(label, true);
				GridPane.setHalignment(label, HPos.CENTER);
				GridPane.setConstraints(label, i, c); // column=2 row=0
				grid.getChildren().add(label);
			}
		}

		return grid;
	}

	/************************************************************************************/

	EventHandler<ActionEvent> forwardPressed = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			g.setPlayerMoves("F");
			robotStats.setText(g.getRobotDetailstoString());
		}
	};

	/************************************************************************************/

	EventHandler<ActionEvent> backwardPressed = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			g.setPlayerMoves("B");
			robotStats.setText(g.getRobotDetailstoString());
		}
	};

	/************************************************************************************/

	EventHandler<ActionEvent> leftPressed = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			g.setPlayerMoves("L");
			robotStats.setText(g.getRobotDetailstoString());
		}
	};

	/************************************************************************************/

	EventHandler<ActionEvent> rightPressed = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			g.setPlayerMoves("R");
			robotStats.setText(g.getRobotDetailstoString());
		}
	};

	/************************************************************************************/

	EventHandler<ActionEvent> uTurnPressed = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			g.setPlayerMoves("U");
			robotStats.setText(g.getRobotDetailstoString());
		}
	};

	/************************************************************************************/

	EventHandler<ActionEvent> doNothingPressed = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			g.setPlayerMoves("W");
			robotStats.setText(g.getRobotDetailstoString());
		}
	};

	/************************************************************************************/

	EventHandler<ActionEvent> removePressed = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			g.removeLastMove();
			robotStats.setText(g.getRobotDetailstoString());
		}
	};
}
