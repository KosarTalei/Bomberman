package ir.ac.kntu.map;

import java.util.List;

import ir.ac.kntu.go.GameObject;

public interface MapGenerator<Entity> {
	List<GameObject> generate(List<List<Entity>> mapEntities);
}
