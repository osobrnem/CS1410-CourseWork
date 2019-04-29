package main;



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
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		launch(args);
	}
}
