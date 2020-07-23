package ir.ac.kntu.map;

public enum MapEntity {
	W("Wall"), E("Enemy"), P("Player"), B("Bomb"),G("Grass");

	private String	name;

	private MapEntity(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
