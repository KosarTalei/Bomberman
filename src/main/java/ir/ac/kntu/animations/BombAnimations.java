
package ir.ac.kntu.animations;
import ir.ac.kntu.go.GameObject;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class BombAnimations {

    private Image blackBomb;
    private double playSpeed;

    public Image getBlackBomb() {
        return blackBomb;
    }

    public void setBlackBomb(Image blackBomb) {
        this.blackBomb = blackBomb;
    }

    public BombAnimations(GameObject e) {
        blackBomb = new Image("C:\\Users\\Mohammad\\Desktop\\hw5-fariborz-bomberman\\resources\\assets\\map" +
                "\\explosion\\up-explosion-3.png");
        playSpeed=0.3;
        
        List<Rectangle> specs=new ArrayList<>();
        specs.add(new Rectangle(180, 93,18,16));
        specs.add(new Rectangle(211, 93,16,16));
        specs.add(new Rectangle(240, 93,18,17));
    }
}
