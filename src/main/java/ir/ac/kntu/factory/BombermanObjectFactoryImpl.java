package ir.ac.kntu.factory;

import ir.ac.kntu.go.*;
import ir.ac.kntu.keyboard.KeyLogger;

public class BombermanObjectFactoryImpl implements BombermanObjectFactory {

	private static final int SIZE= 30;

	private KeyLogger keyLogger;

	public BombermanObjectFactoryImpl(KeyLogger keyLogger) {
		super();
		this.keyLogger = keyLogger;
	}

	@Override
	public Wall getWall(int x, int y) {
		return new Wall(x * SIZE, y * SIZE, SIZE, SIZE);
	}

	@Override
	public RandomMovableObject getMovableObject(int x, int y) {
		return new RandomMovableObject(x * SIZE, y * SIZE, SIZE, SIZE);
		//x * SIZE + 1, y * SIZE + 1, SIZE - 2, SIZE - 2
	}

	@Override
	public PlayerEnemy getPlayerEnemy(int x, int y) {
		PlayerEnemy playerEnemy = new PlayerEnemy(x * SIZE, y * SIZE, SIZE, SIZE);
		//x * SIZE + 5, y * SIZE + 5, SIZE - 10, SIZE - 10
		keyLogger.registerListener(playerEnemy);
		return playerEnemy;
	}

	@Override
	public Player getPlayer(int x, int y) {
		Player player = new Player(x * SIZE, y * SIZE, SIZE, SIZE);
		return player;
	}

	@Override
	public Bomb getBomb(int x, int y) {
		return new Bomb(x * SIZE, y * SIZE, SIZE, SIZE);
		//x * SIZE + SIZE / 2, y * SIZE + SIZE / 2,SIZE, SIZE
	}

	@Override
	public Grass getGrass(int x, int y) {
		return new Grass(x * SIZE, y * SIZE, SIZE, SIZE);
	}

	@Override
	public Brick getBrick(int x, int y) {
		return new Brick(x * SIZE, y * SIZE, SIZE, SIZE);
	}
}
