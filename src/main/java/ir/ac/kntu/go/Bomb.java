package ir.ac.kntu.go;

import ir.ac.kntu.GameEngine;
import ir.ac.kntu.boundedbox.RectBoundedBox;
import javafx.animation.ScaleTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

public class Bomb extends GameObject {

    private int layer;
	private static final long	BOMB_TIME	= 5000000000L;
	private int timerDurationInMillis = 2000;
	private long startTime= 0;
	private boolean	firstUpdate	= false;
	private ScaleTransition	ticking;

	private double x;
	private double y;
	private double width;
	private double height;

	private RectBoundedBox entityBoundary;
	private Date addedDate;
	private STATE bombState;

	public Bomb(int x, int y, double width, double height) {
		super(x,y);
	    this.x= x;
	    this.y = y;
	    this.height=height;
	    this.width=width;
		ImageView imageView = getImageView(x, y, width, height);
		setNode(imageView);

		layer=-2;

		setTicking();
		ticking.setNode(getNode());

		entityBoundary = new RectBoundedBox(x, y, width, height);
		addedDate=new Date();
		bombState=STATE.ACTIVE;
	}

	private void setTicking() {
		ticking = new ScaleTransition();
		ticking.setFromX(1);
		ticking.setFromY(1);
		ticking.setDuration(Duration.millis(500));
		ticking.setToX(0.8);
		ticking.setToY(0.8);
		ticking.setAutoReverse(true);
		ticking.setCycleCount(1000);
	}

	private ImageView getImageView(int x, int y, double width, double height) {
		Image image = getImage();
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(height);
		imageView.setFitWidth(width);
		imageView.setX(x);
		imageView.setY(y);
		return imageView;
	}

	enum STATE {
		INACTIVE,   //INACTIVE when bomb's timer hasnt yet started
		ACTIVE,     //Active when bomb's timer has started and it will explode soon
		EXPLODING,  //when bomb is exploding
		DEAD;   //when the bomb has already exploded
	}

	public boolean isAlive(){
		STATE s = checkBombState();
		if(s==STATE.DEAD){
			return false;
		} else{
			if(s==STATE.ACTIVE||s==STATE.INACTIVE){
				return true;
			}
			return true;
		}
	}

	public STATE checkBombState(){
		if(new Date().getTime()>timerDurationInMillis+addedDate.getTime()){
			return STATE.DEAD;
		}else{
			return STATE.ACTIVE;
		}
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
	public <T> void collide(GameEngine<T, ?> atomSmasher, GameObject go1) {

	}

	@Override
	public int getLayer() {
		return layer;
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
	public Image getImage() {
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
}