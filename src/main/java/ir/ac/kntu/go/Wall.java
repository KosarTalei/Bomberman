package ir.ac.kntu.go;

import ir.ac.kntu.GameEngine;
import ir.ac.kntu.factory.GameObjectFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Wall extends GameObject {
	private int layer;

	public Wall(double x, double y, double width, double height) {
		Image image = getImage();
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(height);
		imageView.setFitWidth(width);
		imageView.setX(x);
		imageView.setY(y);
		setNode(imageView);

		layer = 1;
	}

	private Image getImage() {
		Image image = null;
		try {
			image = new Image(new FileInputStream(
					"C:\\Users\\Mohammad\\Desktop\\bomberman1\\resources\\assets\\map\\wall.png"));
		} catch (FileNotFoundException e) {
			System.out.println("cannot load wall img!");
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

	@Override
	public int getLayer() {
		return layer;
	}
}
