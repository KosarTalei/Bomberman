package ir.ac.kntu.go;

import ir.ac.kntu.GameEngine;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GameObject {

	private ImageView node;
	private double x= 0;
	private double y= 0;
	private boolean isDead= false;

	public GameObject(double x ,double y){
		this.x=x;
		this.y=y;
	}

	public ImageView getNode() {
		return node;
	}
	public Image getImage(){
		return node.getImage();
	}

	public void setNode(ImageView node) {
		this.node = node;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean dead) {
		isDead = dead;
	}

	public abstract void update(Scene scene, long time);

	public boolean isColliding(GameObject gameObject) {
		if (!node.isVisible() || !gameObject.node.isVisible() || this == gameObject) {
			return false;
		}

		return node.getBoundsInParent().intersects(gameObject.node.getBoundsInParent());
	}

	public abstract <T> void collide(GameEngine<T, ?> atomSmasher, GameObject go1);


	/**
	 * 	Player is at layer 0
	 * 	Anything to be below the player will get layer < 0
	 * 	Anything to be above the player will get layer > 0
	 * */

	public abstract int getLayer();

	public double getY() {
		return y;
	}

	public double getX() {
		return x;
	}
}
