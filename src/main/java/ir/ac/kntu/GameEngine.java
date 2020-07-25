package ir.ac.kntu;
import java.io.File;
import java.util.*;
import ir.ac.kntu.animations.PlayerAnimations;
import ir.ac.kntu.go.Player;
import ir.ac.kntu.go.PlayerEnemy;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ir.ac.kntu.go.GameObject;
import ir.ac.kntu.go.GameObjectManager;
import ir.ac.kntu.keyboard.KeyListener;
import ir.ac.kntu.keyboard.KeyLogger;
import ir.ac.kntu.map.MapGenerator;
import ir.ac.kntu.map.MapParser;

public abstract class GameEngine<GOFactory, Entity> {

	private int width;
	private int height;
	private static Vector<GameObject> entities = new Vector<GameObject>();
	private static PlayerEnemy playerEnemy;
	private static Player player;

	private TimerWithStatus	animationTimer;
	// private Timeline gameLoop;
	private Scene gameSurface;
	private Group sceneNodes;
	private static Canvas canvas;
	private static GraphicsContext gc;
	private GameObjectManager	manager	= new GameObjectManager();
	private GOFactory factory;
	private MapParser<Entity> mapParser;
	private MapGenerator<Entity>	generator;
	private KeyLogger	keyLogger;

	public GameEngine(GOFactory factory, MapParser<Entity> mapParser, MapGenerator<Entity> generator,
					  KeyLogger keyLogger) {
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

	public void initialize(Stage primaryStage, String title, int width, int height, File map) throws Exception {
		this.height = height;
		this.width = width;
		primaryStage.setTitle(title);
		setSceneNodes(new Group());//root
		setGameSurface(new Scene(getSceneNodes(), width,height));//scene
		keyLogger.init(getGameSurface());

		canvas = new Canvas(width, height);
		getSceneNodes().getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.BLUE);
		gc.setLineWidth(2);
		gc.setFill(Color.BLUE);
		gc.strokeRect(0,0,width,height);
		//GameLoop.start(gc);

		List<List<Entity>> parse = getMapParser().parse(map);
		List<GameObject> generate = getGenerator().generate(parse);
		addObjects(generate);

		PlayerAnimations playerAnimations = new PlayerAnimations();
		playerAnimations.start(getGameSurface(),getSceneNodes());
		//should be called at last it based on player
		//EventHandler.attachEventHandlers(gameSurface);

		primaryStage.setScene(getGameSurface());
	}

	public void removeObjects(GameObject... gameObjects) {
		getManager().addGosToBeRemoved(gameObjects);
		for (GameObject gameObject : gameObjects) {
			getSceneNodes().getChildren().remove(gameObject.getNode());
			if (gameObject instanceof KeyListener) {
				keyLogger.removeListener((KeyListener) gameObject);
			}
		}
	}

	public void setUpScreen(){
		gameSurface.setFill(Color.rgb(5,115,60));
	}

	public void addObjects(List<GameObject> generate) {
		setUpScreen();
		Collections.sort(generate,layerComparator);
		getManager().addGo(generate.toArray(new GameObject[0]));
		for (GameObject gameObject : generate) {
			//gc.drawImage(gameObject.getImage(),gameObject.getX(), gameObject.getY(), width * gameObject.getX(),
			// height);
			getSceneNodes().getChildren().add(gameObject.getNode());
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


	private static Comparator<GameObject> layerComparator=new Comparator<GameObject>() {
		@Override
		public int compare(GameObject o1, GameObject o2) {
			int result = Integer.compare(o1.getLayer(),o2.getLayer());
			return result;
		}
	};

	public static boolean addEntityToGame(GameObject e){
		if(!entities.contains(e)){
			entities.add(e);
			Collections.sort(entities,layerComparator);
			return true;
		} else {
			return false;
		}
	}
	public static Vector<GameObject> getEntities(){
		return entities;
	}
	public static PlayerEnemy getPlayerEnemy(){
		return playerEnemy;
	}
	public static void setPlayer(Player p){
		player = p;
		addEntityToGame(p);
	}
	public static Player getPlayer(){
		return player;
	}

	public static GraphicsContext getGc() {
		return gc;
	}
}