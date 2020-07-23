package ir.ac.kntu.go;

import ir.ac.kntu.GameEngine;
import ir.ac.kntu.factory.GameObjectFactory;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

public class Flame extends MovableObject {

	private final Direction	direction;
	private final Position	position;
	private final int		length;
	private final int		size;
	private ImageView imageView;
	private boolean			start	= false;

	public Flame(int x, int y, Direction direction, int length, int size) {
		super(x, y, size, size);
		this.position = new Position(x, y);
		this.direction = direction;
		this.length = length;
		this.size = size;
		imageView.setX(x);
		imageView.setY(y);
		node = imageView;

	}

	public void play() {
		imageView.setFitWidth(size);
		imageView.setFitHeight(size);
		start = true;
	}


	@Override
	public void update(Scene scene, long time) {

	}

	@Override
	public <T extends GameObjectFactory> void collide(GameEngine<T, ?> atomSmasher, GameObject go1) {
	}
}
