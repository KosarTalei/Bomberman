package ir.ac.kntu.go;

import ir.ac.kntu.GameEngine;
import ir.ac.kntu.factory.GameObjectFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class DirectionalExplosion extends GameObject{
    private GameObjectManager gameObjectManager;
    protected int _direction;
    private int _radius;
    protected int xOrigin, yOrigin;
    private ArrayList<Bomb> bombs = new ArrayList();

    public DirectionalExplosion(int x, int y, int direction, int radius) {
        xOrigin = x;
        yOrigin = y;
        direction = direction;
    }
    public void addBomb(Bomb bomb){
        bombs.add(bomb);
    }

    private void createExplosions() {
        for (Bomb bomb : bombs) {
            if (bomb.getBombTime() <= 0) {
                gameObjectManager.getActors().remove(bomb);
                explosion(bomb);
            }
        }
    }
    private void explosion(Bomb bomb){

        ImageView imageView = new ImageView(getImage());
        imageView.setFitHeight(bomb.getHeight());
        imageView.setFitWidth(bomb.getWidth());
        imageView.setX(bomb.getX());
        imageView.setY(bomb.getY());
        node = imageView;

    }
    private Image getImage() {
        Image image = null;
        try {
            image = new Image(new FileInputStream(
                    "C:\\Users\\Mohammad\\Desktop\\bomberman1\\resources\\assets\\map\\explosion\\up-explosion-3.png"));
        } catch (FileNotFoundException e) {
            System.out.println("cannot load grass img!");
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public void update(Scene scene, long time) {

    }

    @Override
    public <T extends GameObjectFactory> void collide(GameEngine<T, ?> atomSmasher, GameObject go1) {

    }
}
