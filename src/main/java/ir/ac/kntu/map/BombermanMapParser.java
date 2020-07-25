package ir.ac.kntu.map;

public class BombermanMapParser extends MapParser<MapEntity> {

	@Override
	protected MapEntity parseEntity(char charAt) {
		return MapEntity.valueOf(String.valueOf(charAt));
	}

}
