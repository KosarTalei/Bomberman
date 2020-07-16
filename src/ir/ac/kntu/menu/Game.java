package ir.ac.kntu.menu;


import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class Game {

	private Scene scene;

	public Game(Scene scene) {
        this.scene = scene;
	}
	public void menu() {

		// create a menu
		Menu m = new Menu("Game");

		// create menu items
		MenuItem m1 = new MenuItem("New Game");
		MenuItem m2 = new MenuItem("Scores");

		// add menu items to menu
		m.getItems().add(m1);
		m.getItems().add(m2);

		// create a menubar
		MenuBar mb = new MenuBar();

		// add menu to menubar
		mb.getMenus().add(m);

		// create a VBox
		VBox vb = new VBox(mb);

		// create a scene
		scene = new Scene(vb, 500, 300);

	}

	public void option(){
		Menu m = new Menu("Options");
		MenuItem m1 = new MenuItem("Pause");
		MenuItem m2 = new MenuItem("Resume");
		m.getItems().add(m1);
		m.getItems().add(m2);
		// create a menubar
		MenuBar mb = new MenuBar();

		// add menu to menubar
		mb.getMenus().add(m);
		// create a VBox
		VBox vb = new VBox(mb);

		// create a scene
		scene = new Scene(vb, 500, 300);
	}

}
