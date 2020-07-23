package ir.ac.kntu.go;

import ir.ac.kntu.GameEngine;
import ir.ac.kntu.factory.GameObjectFactory;
import javafx.animation.ScaleTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Bomb extends GameObject {

	private static final long	BOMB_TIME	= 5000000000L;
	private long	startTime	= 0;
	private boolean	firstUpdate	= false;
	private ScaleTransition		ticking;

	private double x;
	private double y;
	private double width;
	private double height;

	public Bomb(int x, int y, double width, double height) {
	    this.x= x;
	    this.y = y;
	    this.height=height;
	    this.width=width;
		Image image = getImage();
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(height);
		imageView.setFitWidth(width);
		imageView.setX(x);
		imageView.setY(y);
		node = imageView;

		ticking = new ScaleTransition();
		ticking.setFromX(1);
		ticking.setFromY(1);
		ticking.setDuration(Duration.millis(500));
		ticking.setToX(0.8);
		ticking.setToY(0.8);
		ticking.setAutoReverse(true);
		ticking.setCycleCount(1000);
		ticking.setNode(node);
	}

	private Image getImage() {
		Image image = null;
		try {
			image = new Image(new FileInputStream(
					"C:\\Users\\Mohammad\\Desktop\\bomberman1\\resources\\assets\\map\\bomb.png"));
		} catch (FileNotFoundException e) {
			System.out.println("cannot load bomb img!");
			e.printStackTrace();
		}
		return image;
	}

	@Override
	public void update(Scene scene, long time) {
		if (!firstUpdate) {
			firstUpdate = true;
			startTime = time;
			ticking.play();
		}

		if (time - BOMB_TIME > startTime) {
			ticking.stop();
		}

	}

	@Override
	public <T extends GameObjectFactory> void collide(GameEngine<T, ?> atomSmasher, GameObject go1) {

	}

	public static long getBombTime() {
		return BOMB_TIME;
	}

	public double getWidth() {
		return width;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getHeight() {
		return height;
	}
}
