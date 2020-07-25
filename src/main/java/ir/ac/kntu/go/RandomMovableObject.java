package ir.ac.kntu.go;

import java.util.Random;

import ir.ac.kntu.GameEngine;
import javafx.scene.Scene;

public class RandomMovableObject extends MovableObject {

	private Random	random	= new Random();
	private int	base	= 31;
	private int	modulo	= 60;

	public RandomMovableObject(double x, double y, double width, double height) {
		super(x, y, width, height);
		changeDirection();
	}

	@Override
	public void update(Scene scene, long time) {
		if (extracted(getPosition().getY()) && extracted(getPosition().getX())) {
			changeDirection();
		}
		super.update(scene, time);
	}

	@Override
	public int getLayer() {
		return 0;
	}

	private void changeDirection() {
		Direction tmp;
		Direction direction = (tmp = randomDirection()) == getDirection() ? randomDirection() : tmp;
		setDirection(direction);
	}

	private Direction randomDirection() {
		return Direction.values()[random.nextInt(Direction.values().length)];
	}

	private boolean extracted(int position) {
		return (position - base) % modulo == 0;
	}

	@Override
	protected <T> void collide(GameEngine<T, ?> atomSmasher, Wall go1) {
		super.collide(atomSmasher, go1);
		changeDirection();

	}

	@Override
	protected <T> void collide(GameEngine<T, ?> atomSmasher, Bomb go1) {
		super.collide(atomSmasher, go1);
		changeDirection();
	}

}
