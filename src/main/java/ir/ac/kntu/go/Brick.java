package ir.ac.kntu.go;

import ir.ac.kntu.GameEngine;
import ir.ac.kntu.factory.GameObjectFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Brick extends GameObject {
    private int layer;

    private boolean destroyed;
    private double x;
    private double y;
    private double width;
    private double height;
    private Image brick = new Image( "C:\\Users\\Mohammad\\Desktop\\bomberman1\\resources\\assets\\map\\wall.png");
    private Image grass = new Image("C:\\Users\\Mohammad\\Desktop\\bomberman1\\resources\\assets\\map\\normal.png");

    public Brick(double x, double y, double width, double height) {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        ImageView imageView = new ImageView(getImage(brick));
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        imageView.setX(x);
        imageView.setY(y);
        setNode(imageView);
        layer = 1;
    }

    @Override
    public void update(Scene scene, long time) {
        if(destroyed){// change tile to grass
            ImageView imageView = new ImageView(getImage(grass));
            imageView.setFitHeight(height);
            imageView.setFitWidth(width);
            imageView.setX(x);
            imageView.setY(y);
            setNode(imageView);
        }
    }

    @Override
    public <T extends GameObjectFactory> void collide(GameEngine<T, ?> atomSmasher, GameObject go1) {
    }

    private Image getImage(Image url) {
        Image image = null;
        try {
            image = new Image(new FileInputStream(String.valueOf(url)));
        } catch (FileNotFoundException e) {
            System.out.println("cannot load brick img!");
            e.printStackTrace();
        }
        return image;
    }

    public void destroy() {
        destroyed = true;
    }
	
    @Override
    public int getLayer() {
		return layer;
	}
}