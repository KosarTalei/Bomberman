package ir.ac.kntu.map;

public enum MapEntity {
	W("Wall"), E("Enemy"), B("Bomb"),G("Grass"),P("Player"),
	b("Brick");

	private String	name;

	private MapEntity(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
