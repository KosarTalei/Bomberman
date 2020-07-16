package ir.ac.kntu.menu;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Help implements EventHandler<ActionEvent> {
    private Scene scene;
	public Help(Scene scene)  {
        this.scene = scene;
	}

	public void help(){
		Menu m = new Menu("Help");
		MenuItem m1 = new MenuItem("How to play");
		m.getItems().add(m1);
		// create a menubar
		MenuBar mb = new MenuBar();

		// add menu to menubar
		mb.getMenus().add(m);
		// create a VBox
		VBox vb = new VBox(mb);

		// create a scene
		scene = new Scene(vb, 500, 300);
	}

	@Override
	public void handle(ActionEvent actionEvent) {
		if(actionEvent.toString().equals("How to play")){

		}
	}

}
