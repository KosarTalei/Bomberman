package ir.ac.kntu.go;

import ir.ac.kntu.GameEngine;
import ir.ac.kntu.factory.GameObjectFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class MovableObject extends GameObject {

	protected Position	last;
	protected Position	position;
	protected Direction	direction	= Direction.N;
	protected int speed= 1;
	private double width;
	private double height;

	public MovableObject(double x, double y, double width, double height) {
		Image image = null;
		try {
			image = new Image(new FileInputStream(
					"C:\\Users\\Mohammad\\Desktop\\bomberman1\\resources\\assets\\player\\player_down_moving.png"));
		} catch (FileNotFoundException e) {
			System.out.println("cannot load player img!");
			e.printStackTrace();
		}
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(height);
		imageView.setFitWidth(width);
		imageView.setX(x);
		imageView.setY(y);
		node = imageView;
		position = new Position((int) x, (int) y);
		this.height = height;this.width=width;

	}

	@Override
	public void update(Scene scene, long time) {
		if (isDead) {
			return;
		}
		this.last = this.position;
		this.position = move(position, direction);
	}

	@Override
	public <T extends GameObjectFactory> void collide(GameEngine<T, ?> atomSmasher, GameObject go1) {
		if (go1 instanceof Wall) {
			collide(atomSmasher, (Wall) go1);
		} else if (go1 instanceof RandomMovableObject) {
			collide(atomSmasher, (RandomMovableObject) go1);
		} else if (go1 instanceof Bomb) {
			collide(atomSmasher, (Bomb) go1);
		}
	}

	protected <T extends GameObjectFactory> void collide(GameEngine<T, ?> atomSmasher, Wall go1) {
		if (last != null) {
			this.position = last;
		}
		last = null;
		moveToPosition(position);
	}

	protected <T extends GameObjectFactory> void collide(GameEngine<T, ?> atomSmasher, Bomb go1) {
		if (last != null) {
			this.position = last;
		}
		last = null;
		moveToPosition(position);
	}

	protected <T extends GameObjectFactory> void collide(GameEngine<T, ?> atomSmasher, RandomMovableObject go1) {
	}

	protected Position move(Position position, Direction direction) {
		Position newPosition;
		switch (direction) {
		case N:
			newPosition = position.north(speed);
			break;
		case E:
			newPosition = position.east(speed);
			break;
		case S:
			newPosition = position.south(speed);
			break;
		case W:
		default:
			newPosition = position.west(speed);
			break;
		}

		moveToPosition(newPosition);

		return newPosition;
	}

	protected void moveToPosition(Position newPosition) {
		this.node.setTranslateX(newPosition.getX());
		this.node.setTranslateY(newPosition.getY());
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}
}
