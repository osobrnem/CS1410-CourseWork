package main;

	import javafx.application.Application;
	import javafx.geometry.*;
	import javafx.scene.Scene;
	import javafx.scene.control.*;
	import javafx.scene.layout.*;
	import javafx.scene.text.*;
	import javafx.stage.Stage;

	public class arrayTest extends Application {

	   @Override
	   public void start(Stage stage) throws Exception {

	      GridPane root = new GridPane();
	      String bgStyle = "-fx-background-color: lightblue;"
	         + "-fx-background-radius: 0%;"
	         + "-fx-background-inset: 5px;";

	         root.add(createGridPane(), 1, 0, 1, 1);
	         Scene scene = new Scene(root, 550, 300);
	         stage.setTitle("Layout Demo");
	         stage.setScene(scene);
	         stage.show();
	   }

	   public GridPane createGridPane() {
	      GridPane grid = new GridPane();
	      grid.setPadding(new Insets(10));
	      grid.setHgap(10);
	      grid.setVgap(10);
	      grid.setGridLinesVisible(true);

	      Label label = new Label();
	      label.setText("test");
	      GridPane.setConstraints(label, 2, 3); // column=2 row=0

	      grid.getChildren().addAll( label);

	      return grid;
	   }
	}
