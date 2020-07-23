package ir.ac.kntu.go;

import java.util.ArrayList;
import java.util.List;

import ir.ac.kntu.GameEngine;
import ir.ac.kntu.factory.GameObjectFactory;
import javafx.animation.Animation;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

public abstract class GameObject {

	private List<Animation>	animations	= new ArrayList<>();
	private ImageView node;
	private double vX= 0;
	private double vY= 0;
	private boolean isDead= false;

	public ImageView getNode() {
		return node;
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

	public abstract <T extends GameObjectFactory> void collide(GameEngine<T, ?> atomSmasher, GameObject go1);


	/**
	 * 	Player is rendered at layer 0
	 * 	Anything to be rendered below the player will get layer < 0 i.e Negative
	 * 	Anything to be rendered above the player will get layer > 0 i.e Positive
	 * */

	public abstract int getLayer();

}
