package ir.ac.kntu;

import ir.ac.kntu.factory.BombermanObjectFactory;
import ir.ac.kntu.map.MapEntity;
import ir.ac.kntu.map.MapGenerator;
import ir.ac.kntu.map.MapParser;

public class Bomberman extends GameEngine<BombermanObjectFactory, MapEntity> {

	public Bomberman() {
		super((BombermanObjectFactory) Factory.getFactory(), (MapParser<MapEntity>) Factory.getMapParser(),
				(MapGenerator<MapEntity>) Factory.getGenerator(),
				Factory.getKeyLogger());
	}


}
