package ir.ac.kntu.go;

import java.util.ArrayList;
import java.util.List;

import ir.ac.kntu.GameEngine;
import ir.ac.kntu.factory.GameObjectFactory;
import javafx.animation.Animation;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

public abstract class GameObject {

	protected List<Animation>	animations	= new ArrayList<>();
	public ImageView node;
	public double vX= 0;
	public double vY= 0;
	protected boolean isDead= false;

	public abstract void update(Scene scene, long time);

	public boolean isColliding(GameObject gameObject) {
		if (!node.isVisible() || !gameObject.node.isVisible() || this == gameObject) {
			return false;
		}

		return node.getBoundsInParent().intersects(gameObject.node.getBoundsInParent());
	}

	public abstract <T extends GameObjectFactory> void collide(GameEngine<T, ?> atomSmasher, GameObject go1);

}
