package ir.ac.kntu.Menu;

import ir.ac.kntu.Bomberman;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class MenuItem extends StackPane {

    public MenuItem(String name, Stage stage) {
        LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true,
                CycleMethod.NO_CYCLE, new Stop(0, Color.DARKBLUE),
                new Stop(0.1, Color.BLACK),
                new Stop(0.9, Color.BLACK),
                new Stop(1, Color.DARKBLUE));
        Rectangle bg = new Rectangle(200,40);
        bg.setOpacity(0.4);
        Text text = new Text(name);
        text.setFill(Color.DARKGREY);
        text.setFont(Font.font("Times New Man", FontWeight.SEMI_BOLD,20));
        setAlignment(Pos.CENTER);
        getChildren().addAll(bg, text);
        setOnMouseEntered(event -> {
            bg.setFill(gradient);
            text.setFill(Color.WHITE);

        });
        setOnMouseExited(event -> {
            bg.setFill(Color.BLACK);
            text.setFill(Color.DARKGREY);
        });
        setOnMousePressed(event -> {
            bg.setFill(Color.DARKVIOLET);

            Bomberman atom = new Bomberman();
            try {
                atom.initialize(stage, "Bomberman", 900, 600, new File(
                        "C:\\Users\\Mohammad\\Desktop\\hw5-fariborz-bomberman\\map\\map1.m"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            atom.beginGameLoop();
            stage.show();
        });
        setOnMouseReleased(event -> bg.setFill(gradient));
    }
}
