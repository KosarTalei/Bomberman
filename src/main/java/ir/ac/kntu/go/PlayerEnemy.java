package ir.ac.kntu.go;

import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;
import static javafx.scene.input.KeyCode.UP;

import java.util.Arrays;
import java.util.List;

import ir.ac.kntu.GameEngine;
import ir.ac.kntu.keyboard.KeyListener;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class PlayerEnemy extends MovableObject implements KeyListener {

	private KeyCode	currentlyPressed;
	private List<Bomb> bombs;
	private int timeBetweenPutBombs = 0;
	private GameObjectManager gameObjectManager;
	private GameEngine gameEngine;
	private int layer;
	private boolean alive=true;

	public PlayerEnemy(double x, double y, double width, double height) {
		super(x, y, width, height);
		this.setSpeed(0);
		layer=0;
	}

	@Override
	public List<KeyCode> interestedIn() {
		return Arrays.asList(LEFT, RIGHT, UP, DOWN);
	}

	@Override
	public void notify(KeyEvent keyEvent) {
		EventType<? extends Event> eventType = keyEvent.getEventType();
		if (KeyEvent.KEY_RELEASED.equals(eventType)) {
			if (keyEvent.getCode().equals(currentlyPressed)) {
				this.setSpeed(0);
			}
		} else if (KeyEvent.KEY_PRESSED.equals(eventType)) {
			currentlyPressed = keyEvent.getCode();
			setDirection(getDirection(keyEvent));
			this.setSpeed(1);
		}

	}

	private Direction getDirection(KeyEvent keyEvent) {
		switch (keyEvent.getCode()) {
		    case LEFT:
			    return Direction.E;
		    case UP:
			    return Direction.N;
		    case DOWN:
			    return Direction.S;
		    case RIGHT:
		    default:
			    return Direction.W;
		}
	}

	@Override
	protected <T> void collide(GameEngine<T, ?> atomSmasher, RandomMovableObject go1) {
		if (!isDead()) {
			this.setSpeed(0);
			setDead(true);
			FadeTransition fadeTransition = new FadeTransition();
			fadeTransition.setNode(getNode());
			fadeTransition.setDuration(Duration.millis(3000));
			fadeTransition.setFromValue(getNode().getOpacity());
			fadeTransition.setToValue(0);
			fadeTransition.setOnFinished((new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					atomSmasher.removeObjects(PlayerEnemy.this);
				}
			}));
			fadeTransition.play();

		}
	}

	protected void placeBomb(int x, int y) {
		Bomb b = new Bomb(x, y,getWidth(),getHeight());
		gameObjectManager.addActor(b);
	}

	public void kill() {
		if(alive == false){
			return;
		}
		alive = false;
		TextField msg = new TextField("PLAYER WAS DEAD!");
		gameEngine.addMessage(msg);
	}

	@Override
	public int getLayer() {
		return layer;
	}
	public boolean isPlayerCollisionFriendly() {
		return true;
	}

}