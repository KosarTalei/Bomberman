package ir.ac.kntu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import ir.ac.kntu.factory.GameObjectFactory;
import ir.ac.kntu.go.GameObject;
import ir.ac.kntu.go.GameObjectManager;
import ir.ac.kntu.keyboard.KeyListener;
import ir.ac.kntu.keyboard.KeyLogger;
import ir.ac.kntu.map.MapGenerator;
import ir.ac.kntu.map.MapParser;

public abstract class GameEngine<GOFactory extends GameObjectFactory, Entity> {
	private int width;
	private int height;

	private TimerWithStatus			animationTimer;
	// private Timeline gameLoop;
	private Scene gameSurface;
	private Group sceneNodes;
	private GameObjectManager	manager	= new GameObjectManager();
	private GOFactory	factory;
	private MapParser<Entity> mapParser;
	private MapGenerator<Entity>	generator;
	private KeyLogger	keyLogger;

	public GameEngine(GOFactory factory, MapParser<Entity> mapParser, MapGenerator<Entity> generator, KeyLogger keyLogger) {
		super();
		this.factory = factory;
		this.mapParser = mapParser;
		this.generator = generator;
		this.keyLogger = keyLogger;
		buildAndSetGameLoop();
	}

	protected final void buildAndSetGameLoop() {
		animationTimer = new TimerWithStatus() {

			@Override
			public void handle(long time) {
				updateSprites(time);

				checkCollisions();

				cleanupSprites();

			}
		};

	}

	public void initialize(Stage primaryStage, String title, int width, int height, File map) {
		this.height = height;
		this.width = width;
		primaryStage.setTitle(title);
		setSceneNodes(new Group());
		setGameSurface(new Scene(getSceneNodes(), width, height));
		primaryStage.setScene(getGameSurface());
		keyLogger.init(getGameSurface());
		List<List<Entity>> parse = getMapParser().parse(map);
		List<GameObject> generate = getGenerator().generate(parse);
		addObjects(generate);
	}

	public void removeObjects(GameObject... gameObjects) {
		getManager().addGosToBeRemoved(gameObjects);
		for (GameObject gameObject : gameObjects) {
			getSceneNodes().getChildren().remove(gameObject.node);
			if (gameObject instanceof KeyListener) {
				keyLogger.removeListener((KeyListener) gameObject);
			}
		}
	}
	public void addTile(int x,int y){
		try {
			Image image= new Image(new FileInputStream(
					"C:\\Users\\Mohammad\\Desktop\\bomberman1\\resources\\assets\\map\\normal.png"));
			ImageView imageView = new ImageView(image);
			getSceneNodes().getChildren().add(imageView);
			TilePane tilePane = new TilePane(x,y,imageView,getSceneNodes());
			tilePane.setHgap(x);
			tilePane.setVgap(y);
		} catch (FileNotFoundException e) {
			System.out.println("cannot load grass img!");
			e.printStackTrace();
		}

	}

	public void setUpScreen(){
		System.out.println("***************"+width);

		for (int i=0;i<width%30;i++){
			for (int j=0;j<height%30;j++){
				try {
					System.out.println("***************");
					Image image= new Image(new FileInputStream(
							"C:\\Users\\Mohammad\\Desktop\\bomberman1\\resources\\assets\\map\\normal.png"));
					ImageView imageView = new ImageView(image);
					getSceneNodes().getChildren().add(imageView);
				} catch (FileNotFoundException e) {
					System.out.println("cannot load grass img!");
					e.printStackTrace();
				}
			}
		}
	}

	private void addObjects(List<GameObject> generate) {
		//setUpScreen();
		getManager().addGo(generate.toArray(new GameObject[0]));
		for (GameObject gameObject : generate) {
			getSceneNodes().getChildren().add(gameObject.node);
		}

	}

	public void beginGameLoop() {
		getAnimationTimer().start();
	}

	protected void cleanupSprites() {
		manager.cleanupGo();
	}

	protected void checkCollisions() {
		for (GameObject go1 : manager.getActors()) {
			for (GameObject go2 : manager.getActors()) {
				if (handleCollision(go1, go2)) {
					break;
				}
			}
		}
	}

	protected MapParser<Entity> getMapParser() {
		return mapParser;
	}

	protected MapGenerator<Entity> getGenerator() {
		return generator;
	}

	protected GOFactory getFactory() {
		return factory;
	}

	protected Scene getGameSurface() {
		return gameSurface;
	}

	protected boolean handleCollision(GameObject go1, GameObject go2) {
		if (go1.isColliding(go2)) {
			go1.collide(this, go2);
			go2.collide(this, go1);
			return true;
		}
		return false;
	}

	protected void updateSprites(long time) {
		for (GameObject gameObject : manager.getActors()) {
			gameObject.update(getGameSurface(), time);
		}
	}

	protected void setSceneNodes(Group sceneNodes) {
		this.sceneNodes = sceneNodes;
	}

	public Group getSceneNodes() {
		return sceneNodes;
	}

	protected void setGameSurface(Scene gameSurface) {
		this.gameSurface = gameSurface;
	}

	public GameObjectManager getManager() {
		return manager;
	}

	public TimerWithStatus getAnimationTimer() {
		return animationTimer;
	}

	public void addMessage(TextField textField){
		getSceneNodes().getChildren().add(textField);
	}
}
