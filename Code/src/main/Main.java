package main;



import coursework.GameView;

/**
 * @author Matthew Osborne
 *
 */

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application  {


	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 *
	 * Launch a new GameView
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		new GameView();

	}


	/************************************************************************************/

	/**
	 * @param args Input
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
