package ir.ac.kntu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		/*Bomberman atom = new Bomberman();
		atom.initialize(primaryStage, "Bomberman", 900, 600, new File(
				"C:/Users/Mohammad/Desktop/bomberman1/map/map1.m"));
		atom.beginGameLoop();*/
        Menu menu = new Menu(primaryStage);
		Scene scene = new Scene(menu.createContent());
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.show();
	}

}
