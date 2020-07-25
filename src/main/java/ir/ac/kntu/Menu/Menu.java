package ir.ac.kntu.Menu;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Menu {

    private Stage stage;
    public Menu(Stage stage){
        this.stage = stage;
    }
    public  Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(900, 500);
        try(InputStream is = Files.newInputStream(Paths.get(
                "C:\\Users\\Mohammad\\Desktop\\bomberman1\\resources\\Background.jpg"))){
            ImageView img = new ImageView(new Image(is));
            img.setFitWidth(900);
            img.setFitHeight(500);
            root.getChildren().add(img);
        } catch(IOException e) {
            System.out.println("Couldn't load image");
        }
        Title title = new Title ("B O M B E R M A N");
        title.setTranslateX(50);
        title.setTranslateY(200);
        MenuItem m1 = new MenuItem("START THE GAME",stage);
        //MenuItem m2 = new MenuItem("2 PLAYER GAME");
        //MenuItem m3 = new MenuItem("3 PLAYER GAME");
        //MenuItem m4 = new MenuItem("4 PLAYER GAME");

        MenuBox vbox = new MenuBox(m1);//,m2,m3,m4
        vbox.setTranslateX(100);
        vbox.setTranslateY(300);
        root.getChildren().addAll(title,vbox);
        return root;
    }

    private static class Title extends StackPane {
        public Title(String name) {
            Rectangle bg = new Rectangle(410, 75);
            bg.setStroke(Color.WHITE);
            bg.setStrokeWidth(2);
            bg.setFill(null);
            Text text = new Text(name);
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Times New Man", FontWeight.SEMI_BOLD, 50));
            setAlignment(Pos.CENTER);
            getChildren().addAll(bg,text);
        }
    }
}