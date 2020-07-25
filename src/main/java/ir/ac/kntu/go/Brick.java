package ir.ac.kntu.go;
import ir.ac.kntu.GameEngine;
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
    private String brick = "C:\\Users\\Mohammad\\Desktop\\hw5-fariborz-bomberman\\" +
            "resources\\assets\\map\\block_breaking.png";
    private String grass = "C:\\Users\\Mohammad\\Desktop\\hw5-fariborz-bomberman\\" +
            "resources\\assets\\map\\normal.png";

    public Brick(double x, double y, double width, double height) {
        super(x,y);
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        ImageView imageView = getImageView(x, y, width, height);
        setNode(imageView);
        layer = 1;
    }

    private ImageView getImageView(double x, double y, double width, double height) {
        ImageView imageView = new ImageView(getImage(brick));
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        imageView.setX(x);
        imageView.setY(y);
        return imageView;
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
    public <T> void collide(GameEngine<T, ?> atomSmasher, GameObject go1) {
    }

    private Image getImage(String s) {
        Image image = null;
        try {
            image = new Image(new FileInputStream(s));
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