package ir.ac.kntu.map;

import ir.ac.kntu.GameEngine;
import ir.ac.kntu.factory.BombermanObjectFactory;
import ir.ac.kntu.go.GameObject;
import ir.ac.kntu.go.Wall;
import java.util.Vector;

public class BombermanMapGeneratorImpl extends MapGeneratorImpl<MapEntity> {

	private BombermanObjectFactory objectFactory;
	private GameEngine gameEngine;
	private Vector<Wall> walls = new Vector<Wall>();

	public BombermanMapGeneratorImpl(BombermanObjectFactory objectFactory) {
		super();
		this.objectFactory = objectFactory;
	}

	@Override
	protected GameObject generateEntity(int x, int y, MapEntity entity) {
		switch (entity) {
			case W:
				Wall wall = objectFactory.getWall(x, y);
				walls.add(wall);
				return wall;
			case E:
				return objectFactory.getMovableObject(x, y);
			case P:
				return objectFactory.getPlayer(x, y);
			case B:
				return objectFactory.getBomb(x, y);
			case G:
				return objectFactory.getGrass(x, y);
			default:
				return null;
		}

	}
	public void addWall(){
		for(Wall wall : walls) {
			gameEngine.addEntityToGame(wall);
		}
	}

}
