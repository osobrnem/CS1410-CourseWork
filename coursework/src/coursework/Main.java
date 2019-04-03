package coursework;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application  {


	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */

	@Override
	public void start(Stage primaryStage) throws Exception {
		try{
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("GameGUI.fxml"));
			loader.setController(new BoardController());
			final Parent root = loader.load();

			final Scene scene = new Scene(root, 400, 200);
			primaryStage.setTitle("Board");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	/************************************************************************************/

	/**
	 * @param args
	 */
	public static void main(String[] args){
		launch(args);
	}
}
