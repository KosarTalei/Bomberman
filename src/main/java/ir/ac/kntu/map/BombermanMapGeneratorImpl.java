package ir.ac.kntu.map;

import ir.ac.kntu.factory.BombermanObjectFactory;
import ir.ac.kntu.go.GameObject;

public class BombermanMapGeneratorImpl extends MapGeneratorImpl<MapEntity> {

	private BombermanObjectFactory objectFactory;

	public BombermanMapGeneratorImpl(BombermanObjectFactory objectFactory) {
		super();
		this.objectFactory = objectFactory;
	}

	@Override
	protected GameObject generateEntity(int x, int y, MapEntity entity) {
		switch (entity) {
		case W:
			return objectFactory.getWall(x, y);
		case E:
			return objectFactory.getMovableObject(x, y);
		case P:
			return objectFactory.getPlayer(x, y);
		case B:
			return objectFactory.getBomb(x, y);
		case G:
			case N:
				return objectFactory.getGrass(x,y);
			default:
			return null;
		}
	}

}
